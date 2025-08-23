package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameSanguineo;
import br.com.stdiagnosticos.exame.Indicador;

public class ValidadorGlicose extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameSanguineo)) return;
        ExameSanguineo s = (ExameSanguineo) exame;
        Indicador g = s.getIndicadorPorNome("GLICOSE");
        if (g == null) return;

        double v = g.valor();
        if (v < 0) throw new IllegalArgumentException("Valor de glicose inválido.");

        // valores de referência
        if (v < 60) s.adicionarObservacao("Glicose: HIPOglicemia (<60 mg/dL)");
        else if (v <= 99) s.adicionarObservacao("Glicose dentro do NORMAL (60-99 mg/dL)");
        else if (v <= 125) s.adicionarObservacao("Glicose: INTOLERANTE (100-125 mg/dL)");
        else s.adicionarObservacao("Glicose: DIABETES (>125 mg/dL)");
    }
}
