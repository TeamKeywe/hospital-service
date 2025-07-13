package com.doubleo.hospitalservice.global.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HospitalErrorCode implements BaseErrorCode {
    HOSPITAL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 병원을 찾을 수 없습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String errorClassName() {
        return this.name();
    }
}
