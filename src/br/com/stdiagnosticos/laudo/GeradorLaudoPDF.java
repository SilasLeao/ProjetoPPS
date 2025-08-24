package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;

public class GeradorLaudoPDF extends GeradorDeLaudo {

    @Override
    public String formato() { return "PDF"; }

    @Override
    protected String gerarCabecalho(Exame exame) {
        return "IF Diagnósticos | Exame #" + exame.getNumeroExame() +
                " | Paciente: " + exame.getNomePaciente() +
                " | Convênio: " + exame.getConvenio() +
                " | Data: " + exame.getDataInclusao();
    }

    @Override
    protected String gerarCorpo(Exame exame) {
        return exame.descreverResultados();
    }

    @Override
    protected String gerarRodape(Exame exame) {
        return "Responsável Técnico: " + exame.getAssinaturaResponsavel();
    }

    @Override
    protected String renderizar(String cabecalho, String corpo, String rodape) {
        String conteudo = cabecalho + "\n\n" + corpo + "\n\n" + rodape + "\n";

        String nomeArquivo = "laudo-" + System.currentTimeMillis() + ".pdf";

        try {
            PdfWriter writer = new PdfWriter(nomeArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(cabecalho));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(corpo));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(rodape));

            document.close();

            System.out.println("[PDF] Arquivo gerado em: " + new File(nomeArquivo).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conteudo;
    }
}
