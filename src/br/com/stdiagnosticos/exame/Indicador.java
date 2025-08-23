package br.com.stdiagnosticos.exame;

import br.com.stdiagnosticos.common.UnidadeMedida;

public record Indicador(String nome, double valor, UnidadeMedida unidade) {
    public String texto(){
        return String.format("%-15s %.2f %s", nome, valor, unidade.rotulo());
    }
}
