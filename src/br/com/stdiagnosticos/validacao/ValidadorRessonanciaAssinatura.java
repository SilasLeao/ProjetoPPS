package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorRessonanciaAssinatura extends ValidadorRessonancia {

    @Override
    public String validar(ExameRessonancia exame) {
        if (exame.getAssinaturaRadiologista() == null || exame.getAssinaturaRadiologista().isBlank()) {
            return "Exame deve ter a assinatura do radiologista responsável.";
        }
        
        return super.validar(exame);
    }
}