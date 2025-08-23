package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;

public class GeradorLaudoHTML extends GeradorDeLaudo {
    @Override
    protected String formato() { return "HTML"; }

    @Override
    protected String gerarCabecalho(Exame exame) {
        return "<h1>IF Diagnósticos</h1>" +
                "<p><strong>Exame #</strong>"+exame.getNumeroExame()+"</p>" +
                "<p><strong>Paciente:</strong> "+exame.getNomePaciente()+"</p>" +
                "<p><strong>Convênio:</strong> "+exame.getConvenio()+"</p>" +
                "<p><strong>Data:</strong> "+exame.getDataInclusao()+"</p>";
    }

    @Override
    protected String gerarCorpo(Exame exame) {
        return "<div>"+exame.descreverResultadosHTML()+"</div>";
    }

    @Override
    protected String gerarRodape(Exame exame) {
        return "<hr/><p><em>Responsável Técnico: "+exame.getAssinaturaResponsavel()+"</em></p>";
    }

    @Override
    protected String renderizar(String cabecalho, String corpo, String rodape) {
        return "<html><body>"+cabecalho+corpo+rodape+"</body></html>";
    }
}
