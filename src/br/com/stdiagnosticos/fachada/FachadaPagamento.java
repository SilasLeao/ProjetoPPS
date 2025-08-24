package br.com.stdiagnosticos.fachada;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.pagamento.CalculadoraPreco;
import br.com.stdiagnosticos.pagamento.Desconto;

public class FachadaPagamento {
    private CalculadoraPreco calcPreco = new CalculadoraPreco();

    public void adicionarDesconto(Desconto d) {
        calcPreco.adicionar(d);
    }

    public double calcular(Exame exame) {
        return calcPreco.calcular(exame.getPrecoBase(), exame);
    }
}
