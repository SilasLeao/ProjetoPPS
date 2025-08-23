package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;

public class EstadoLaudoPronto implements EstadoExame {
    @Override
    public void validar(Exame exame) {
        System.out.println("Exame já foi validado.");
    }

    @Override
    public void gerarLaudo(Exame exame) {
        System.out.println("Laudo já foi gerado.");
    }

    @Override
    public String nome() { return "LAUDO_PRONTO"; }
}
