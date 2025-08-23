package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;

public class EstadoSolicitado implements EstadoExame {
    @Override
    public void validar(Exame exame) {
        exame.getCadeiaValidador().validar(exame);
        exame.setEstadoAtual(new EstadoEmAnalise());
    }

    @Override
    public void gerarLaudo(Exame exame) {
        throw new IllegalStateException("Não é possível gerar laudo antes de validar.");
    }

    @Override
    public void notificar(Exame exame) {
        throw new IllegalStateException("Notificação somente após laudo pronto.");
    }

    @Override
    public String nome() { return "SOLICITADO"; }
}
