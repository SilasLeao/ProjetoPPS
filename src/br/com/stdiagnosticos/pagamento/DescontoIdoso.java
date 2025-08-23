package br.com.stdiagnosticos.pagamento;

import br.com.stdiagnosticos.exame.Exame;

import java.time.LocalDate;
import java.time.Period;

public class DescontoIdoso implements Desconto {
    private final double percentual;

    public DescontoIdoso(double percentual){
        this.percentual = percentual;
    }

    // Simples: usa campo opcional de dataNascimento no Exame (se existir) ou ignora.
    @Override
    public double aplicar(double valorAtual, Exame exame) {
        if (exame.getDataNascimentoPaciente() != null) {
            int idade = Period.between(exame.getDataNascimentoPaciente(), LocalDate.now()).getYears();
            if (idade >= 60) return valorAtual * (1.0 - percentual);
        }
        return valorAtual;
    }

    @Override
    public String nome() { return "Desconto Idoso"; }
}
