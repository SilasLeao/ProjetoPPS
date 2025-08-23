package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeradorLaudoHTML extends GeradorDeLaudo {
    @Override
    protected String formato() { return "HTML"; }

    @Override
    protected String gerarCabecalho(Exame exame) {
        return "<h1>IF Diagnósticos</h1>" +
                "<p><strong>Exame #</strong> " + exame.getNumeroExame() + "</p>" +
                "<p><strong>Paciente:</strong> " + exame.getNomePaciente() + "</p>" +
                "<p><strong>Convênio:</strong> " + exame.getConvenio() + "</p>" +
                "<p><strong>Data:</strong> " + exame.getDataInclusao() + "</p>";
    }

    @Override
    protected String gerarCorpo(Exame exame) {
        return "<div>" + exame.descreverResultadosHTML() + "</div>";
    }

    @Override
    protected String gerarRodape(Exame exame) {
        return "<hr/><p><em>Responsável Técnico: " + exame.getAssinaturaResponsavel() + "</em></p>";
    }

    @Override
    protected String renderizar(String cabecalho, String corpo, String rodape) {
        String html = "<html><body>" + cabecalho + corpo + rodape + "</body></html>";

        String nomeArquivo = "laudo_" + System.currentTimeMillis() + ".html";
        File arquivo = new File(nomeArquivo);

        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            writer.write(html);
            System.out.println("[HTML] Arquivo gerado em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return html;
    }
}
