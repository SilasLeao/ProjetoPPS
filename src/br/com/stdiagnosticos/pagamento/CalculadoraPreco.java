package br.com.stdiagnosticos.pagamento;

import br.com.stdiagnosticos.exame.Exame;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraPreco {
    private final List<Desconto> descontos = new ArrayList<>();

    public CalculadoraPreco adicionar(Desconto d){
        descontos.add(d);
        return this;
    }

    public double calcular(double precoBase, Exame exame){
        double valor = precoBase;
        for (Desconto d : descontos) {
            valor = d.aplicar(valor, exame);
        }
        return Math.max(0.0, valor);
    }
}
