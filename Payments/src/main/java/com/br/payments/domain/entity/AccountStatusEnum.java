package com.br.payments.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountStatusEnum implements Serializable {
    OPEN("Em aberto"),
    TODAY("Vencimento Hoje"),
    LATE("Atrasado"),
    PAID("Pago");

    private final String name;
    private final String value;

    AccountStatusEnum(String name) {
        this.name = name;
        this.value = this.toString();
    }
    public static AccountStatusEnum forValues(String value) {
        for (AccountStatusEnum accountStatusEnum : AccountStatusEnum.values()) {
            if (accountStatusEnum.value.equals(value)) {
                return accountStatusEnum;
            }
        }
        return null;
    }

}
