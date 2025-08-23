package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;

public interface ValidadorExame<T extends Exame<T>> {

    void setNext(ValidadorExame<T> proximo);
    String validar(T exame);

}
