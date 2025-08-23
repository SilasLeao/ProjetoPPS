package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;

public class EstadoLaudoPronto implements EstadoExame {
    @Override
    public void validar(Exame exame) { /* nada */ }

    @Override
    public void gerarLaudo(Exame exame) { /* já gerado */ }

    @Override
    public void notificar(Exame exame) {
        //String msg = "Seu laudo #" + exame.getNumeroExame() + " está disponível.";
        //exame.notificarObservadores(msg);
        exame.setEstadoAtual(new EstadoNotificado());
    }

    @Override
    public String nome() { return "LAUDO_PRONTO"; }
}
