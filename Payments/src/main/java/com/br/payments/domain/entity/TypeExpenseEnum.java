package com.br.payments.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TypeExpenseEnum implements Serializable {
    FOOD("Alimentação"),
    FERRY("Balsa"),
    CINEMA("Cinema"),
    FUEL("Combustivel"),
    DPVAT("DPVAT"),
    SEVERAL("Diversos"),
    PARKING("Estacionamento"),
    PHARMACY("Fármacia"),
    HARDWARE("HardWare"),
    ACCOMMODATION("Hospedagem"),
    LEISURE("Lazer"),
    LICENSING("Licenciamento"),
    AUTOMOTIVE_MAINTENANCE("Manutenção Automovel"),
    TICKET("Passagem"),
    TOLL("Pedágio"),
    FISHERY("Pescaria"),
    GIFT("Presente"),
    RELOAD_TIM("Recarga TIM"),
    SUPERMARKET("Supermercado"),
    TRANSPORT("Transporte"),
    CLOTHING("Vestuário"),
    DOCTOR_CONSULTATION("Medico/Consulta");

    private final String name;
    private final String value;

    TypeExpenseEnum(String name) {
        this.name = name;
        this.value = this.toString();
    }
    public static TypeExpenseEnum forValues(String value) {
        for (TypeExpenseEnum typeExpenseEnum : TypeExpenseEnum.values()) {
            if (typeExpenseEnum.value.equals(value)) {
                return typeExpenseEnum;
            }
        }
        return null;
    }
}
