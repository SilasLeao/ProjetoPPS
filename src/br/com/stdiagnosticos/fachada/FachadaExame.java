package br.com.stdiagnosticos.fachada;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.exame.*;
import br.com.stdiagnosticos.laudo.GeradorDeLaudo;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;
import br.com.stdiagnosticos.prioridade.FilaPrioridadeExames;

import java.time.LocalDate;

public class FachadaExame {
    private final FilaPrioridadeExames fila = new FilaPrioridadeExames();

    public void inserirExame(Exame exame) {
        fila.inserir(exame);
    }

    public Exame processarProximo() {
        return fila.retirar();
    }

    public boolean filaVazia() {
        return fila.vazia();
    }

    // === Métodos de criação ===
    public ExameSanguineo criarExameSanguineo(Paciente paciente, Medico medico, Convenio convenio,
                                              LocalDate data, Prioridade prioridade, GeradorDeLaudo gerador) {
        ExameSanguineo ex = new ExameSanguineo(paciente, medico, convenio, data, prioridade, gerador);
        inserirExame(ex);
        return ex;
    }

    public ExameRaioX criarExameRaioX(Paciente paciente, Medico medico, Convenio convenio,
                                      LocalDate data, Prioridade prioridade, GeradorDeLaudo gerador) {
        ExameRaioX ex = new ExameRaioX(paciente, medico, convenio, data, prioridade, gerador);
        inserirExame(ex);
        return ex;
    }

    public ExameRessonancia criarExameRessonancia(Paciente paciente, Medico medico, Convenio convenio,
                                                  LocalDate data, Prioridade prioridade, GeradorDeLaudo gerador) {
        ExameRessonancia ex = new ExameRessonancia(paciente, medico, convenio, data, prioridade, gerador);
        inserirExame(ex);
        return ex;
    }
}
