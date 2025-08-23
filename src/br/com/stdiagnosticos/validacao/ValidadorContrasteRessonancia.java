package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorContrasteRessonancia extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRessonancia)) return;
        ExameRessonancia rm = (ExameRessonancia) exame;
        if (rm.isUsoContraste()) {
            if (rm.getTipoContraste() == null || rm.getTipoContraste().isBlank()) {
                throw new IllegalArgumentException("Ressonância: contraste utilizado deve ser indicado.");
            }
            if (rm.getDosagemContraste() == null || rm.getDosagemContraste().isBlank()) {
                throw new IllegalArgumentException("Ressonância: dosagem do contraste deve ser indicada.");
            }
        }
    }
}
