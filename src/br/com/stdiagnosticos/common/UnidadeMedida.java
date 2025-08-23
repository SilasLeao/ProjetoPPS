package br.com.stdiagnosticos.common;

public enum UnidadeMedida {
    MG_DL("mg/dL"),
    MG_L("mg/L"),
    G_DL("g/dL"),
    U_ML("U/mL");

    private final String rotulo;
    UnidadeMedida(String r){ this.rotulo = r; }
    public String rotulo(){ return rotulo; }
}
