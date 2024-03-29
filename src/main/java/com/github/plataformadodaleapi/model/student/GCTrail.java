package com.github.plataformadodaleapi.model.student;

public enum GCTrail {
    PROGRAMACAO("Programacão"),
    UX_UI_DESIGN("UX/UI Design"),
    MARKETING_DIGITAL("Marketing Digital"),
    GESTAO_E_VENDAS("Gestão Vendas"),
    COMPUTACAO_EM_NUVEM("Computação em Nuvem"),
    FERRAMENTAS_DE_TRABALHO("Ferramentas de Trabalho");

    private String value;

    GCTrail(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}