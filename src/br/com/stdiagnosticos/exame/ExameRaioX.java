package br.com.stdiagnosticos.exame;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.laudo.GeradorDeLaudo;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;

import java.time.LocalDate;

public class ExameRaioX extends Exame {
    private String caminhoImagem;

    public ExameRaioX(Paciente paciente, Medico medicoResponsavel, Convenio convenio, LocalDate dataInclusao, Prioridade prioridade, GeradorDeLaudo gerador) {
        super(paciente, medicoResponsavel, convenio, dataInclusao, prioridade, gerador);
    }

    public String getCaminhoImagem() { return caminhoImagem; }
    public void setCaminhoImagem(String caminhoImagem) { this.caminhoImagem = caminhoImagem; }

    public String getAssinaturaRadiologista() { return super.getAssinaturaResponsavel(); }

    @Override
    public String descreverResultados() {
        return "Raio-X de Tórax\nImagem: " + caminhoImagem;
    }

    @Override
    public String descreverResultadosHTML() {
        return "<h2>Raio-X de Tórax</h2>" +
                "<p><strong>Imagem:</strong> "+caminhoImagem+"</p>";
    }
}
