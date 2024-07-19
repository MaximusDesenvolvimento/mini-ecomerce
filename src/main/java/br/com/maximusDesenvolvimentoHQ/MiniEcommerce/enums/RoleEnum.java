package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleEnum {
    ROLE_ADMIN,
    ROLE_CUSTOMER,
    ROLE_VENDOR
}
