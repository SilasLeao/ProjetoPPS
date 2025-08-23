package br.com.stdiagnosticos.notificacao;

import br.com.stdiagnosticos.exame.Exame;

public class NotificadorSMS implements ObservadorNotificacao {
    @Override
    public void notificar(Exame exame, String mensagem) {
        System.out.printf("[SMS] Para: %s | Exame #%d | %s%n",
                exame.getNomePaciente(), exame.getNumeroExame(), mensagem);
    }
    @Override
    public String canal() { return "SMS"; }
}
