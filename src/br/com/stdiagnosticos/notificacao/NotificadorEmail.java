package br.com.stdiagnosticos.notificacao;

import br.com.stdiagnosticos.exame.Exame;

public class NotificadorEmail implements ObservadorNotificacao {
    @Override
    public void notificar(Exame exame, String mensagem) {
        // Ponto de extensão: integrar JavaMail/SMTP aqui.
        System.out.printf("[Email] Para: %s | Exame #%d | %s%n",
                exame.getNomePaciente(), exame.getNumeroExame(), mensagem);
    }

    @Override
    public String canal() { return "EMAIL"; }
}
