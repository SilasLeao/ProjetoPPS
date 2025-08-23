package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameSanguineo;

public class ValidadorSanguineoBase extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameSanguineo)) return;
        ExameSanguineo s = (ExameSanguineo) exame;
        if (s.getIndicadores().isEmpty()) {
            throw new IllegalArgumentException("Exame sanguíneo deve conter ao menos um indicador.");
        }
    }
}
