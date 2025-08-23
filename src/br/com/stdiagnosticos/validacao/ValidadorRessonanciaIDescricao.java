package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorRessonanciaIDescricao extends ValidadorRessonancia {

    @Override
    public String validar(ExameRessonancia exame) {
        if (exame.getDescricao() == null || exame.getDescricao().isBlank()){
            return "Descrição não pode estar vazia";
        }
        return super.validar(exame);
    }
    
}
