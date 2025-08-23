package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;

public class GeradorLaudoTexto extends GeradorDeLaudo {
    @Override
    protected String formato() { return "TEXTO"; }

    @Override
    protected String gerarCabecalho(Exame exame) {
        return "IF Diagnósticos\nExame #" + exame.getNumeroExame() +
                "\nPaciente: " + exame.getNomePaciente() +
                "\nConvênio: " + exame.getConvenio() +
                "\nData: " + exame.getDataInclusao() + "\n";
    }

    @Override
    protected String gerarCorpo(Exame exame) {
        return exame.descreverResultados();
    }

    @Override
    protected String gerarRodape(Exame exame) {
        return "\nResponsável Técnico: " + exame.getAssinaturaResponsavel() + "\n";
    }

    @Override
    protected String renderizar(String cabecalho, String corpo, String rodape) {
        return cabecalho + "\n" + corpo + "\n" + rodape;
    }
}
