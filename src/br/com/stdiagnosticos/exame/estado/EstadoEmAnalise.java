package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.laudo.Laudo;

public class EstadoEmAnalise implements EstadoExame {

    @Override
    public void validar(Exame exame){
        throw new UnsupportedOperationException("Exame já em análise, não pode validar novamente.");
    }

    @Override
    public void gerarLaudo(Exame exame){
        Laudo laudo = exame.getGerador().gerar(exame);
        exame.setLaudo(laudo);

        // Log de geração
        System.out.println("[Laudo] Exame #" + exame.getNumeroExame() + " gerado (" + exame.getGerador().formato() + ").");
    }

    @Override
    public String nome(){
        return "Em Análise";
    }
}
