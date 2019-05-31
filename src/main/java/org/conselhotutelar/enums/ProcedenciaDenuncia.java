package org.conselhotutelar.enums;

import org.conselhotutelar.modelos.DynamicDto;

import java.util.ArrayList;
import java.util.List;

public enum ProcedenciaDenuncia {


    UM(1,"1 - Disque denúncia local"),
    DOIS(2,"2 - Sistema social/CRAS/CREAS"),
    TRES(3,"3 - Disque 100"),
    QUATRO(4,"4 - Ministério público/ Vara da família / ministério do trabalho/defensoria publica"),
    CINCO(5,"5 - Escola estadual"),
    SEIS(6,"6 - Escola municipal"),
    SETE(7,"7 - Escola particular"),
    OITO(8,"8 - APOIA"),
    NOVE(9,"9 - Serviço de saúde/ hospitais/ unidade de saúde/ SINAM"),
    DEZ(10,"10 - A própria criança ou adolescente"),
    ONZE(11,"11 - Família/ Responsável")
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

    public static List<DynamicDto> builderAsDtoList() {
        DynamicDto dto;
        List<DynamicDto> dtos = new ArrayList<>();
        for (ProcedenciaDenuncia pd : ProcedenciaDenuncia.values()) {
            dto = DynamicDto.build().withInteger("value", pd.getValue()).with("descricao", pd.getDescricao());
            dtos.add(dto);
        }
        return dtos;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }
}
