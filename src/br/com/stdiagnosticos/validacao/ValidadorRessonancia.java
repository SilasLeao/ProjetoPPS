package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRessonancia;

public abstract class ValidadorRessonancia implements ValidadorExame<ExameRessonancia> {
    private ValidadorExame<ExameRessonancia> proximo;

    @Override
    public void setNext(ValidadorExame<ExameRessonancia> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String validar(ExameRessonancia exame) {
        if (proximo != null) {
            return proximo.validar(exame);
        }

        return "Exame validado!";

        
    }
}
