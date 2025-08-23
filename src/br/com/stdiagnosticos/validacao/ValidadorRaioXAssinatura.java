package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRaioX;

public class ValidadorRaioXAssinatura extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRaioX)) return;
        ExameRaioX rx = (ExameRaioX) exame;
        if (rx.getCaminhoImagem() == null || rx.getCaminhoImagem().isBlank())
            throw new IllegalArgumentException("Raio-X: imagem obrigatória.");
        if (rx.getAssinaturaRadiologista() == null || rx.getAssinaturaRadiologista().isBlank())
            throw new IllegalArgumentException("Raio-X: assinatura do radiologista é obrigatória.");
    }
}
