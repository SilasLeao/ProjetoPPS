package br.com.stdiagnosticos.exame;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.laudo.GeradorDeLaudo;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;

import java.time.LocalDate;

public class ExameRessonancia extends Exame {
    private String descricao;
    private String protocolo;
    private boolean usoContraste;
    private String tipoContraste;
    private String dosagemContraste;
    private boolean possuiMarcapasso;
    private boolean possuiImplantes;

    public ExameRessonancia(Paciente paciente, Medico medicoResponsavel, Convenio convenio, LocalDate dataInclusao, Prioridade prioridade, GeradorDeLaudo gerador) {
        super(paciente, medicoResponsavel, convenio, dataInclusao, prioridade, gerador);
    }

    // Getters/Setters
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }

    public boolean isUsoContraste() { return usoContraste; }
    public void setUsoContraste(boolean usoContraste) { this.usoContraste = usoContraste; }

    public String getTipoContraste() { return tipoContraste; }
    public void setTipoContraste(String tipoContraste) { this.tipoContraste = tipoContraste; }

    public String getDosagemContraste() { return dosagemContraste; }
    public void setDosagemContraste(String dosagemContraste) { this.dosagemContraste = dosagemContraste; }

    public boolean isPossuiMarcapasso() { return possuiMarcapasso; }
    public void setPossuiMarcapasso(boolean possuiMarcapasso) { this.possuiMarcapasso = possuiMarcapasso; }

    public boolean isPossuiImplantes() { return possuiImplantes; }
    public void setPossuiImplantes(boolean possuiImplantes) { this.possuiImplantes = possuiImplantes; }

    public String getAssinaturaRadiologista() { return super.getAssinaturaResponsavel(); }

    @Override
    public String descreverResultados() {
        StringBuilder sb = new StringBuilder("Ressonância Magnética\n");
        sb.append("Protocolo: ").append(protocolo).append("\n");
        if (usoContraste) sb.append("Contraste: ").append(tipoContraste).append(" (").append(dosagemContraste).append(")\n");
        sb.append("Descrição: ").append(descricao);
        return sb.toString();
    }

    @Override
    public String descreverResultadosHTML() {
        StringBuilder sb = new StringBuilder("<h2>Ressonância Magnética</h2>");
        sb.append("<p><strong>Protocolo:</strong> ").append(protocolo).append("</p>");
        if (usoContraste) sb.append("<p><strong>Contraste:</strong> ").append(tipoContraste)
                .append(" (").append(dosagemContraste).append(")</p>");
        sb.append("<p><strong>Descrição:</strong> ").append(descricao).append("</p>");
        return sb.toString();
    }
}
