package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRaioX;

// Validador que checa se a imagem do Raio-X está presente
public class ValidadorImagemRaioX extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRaioX)) return;
        ExameRaioX rx = (ExameRaioX) exame;
        if (rx.getCaminhoImagem() == null || rx.getCaminhoImagem().isBlank())
            throw new IllegalArgumentException("Raio-X: imagem obrigatória.");
    }
}