package com.nnroad.common.enums;

import lombok.Getter;

@Getter
public enum NumEnum {

    ONE(1);

    NumEnum(Integer num) {
        this.num = num;
    }

    private final Integer num;

}