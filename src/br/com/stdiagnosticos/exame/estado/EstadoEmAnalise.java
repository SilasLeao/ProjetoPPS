package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.laudo.Laudo;

public class EstadoEmAnalise implements EstadoExame {
    @Override
    public void validar(Exame exame) {
        System.out.println("Exame já foi validado.");
    }

    @Override
    public void gerarLaudo(Exame exame) {
        Laudo laudo = exame.getGerador().gerar(exame);
        exame.setLaudo(laudo);
        exame.setEstadoAtual(new EstadoLaudoPronto());
        System.out.printf("[Laudo] Exame #%d gerado (%s).%n", exame.getNumeroExame(), laudo.getFormato());
    }

    @Override
    public String nome() { return "EM_ANALISE"; }
}
