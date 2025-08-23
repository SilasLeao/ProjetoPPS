package br.com.stdiagnosticos.notificacao;

import br.com.stdiagnosticos.exame.Exame;

public interface ObservadorNotificacao {
    void notificar(Exame exame, String mensagem);
    String canal();
}
