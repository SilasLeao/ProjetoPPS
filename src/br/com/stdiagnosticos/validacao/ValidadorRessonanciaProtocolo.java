package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorRessonanciaProtocolo extends ValidadorRessonancia {

     @Override
    public String validar(ExameRessonancia exame) {
        if (exame.getProtocolo() == null || exame.getProtocolo().isBlank()){
            return "Protocolo não pode estar vazio";
        }
        return super.validar(exame);
    }
}