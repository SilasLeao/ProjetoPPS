package br.com.stdiagnosticos.pagamento;

import br.com.stdiagnosticos.exame.Exame;

public class DescontoCampanha implements Desconto {
    private final String nomeCampanha;
    private final double percentual;

    public DescontoCampanha(String nomeCampanha, double percentual){
        this.nomeCampanha = nomeCampanha;
        this.percentual = percentual;
    }

    @Override
    public double aplicar(double valorAtual, Exame exame) {
        // Simulação: campanha sempre ativa. Você pode condicionar por mês/indicador.
        return valorAtual * (1.0 - percentual);
    }

    @Override
    public String nome() { return "Campanha: " + nomeCampanha; }
}
