package com.doubleo.hospitalservice.domain.hospitalpolicy.grpc.server;

import com.doubleo.hospitalservice.domain.hospitalpolicy.domain.HospitalPolicy;
import com.doubleo.hospitalservice.domain.hospitalpolicy.repository.HospitalPolicyRepository;
import com.doubleo.hospitalservice.domain.policy.grpc.server.MaximumGuardianNumRequest;
import com.doubleo.hospitalservice.domain.policy.grpc.server.MaximumGuardianNumResponse;
import com.doubleo.hospitalservice.domain.policy.grpc.server.MaximumGuardianNumServiceGrpc;
import com.doubleo.hospitalservice.global.exception.GrpcExceptionUtil;
import com.doubleo.hospitalservice.global.exception.errorcode.HospitalPolicyErrorCode;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class PolicyGrpcServiceImpl extends MaximumGuardianNumServiceGrpc.MaximumGuardianNumServiceImplBase{

    private final HospitalPolicyRepository hospitalPolicyRepository;

    public PolicyGrpcServiceImpl(HospitalPolicyRepository hospitalPolicyRepository) {
        this.hospitalPolicyRepository = hospitalPolicyRepository;
    }

    @Override
    public void getMaximumGuardianNumById(MaximumGuardianNumRequest request,
                                          StreamObserver<MaximumGuardianNumResponse> responseObserver) {

        HospitalPolicy hospitalPolicy = hospitalPolicyRepository
                .getHospitalPolicyByTenantId(request.getTenantId())
                .orElseThrow(() -> GrpcExceptionUtil.toStatusRuntimeException(HospitalPolicyErrorCode.HOSPITAL_POLICY_NOT_FOUND));

        MaximumGuardianNumResponse response = MaximumGuardianNumResponse.newBuilder()
                .setMaximumGuardianNum(hospitalPolicy.getMaxGuardianNum())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
