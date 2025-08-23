package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;

import java.time.LocalDateTime;

public class Laudo {
    private final Exame exame;
    private final String formato; // "TEXTO", "HTML", "PDF"
    private final String conteudo;
    private final LocalDateTime geradoEm;
    private Medico medicoResponsavel;
    private Paciente paciente;

    public Laudo(Exame exame, String formato, String conteudo) {
        this.exame = exame;
        this.formato = formato;
        this.conteudo = conteudo;
        this.geradoEm = LocalDateTime.now();
        this.medicoResponsavel = medicoResponsavel;
        this.paciente = paciente;
    }

    public long getNumeroExame() { return exame.getNumeroExame(); }
    public String getFormato() { return formato; }
    public String getConteudo() { return conteudo; }
    public LocalDateTime getGeradoEm() { return geradoEm; }
}
