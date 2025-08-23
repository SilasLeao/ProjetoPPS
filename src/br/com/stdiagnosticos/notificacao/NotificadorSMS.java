package br.com.stdiagnosticos.notificacao;

import br.com.stdiagnosticos.exame.Exame;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import br.com.stdiagnosticos.util.EnvLoader;

public class NotificadorSMS implements ObservadorNotificacao {

    private static final String ACCOUNT_SID = EnvLoader.get("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN  = EnvLoader.get("TWILIO_AUTH_TOKEN");
    private static final String FROM_NUMBER = EnvLoader.get("TWILIO_FROM_NUMBER");

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void notificar(Exame exame, String mensagem) {
        String toNumber = exame.getPaciente().getTelefone();
        if(toNumber == null || toNumber.isEmpty()){
            System.out.printf("[SMS-FALLBACK] Paciente %s não possui número. Mensagem: %s%n",
                    exame.getPaciente().getNomePaciente(), mensagem);
            return;
        }

        try {
            Message msg = Message.creator(new PhoneNumber(toNumber),
                    new PhoneNumber(FROM_NUMBER), mensagem).create();
            System.out.printf("[SMS] Mensagem enviada para %s | Exame #%d | SID: %s%n",
                    exame.getPaciente().getTelefone(), exame.getNumeroExame(), msg.getSid());
        } catch (Exception e) {
            System.out.println("[SMS-ERROR] Falha ao enviar SMS: " + e.getMessage());
        }
    }

    @Override
    public String canal() { return "SMS"; }
}
