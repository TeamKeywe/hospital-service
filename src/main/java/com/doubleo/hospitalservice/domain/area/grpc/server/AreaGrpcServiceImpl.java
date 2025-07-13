package com.doubleo.hospitalservice.domain.area.grpc.server;

import com.doubleo.hospitalservice.domain.area.domain.Area;
import com.doubleo.hospitalservice.domain.area.dto.response.AreaGrpcResponse;
import com.doubleo.hospitalservice.domain.area.repository.AreaRepository;
import com.doubleo.hospitalservice.domain.building.domain.Building;
import com.doubleo.hospitalservice.domain.building.repository.BuildingRepository;
import com.doubleo.hospitalservice.global.exception.GrpcExceptionUtil;
import com.doubleo.hospitalservice.global.exception.errorcode.AreaErrorCode;
import com.doubleo.hospitalservice.global.exception.errorcode.BuildingErrorCode;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AreaGrpcServiceImpl extends AreaServiceGrpc.AreaServiceImplBase {

    private final AreaRepository areaRepository;
    private final BuildingRepository buildingRepository;

    public AreaGrpcServiceImpl(
            AreaRepository areaRepository, BuildingRepository buildingRepository) {
        this.areaRepository = areaRepository;
        this.buildingRepository = buildingRepository;
    }

    @Override
    public void getAreaById(AreaIdRequest request, StreamObserver<AreaResponse> responseObserver) {
        areaRepository
                .findById(request.getAreaId())
                .map(AreaGrpcResponse::fromEntity)
                .ifPresentOrElse(
                        res -> {
                            AreaResponse resp =
                                    AreaResponse.newBuilder()
                                            .setAreaId(res.id())
                                            .setTenantId(res.tenantId())
                                            .setBuildingId(res.buildingId())
                                            .setAreaName(res.areaName())
                                            .setAreaCode(res.areaCode())
                                            .build();
                            responseObserver.onNext(resp);
                            responseObserver.onCompleted();
                        },
                        () -> {
                            responseObserver.onError(
                                    GrpcExceptionUtil.toStatusRuntimeException(
                                            AreaErrorCode.AREA_NOT_FOUND));
                        });
    }

    @Override
    public void listAreas(
            AreaListRequest request, StreamObserver<AreaListResponse> responseObserver) {
        List<AreaGrpcResponse> all =
                areaRepository.findAll().stream().map(AreaGrpcResponse::fromEntity).toList();

        AreaListResponse response =
                AreaListResponse.newBuilder()
                        .addAllArea(
                                all.stream()
                                        .map(
                                                dto ->
                                                        AreaResponse.newBuilder()
                                                                .setAreaId(dto.id())
                                                                .setTenantId(dto.tenantId())
                                                                .setBuildingId(dto.buildingId())
                                                                .setAreaName(dto.areaName())
                                                                .setAreaCode(dto.areaCode())
                                                                .build())
                                        .collect(Collectors.toList()))
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAreaFullNameByCode(
            AreaFullNameRequest request, StreamObserver<AreaFullNameResponse> responseObserver) {
        String[] parts = request.getAreaCode().split("_");
        List<String> areaNameToken = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            if (i == 2) {
                areaNameToken.add(request.getAreaCode());
            } else {
                areaNameToken.add(parts[i]);
            }
        }
        for (int i = 0; i < areaNameToken.size(); i++) {
            if (i == 0) {
                Optional<Building> building =
                        buildingRepository.findByTenantIdAndBuildingCode(
                                request.getTenantId(), areaNameToken.get(i));
                if (building.isPresent()) {
                    sb.append(building.get().getBuildingName());
                } else {
                    responseObserver.onError(
                            GrpcExceptionUtil.toStatusRuntimeException(
                                    BuildingErrorCode.BUILDING_NOT_FOUND));
                    return;
                }
            } else if (i == 1) {
                try {
                    sb.append(Integer.parseInt(areaNameToken.get(i))).append("층");
                } catch (NumberFormatException e) {
                    responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException());
                    return;
                }
            } else {
                Optional<Area> area =
                        areaRepository.findByTenantIdAndAreaCode(
                                request.getTenantId(), areaNameToken.get(i));
                if (area.isPresent()) {
                    sb.append(area.get().getAreaName());
                } else {
                    responseObserver.onError(
                            GrpcExceptionUtil.toStatusRuntimeException(
                                    AreaErrorCode.AREA_NOT_FOUND));
                    return;
                }
            }
            if (i != areaNameToken.size() - 1) {
                sb.append(" ");
            }
        }
        AreaFullNameResponse response =
                AreaFullNameResponse.newBuilder().setAreaFullName(sb.toString()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
