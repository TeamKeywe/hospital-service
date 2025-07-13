package com.doubleo.hospitalservice.global.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BuildingErrorCode implements BaseErrorCode {
    BUILDING_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 건물입니다.");
    ;

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String errorClassName() {
        return this.name();
    }
}
