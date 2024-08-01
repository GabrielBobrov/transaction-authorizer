package com.gabriel.transaction.authorizator.entrypoint.exception.model.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/dados-invalidos", "Dados inválidos"),
    SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
    INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
    MESSAGE_INCOMPRESSIBLE("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTITY_IN_USE("/entidade-em-uso", "Entidade em uso"),
    BUSINESS_ERROR("/erro-negocio", "Violação de regra de negócio");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://transaction-authorizator.com.br" + path;
        this.title = title;
    }

}
