package br.com.stdiagnosticos.exame;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.common.SequenciadorExame;
import br.com.stdiagnosticos.laudo.GeradorDeLaudo;
import br.com.stdiagnosticos.laudo.Laudo;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;
import br.com.stdiagnosticos.notificacao.ObservadorNotificacao;
import br.com.stdiagnosticos.validacao.ValidadorExame;
import br.com.stdiagnosticos.exame.estado.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Exame {
    private final long numeroExame;
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDate dataNascimentoPaciente;
    private final Convenio convenio;
    private final LocalDate dataInclusao;
    private final Prioridade prioridade;

    private final List<ObservadorNotificacao> observadores = new ArrayList<>();
    private GeradorDeLaudo gerador; // Implementador (Bridge)
    private ValidadorExame cadeiaValidador;
    private EstadoExame estadoAtual = new EstadoSolicitado();
    private Laudo laudo;
    private double precoBase;

    public Exame(Paciente paciente, Medico medicoResponsavel, Convenio convenio, LocalDate dataInclusao, Prioridade prioridade, GeradorDeLaudo gerador) {
        this.numeroExame = SequenciadorExame.getInstance().proximo();
        this.medicoResponsavel = medicoResponsavel;
        this.paciente = paciente;
        this.convenio = convenio;
        this.dataInclusao = dataInclusao;
        this.prioridade = prioridade;
        this.gerador = gerador;
    }

    // === Fluxo de alto nível para Main ===
    public final void validar(){ estadoAtual.validar(this); }
    public final void emitirLaudo(){ estadoAtual.gerarLaudo(this); }
    public final void notificarPacientes(){ estadoAtual.notificar(this); }

    // === Observer ===
    public void adicionarObservador(ObservadorNotificacao o){ observadores.add(o); }
    public void removerObservador(ObservadorNotificacao o){ observadores.remove(o); }
    public void notificarObservadores(String msg){
        for (ObservadorNotificacao o : observadores) o.notificar(this, msg);
    }

    // === Métodos que os geradores usam (Template) ===
    public abstract String descreverResultados();
    public abstract String descreverResultadosHTML();

    // === Getters/Setters ===
    public long getNumeroExame() { return numeroExame; }
    public String getNomePaciente() { return paciente.getNomePaciente(); }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedicoResponsavel() { return medicoResponsavel; }
    public Convenio getConvenio() { return convenio; }
    public LocalDate getDataInclusao() { return dataInclusao; }
    public Prioridade getPrioridade() { return prioridade; }

    public GeradorDeLaudo getGerador() { return gerador; }
    public void setGerador(GeradorDeLaudo g){ this.gerador = g; }

    public ValidadorExame getCadeiaValidador() { return cadeiaValidador; }
    public void setCadeiaValidador(ValidadorExame v){ this.cadeiaValidador = v; }

    public EstadoExame getEstadoAtual() { return estadoAtual; }
    public void setEstadoAtual(EstadoExame e){ this.estadoAtual = e; }

    public Laudo getLaudo() { return laudo; }
    public void setLaudo(Laudo l){ this.laudo = l; }

    public String getAssinaturaResponsavel() { return medicoResponsavel.getAssinatura(); }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public LocalDate getDataNascimentoPaciente() { return dataNascimentoPaciente; }
    public void setDataNascimentoPaciente(LocalDate d){ this.dataNascimentoPaciente = d; }
}
