package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;

public class EstadoNotificado implements EstadoExame {
    @Override
    public void validar(Exame exame) { /* encerrado */ }

    @Override
    public void gerarLaudo(Exame exame) { /* encerrado */ }

    @Override
    public void notificar(Exame exame) { /* encerrado */ }

    @Override
    public String nome() { return "NOTIFICADO"; }
}
