package br.com.stdiagnosticos.pagamento;

import br.com.stdiagnosticos.exame.Exame;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DescontoIdoso implements Desconto {
    private final double percentual;

    public DescontoIdoso(double percentual){
        this.percentual = percentual;
    }

    @Override
    public double aplicar(double valorAtual, Exame exame) {
        String dataStr = exame.getPaciente().getDataNasc();
        if (dataStr != null && !dataStr.isEmpty()) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNasc = LocalDate.parse(dataStr, fmt);
            int idade = Period.between(dataNasc, LocalDate.now()).getYears();
            if (idade >= 60) return valorAtual * (1.0 - percentual);
        }
        return valorAtual;
    }

    @Override
    public String nome() { return "Desconto Idoso"; }
}
