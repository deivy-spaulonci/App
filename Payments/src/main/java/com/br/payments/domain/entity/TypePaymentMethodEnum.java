package com.br.payments.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TypePaymentMethodEnum implements Serializable {
    CHECK("Cheque"),
    MONEY("Dinheiro"),
    MEAL_VALUE("Vale Refeição"),
    FOOD_VALUE("Vale Alimentação"),
    DEB_CARD_SANTANDER("Cartão de Débito Santander"),
    DEB_CARD_NUBANK("Cartão de Débito Nubank"),
    DEB_CARD_ORIGINAL("Cartão de Débito Original"),
    CRE_CARD_SANTANDER("Cartão de Crédito Santander"),
    PIX_ACCOUNT_SANTANDER("PIX Conta Santander"),
    DEB_NETB_SANTANDER("Débito NetBanking Conta Santander"),
    DEB_NETB_BRADESCO_PJ("Débito NetBanking Conta Bradesco PJ"),
    DEB_NETB_ORIGINAL("Débito NetBanking Banco Original"),
    DEB_NETB_NUBANK("Débito NetBanking Conta Nubank"),
    DEB_AUT_SANTANDER("Débito Automático Santander"),
    BANK_TELLER("Pagamento Boca do Caixa"),
    ATM("Pagamento Caixa Eletônico");

    private final String name;
    private final String value;

    TypePaymentMethodEnum(String name) {
        this.name = name;
        this.value = this.toString();
    }
    public static TypePaymentMethodEnum forValues(String value) {
        for (TypePaymentMethodEnum typePaymentMethod : TypePaymentMethodEnum.values()) {
            if (typePaymentMethod.value.equals(value)) {
                return typePaymentMethod;
            }
        }
        return null;
    }
}
