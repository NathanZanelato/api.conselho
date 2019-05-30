package org.conselhotutelar.enums;

public enum MedidaAplicada {


    UM(1,"01 - Orientação / Aconselhamento aos pais ou responsáveis."),
    DOIS(2,"02 - Determinação."),
    TRES(3,"03 - Advertência."),
    QUATRO(4,"04 - Acolhimento institucional."),
    CINCO(5,"05 - Requisição / Educação."),
    SEIS(6,"06 - Requisição / Saúde."),
    SETE(7,"07 - Requisição / Social."),
    OITO(8,"08 - Requisição / Segurança Pública."),
    NOVE(9,"09 - Requisição de certidão de nascimento."),
    DEZ(10,"10 - Medida de proteção CRAS."),
    ONZE(11,"11 - Medida de proteção CREAS."),
    DOZE(12,"12 - Medida de proteção unidade de saúde."),
    TREZE(13,"13 - Medida de proteção vigilancia sanitária."),
    QUATORZE(14,"14- Encaminhamento defensoria publica / Advogado."),
    QUINZE(15,"15 - Delegacias."),
    DEZESSEIS(16,"16 - CIEE / ABADEUS."),
    DEZESSETE(17,"17 - Policia Militar."),
    DEZOITO(18, "18 - Representação ao MP para efeitos das ações de perda ou suspensão do poder familiar, após esgotadas as possibilidades de manutenção da criança ou do adolescente junto a família natural."),
    DEZENOVE(19, "19 - Representação ao MP de fato que constituia infração administrativa ou penal contra os direitos de criança ou adolescente."),
    VINTE(20,"20 - Representação junto a autoridade judiciária nos casos de descumprimento injustificado de suas deliberações."),
    VINTE_UM(21,"21 - Encaminhamento à autoridade judiciária dos casos de sua competência."),
    VINTE_DOIS(22, "22 - NCE."),
    VINTE_TRES(23, "23 - Medida de proteção CAPSi."),
    VINTE_QUATRO(24, "24 - CAPSad / CAPSII / CAPSIII."),
    VINTE_CINCO(25, "25 - Ofícios expedidos."),
    VINTE_SEIS(26, "26 - E-mail expedidos."),
    VINTE_SETE(27, "27 - Visitas domiciliares."),
    VINTE_OITO(28, "28 - Inspeção nas entidades mencionadas no art. 90 do ECA."),
    VINTE_NOVE(29, "29 - Participação em audiências."),
    TRINTA(30,"30 - Atendimentos realizados em regime de plantão.")
    ;

    private Integer value;
    private String descricao;

    MedidaAplicada(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static MedidaAplicada byValue(Integer value) {
        for (MedidaAplicada v : MedidaAplicada.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Valor '%s' inválido para medida aplicada", value));
    }

    public Integer getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }
}
