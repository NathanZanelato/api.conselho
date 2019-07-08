package org.conselhotutelar.enums;

public enum RelType {

    PDF("pdf"), XLS("xls");

    private String value;

    RelType(String value) {
        this.value = value;
    }

    public static RelType byValue(String value) {
        for (RelType v : RelType.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para opção de arquivo de saída", value));
    }

    public String getValue() {
        return value;
    }

}
