package br.com.stdiagnosticos.laudo;

import br.com.stdiagnosticos.exame.Exame;

// Implementador do Bridge + Template de geração
public abstract class GeradorDeLaudo {
    public final Laudo gerar(Exame exame){
        String cabecalho = gerarCabecalho(exame);
        String corpo = gerarCorpo(exame);
        String rodape = gerarRodape(exame);
        String renderizado = renderizar(cabecalho, corpo, rodape);
        return new Laudo(exame, formato(), renderizado);
    }

    protected abstract String formato();

    protected abstract String gerarCabecalho(Exame exame);
    protected abstract String gerarCorpo(Exame exame);
    protected abstract String gerarRodape(Exame exame);

    protected abstract String renderizar(String cabecalho, String corpo, String rodape);
}
