package org.conselhotutelar.enums;

import org.conselhotutelar.modelos.DynamicDto;

import java.util.ArrayList;
import java.util.List;

public enum AgenteViolador {

    UM(1, "1 - Secretaria assistência social /  CRAS / CREAS"),
    DOIS(2, "2 - Secretaria de saúde / unidades de saúde"),
    TRES(3, "3 - Educação / Municipio"),
    QUATRO(4, "4 - Educação / ONG / Particular / AFASC"),
    CINCO(5, "5 - Hospitais"),
    SEIS(6, "6 - Policia civil"),
    SETE(7, "7 - Policia militar"),
    OITO(8, "8 - Pai"),
    NOVE(9, "9 - Mãe"),
    DEZ(10, "10 - Padrasto"),
    ONZE(11, "11 - Madrasta"),
    DOZE(12, "12 - Outros membros da família em geral"),
    TREZE(13, "13 - Responsável / Guardião"),
    QUATORZE(14, "14 - Outros")
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

    public static List<DynamicDto> builderAsDtoList() {
        DynamicDto dto;
        List<DynamicDto> dtos = new ArrayList<>();
        for (AgenteViolador av : AgenteViolador.values()) {
            dto = DynamicDto.build().withInteger("value", av.getValue()).with("descricao", av.getDescricao());
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
