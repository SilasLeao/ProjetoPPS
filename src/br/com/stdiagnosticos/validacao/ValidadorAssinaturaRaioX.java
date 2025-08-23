package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRaioX;

// Validador que checa se a assinatura do radiologista está presente
public class ValidadorAssinaturaRaioX extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRaioX)) return;
        ExameRaioX rx = (ExameRaioX) exame;
        if (rx.getAssinaturaRadiologista() == null || rx.getAssinaturaRadiologista().isBlank())
            throw new IllegalArgumentException("Raio-X: assinatura do radiologista é obrigatória.");
    }
}