package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRaioX;

public class ValidadorRaioXAssinatura extends ValidadorRaioX {

    @Override
    public String validar(ExameRaioX exame) {
        if (exame.getAssinaturaRadiologista() == null | exame.getAssinaturaRadiologista().isBlank()) {
            return "O Raio-X deve conter a assinatura do radiologista!";
        }
        return super.validar(exame);
    }
}
