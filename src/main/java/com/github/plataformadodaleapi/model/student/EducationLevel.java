package com.github.plataformadodaleapi.model.student;

public enum EducationLevel {
    FUNDAMENTAL("Ensino Fundamental"),
    MEDIO("Ensino médio"),
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós Graduação"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private String value;

    EducationLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}