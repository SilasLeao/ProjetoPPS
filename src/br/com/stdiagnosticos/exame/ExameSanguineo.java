package br.com.stdiagnosticos.exame;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.laudo.GeradorDeLaudo;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;

import java.time.LocalDate;
import java.util.*;

public class ExameSanguineo extends Exame {
    private final List<Indicador> indicadores = new ArrayList<>();
    private final List<String> observacoes = new ArrayList<>();
    public ExameSanguineo(Paciente paciente, Medico medicoResponsavel, Convenio convenio, LocalDate dataInclusao, Prioridade prioridade, GeradorDeLaudo gerador) {
        super(paciente, medicoResponsavel, convenio, dataInclusao, prioridade, gerador);
    }

    public void addIndicador(Indicador i){ indicadores.add(i); }
    public List<Indicador> getIndicadores(){ return Collections.unmodifiableList(indicadores); }

    public Indicador getIndicadorPorNome(String nome){
        return indicadores.stream().filter(i -> i.nome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    public void adicionarObservacao(String obs){ observacoes.add(obs); }

    @Override
    public String descreverResultados() {
        StringBuilder sb = new StringBuilder("Exame Sanguíneo\n");
        for (Indicador i : indicadores) sb.append(i.texto()).append("\n");
        if (!observacoes.isEmpty()) {
            sb.append("\nObservações:\n");
            observacoes.forEach(o -> sb.append("- ").append(o).append("\n"));
        }
        return sb.toString();
    }

    @Override
    public String descreverResultadosHTML() {
        StringBuilder sb = new StringBuilder("<h2>Exame Sanguíneo</h2><ul>");
        for (Indicador i : indicadores) {
            sb.append("<li>").append(i.nome()).append(": ").append(String.format("%.2f", i.valor()))
                    .append(" ").append(i.unidade().rotulo()).append("</li>");
        }
        sb.append("</ul>");
        if (!observacoes.isEmpty()) {
            sb.append("<h3>Observações</h3><ul>");
            for (String o : observacoes) sb.append("<li>").append(o).append("</li>");
            sb.append("</ul>");
        }
        return sb.toString();
    }
}
