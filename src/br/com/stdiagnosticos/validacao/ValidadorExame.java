package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;

public abstract class ValidadorExame {
    private ValidadorExame proximo;

    public ValidadorExame encadear(ValidadorExame prox){
        this.proximo = prox;
        return prox;
    }

    public final void validar(Exame exame){
        processar(exame);
        if (proximo != null) proximo.validar(exame);
    }

    protected abstract void processar(Exame exame);
}
