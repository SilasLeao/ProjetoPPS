package br.com.stdiagnosticos.pagamento;

import br.com.stdiagnosticos.exame.Exame;

public interface Desconto {
    double aplicar(double valorAtual, Exame exame);
    String nome();
}
