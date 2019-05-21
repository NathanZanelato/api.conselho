package org.conselhotutelar.enums;

public enum Sexo {

    FEMININO("F"), MASCULINO("M"), OUTRO("O");

    private String value;

    Sexo(String value) {
        this.value = value;
    }

    public static Sexo byValue(String value) {
        for (Sexo v : Sexo.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para opção do sexo", value));
    }

    public String getValue() {
        return value;
    }

}
