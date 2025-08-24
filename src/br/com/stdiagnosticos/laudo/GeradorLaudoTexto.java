package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeradorLaudoTexto extends GeradorDeLaudo {
    @Override
    public String formato() { return "TEXTO"; }

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
        String conteudo = cabecalho + "\n\n" + corpo + "\n\n" + rodape + "\n";
        // Escreve arquivo simulando PDF
        try {
            Path p = Path.of("laudo-"+System.currentTimeMillis()+".txt");
            Files.writeString(p, conteudo);
            System.out.println("[Texto] Arquivo gerado em: " + p.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo;
    }
}
