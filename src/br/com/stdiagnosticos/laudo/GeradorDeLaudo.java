package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.notificacao.ObservadorNotificacao;
import java.util.HashMap;
import java.util.Map;

public abstract class GeradorDeLaudo {

    private Map<String, ObservadorNotificacao> assinantes = new HashMap<>();

    public void registrarAssinante(String idPaciente, ObservadorNotificacao o){
        assinantes.put(idPaciente, o);
    }

    public void removerAssinante(String idPaciente){
        assinantes.remove(idPaciente);
    }

    protected void notificar(Exame exame, String msg){
        String id = exame.getPaciente().getIdPaciente();
        if(assinantes.containsKey(id)){
            ObservadorNotificacao obs = assinantes.get(id);
            obs.notificar(exame, msg);
        }
    }

    public final Laudo gerar(Exame exame){
        String cabecalho = gerarCabecalho(exame);
        String corpo = gerarCorpo(exame);
        String rodape = gerarRodape(exame);
        String renderizado = renderizar(cabecalho, corpo, rodape);

        Laudo laudo = new Laudo(exame, formato(), renderizado);

        // Notificação
        notificar(exame, "Seu laudo #" + exame.getNumeroExame() + " está disponível.");

        return laudo;
    }

    public abstract String formato();
    protected abstract String gerarCabecalho(Exame exame);
    protected abstract String gerarCorpo(Exame exame);
    protected abstract String gerarRodape(Exame exame);
    protected abstract String renderizar(String cabecalho, String corpo, String rodape);
}
