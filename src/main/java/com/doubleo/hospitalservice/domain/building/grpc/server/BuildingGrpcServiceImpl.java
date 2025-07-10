package com.doubleo.hospitalservice.domain.building.grpc.server;

import com.doubleo.hospitalservice.domain.building.repository.BuildingRepository;
import com.doubleo.hospitalservice.global.exception.GrpcExceptionUtil;
import com.doubleo.hospitalservice.global.exception.errorcode.BuildingErrorCode;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BuildingGrpcServiceImpl extends BuildingServiceGrpc.BuildingServiceImplBase {

    private final BuildingRepository buildingRepository;

    public BuildingGrpcServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public void getBuildingById(
            BuildingRequest request, StreamObserver<BuildingResponse> responseObserver) {
        buildingRepository
                .findById(request.getBuildingId())
                .ifPresentOrElse(
                        res -> {
                            BuildingResponse resp =
                                    BuildingResponse.newBuilder()
                                            .setBuildingId(res.getBuildingId())
                                            .setBuildingName(res.getBuildingName())
                                            .setBuildingCode(res.getBuildingCode())
                                            .build();
                            responseObserver.onNext(resp);
                            responseObserver.onCompleted();
                        },
                        () -> {
                            responseObserver.onError(
                                    GrpcExceptionUtil.toStatusRuntimeException(
                                            BuildingErrorCode.BUILDING_NOT_FOUND));
                        });
    }
}
