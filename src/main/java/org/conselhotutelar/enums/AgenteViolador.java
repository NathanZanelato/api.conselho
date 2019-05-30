package org.conselhotutelar.enums;

public enum AgenteViolador {

    UM(1, "Secretaria assistência social /  CRAS / CREAS"),
    DOIS(2, "Secretaria de saúde / unidades de saúde"),
    TRES(3, "Educação / Municipio"),
    QUATRO(4, "Educação / ONG / Particular / AFASC"),
    CINCO(5, "Hospitais"),
    SEIS(6, "Policia civil"),
    SETE(7, "Policia militar"),
    OITO(8, "Pai"),
    NOVE(9, "Mãe"),
    DEZ(10, "Padrasto"),
    ONZE(11, "Madrasta"),
    DOZE(12, "Outros membros da família em geral"),
    TREZE(13, "Responsável / Guardião"),
    QUATORZE(14, "Outros")
    ;

    private Integer value;
    private String descricao;

    AgenteViolador(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static AgenteViolador byValue(Integer value) {
        for (AgenteViolador v : AgenteViolador.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para opção do agente violador", value));
    }

    public Integer getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

}
