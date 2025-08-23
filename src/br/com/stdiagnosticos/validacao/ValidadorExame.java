package br.com.stdiagnosticos.validacao;

public interface ValidadorExame<EXAME> {

    void setNext(ValidadorExame<EXAME> proximo);
    String validar(EXAME exame);

}
