package org.conselhotutelar.enums;

public enum DireitoViolado {


    UM(1,"1 - Direito a vida e saúde"),
    DOIS(2,"2 - Liberdade, respeito e dignidade"),
    TRES(3,"3 - Convivência familiar e comunitário"),
    QUATRO(4,"4 - Educação, cultura, esporte e lazer"),
    CINCO(5,"5 - Profissionalização e proteção no trabalho")
    ;

    private Integer value;
    private String descricao;

    DireitoViolado(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static DireitoViolado byValue(Integer value) {
        for (DireitoViolado v : DireitoViolado.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para direito violado", value));
    }

    public Integer getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }
}
