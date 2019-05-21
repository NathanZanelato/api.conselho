package org.conselhotutelar.enums;

public enum SimNao {

    SIM("S"), NAO("N");

    private String value;

    SimNao(String value) {
        this.value = value;
    }

    public static SimNao byValue(String value) {
        for (SimNao v : SimNao.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para esta opção", value));
    }

    public static boolean sim(SimNao simNao) {
        return simNao != null && simNao.getValue().equals(SimNao.SIM.getValue());
    }

    public static boolean sim(String value) {
        return value != null && value.equals(SimNao.SIM.getValue());
    }

    public static boolean nao(SimNao simNao) {
        return simNao == null || simNao.getValue().equals(SimNao.NAO.getValue());
    }

    public static boolean nao(String value) {
        return value == null || value.equals(SimNao.NAO.getValue());
    }

    public String getValue() {
        return value;
    }

}
