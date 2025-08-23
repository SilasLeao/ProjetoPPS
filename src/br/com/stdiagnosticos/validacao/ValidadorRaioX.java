package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRaioX;

public abstract class ValidadorRaioX implements ValidadorExame<ExameRaioX> {

    private ValidadorExame<ExameRaioX> proximo;

    @Override
    public void setNext(ValidadorExame<ExameRaioX> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String validar(ExameRaioX exame) {
        if (proximo != null) {
            return proximo.validar(exame);
        }
        return "Exame validado!";
    }
}