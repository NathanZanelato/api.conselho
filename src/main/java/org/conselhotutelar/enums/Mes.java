package org.conselhotutelar.enums;

public enum Mes {
    Janeiro(1),
    Fevereiro(2),
    Marco(3),
    Abril(4),
    Maio(5),
    Junho(6),
    Julho(7),
    Agosto(8),
    Setembro(9),
    Outubro(10),
    Novembro(11),
    Dezembro(12)
    ;

    private int value;

    Mes(int value) { this.value = value; }

    public static Mes byValue(int value) {
        for (Mes v : Mes.values()) {
            if (v.getValue() == value) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para opção de mês", value));
    }

    public int getValue() {
        return value;
    }

    public String getMes() {
        return "Marco".equals(name()) ? "Março" : name();
    }
}
