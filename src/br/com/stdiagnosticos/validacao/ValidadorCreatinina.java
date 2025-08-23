package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameSanguineo;
import br.com.stdiagnosticos.exame.Indicador;

public class ValidadorCreatinina extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameSanguineo)) return;
        ExameSanguineo s = (ExameSanguineo) exame;
        Indicador c = s.getIndicadorPorNome("CREATININA");
        if (c == null) return;

        double v = c.valor();
        if (v < 0) throw new IllegalArgumentException("Creatinina inválida.");

        if (v > 1.5) s.adicionarObservacao("Creatinina muito alta (>1.5): ALERTA!");
        else if (v > 1.2) s.adicionarObservacao("Creatinina elevada (>1.2): atenção médicos.");
        // referencias por faixa etária/sexo podem ser aplicadas via metadados do paciente
    }
}
