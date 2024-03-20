package com.github.plataformadodaleapi.model.student;

public enum EducationLevel {
    MEDIO_EM_ANDAMENTO("Ensino Médio em andamento"),
    MEDIO_COMPLETO("Ensino Médio completo"),
    SUPERIOR_EM_ANDAMENTO("Ensino Superior em andamento"),
    SUPERIOR_COMPLETO("Ensino Superior completo"),
    POS_GRADUACAO_EM_ANDAMENTO("Pós Graduação em andamento"),
    POS_GRADUACAO_COMPLETO("Pós Graduação completo");

    private String value;

    EducationLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}