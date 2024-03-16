package com.github.plataformadodaleapi.entity;

public enum GCTrail {
    PROGRAMACAO("Programacão"),
    UX_UI_DESIGN("UX/UI Design"),
    MARKETING("Marketing"),
    GESTAO_E_VENDAS("Gestão Vendas");

    private String value;

    GCTrail(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}