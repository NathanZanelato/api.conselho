package org.conselhotutelar.enums;

public enum ProcedenciaDenuncia {


    UM(1,"Disque denúncia local"),
    DOIS(2,"Sistema social/CRAS/CREAS"),
    TRES(3,"Disque 100"),
    QUATRO(4,"Ministério público/ Vara da família / ministério do trabalho/defensoria publica"),
    CINCO(5,"Escola estadual"),
    SEIS(6,"Escola municipal"),
    SETE(7,"Escola particular"),
    OITO(8,"APOIA"),
    NOVE(9,"Serviço de saúde/ hospitais/ unidade de saúde/ SINAM"),
    DEZ(10,"A própria criança ou adolescente"),
    ONZE(11,"Família/ Responsável")
    ;

    private Integer value;
    private String descricao;

    ProcedenciaDenuncia(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static ProcedenciaDenuncia byValue(Integer value) {
        for (ProcedenciaDenuncia v : ProcedenciaDenuncia.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para procedência da denúncia", value));
    }

    public Integer getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }
}
