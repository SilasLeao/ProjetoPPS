package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorAssinaturaRessonancia extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRessonancia)) return;
        ExameRessonancia rm = (ExameRessonancia) exame;
        if (rm.getAssinaturaRadiologista() == null || rm.getAssinaturaRadiologista().isBlank()) {
            throw new IllegalArgumentException("Ressonância: assinatura do radiologista é obrigatória.");
        }
    }
}
