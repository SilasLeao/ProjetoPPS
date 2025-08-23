package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRaioX;

public class ValidadorRaioXImagem extends ValidadorRaioX {

    @Override
    public String validar(ExameRaioX exame) {
        if (exame.getCaminhoImagem() == null || exame.getCaminhoImagem().isBlank()) {
            return "Deve conter a imagem do Raio-X!";
        }
        return super.validar(exame);
    }
}