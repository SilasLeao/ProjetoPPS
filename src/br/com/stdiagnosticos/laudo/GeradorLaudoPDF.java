package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Mock de PDF: escreve um .pdf.txt (ponto de extensão para iText/PDFBox)
public class GeradorLaudoPDF extends GeradorDeLaudo {
    @Override
    protected String formato() { return "PDF"; }

    @Override
    protected String gerarCabecalho(Exame exame) {
        return "IF Diagnósticos | Exame #"+exame.getNumeroExame()+
                " | Paciente: "+exame.getNomePaciente()+
                " | Convênio: "+exame.getConvenio()+
                " | Data: "+exame.getDataInclusao();
    }

    @Override
    protected String gerarCorpo(Exame exame) {
        return exame.descreverResultados();
    }

    @Override
    protected String gerarRodape(Exame exame) {
        return "Responsável Técnico: "+exame.getAssinaturaResponsavel();
    }

    @Override
    protected String renderizar(String cabecalho, String corpo, String rodape) {
        String conteudo = cabecalho + "\n\n" + corpo + "\n\n" + rodape + "\n";
        // Escreve arquivo simulando PDF
        try {
            Path p = Path.of("laudo-"+System.currentTimeMillis()+".pdf.txt");
            Files.writeString(p, conteudo);
            System.out.println("[PDF] Arquivo gerado em: " + p.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo;
    }
}
