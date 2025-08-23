package br.com.stdiagnosticos.pagamento;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.exame.Exame;

public class DescontoConvenio implements Desconto {
    private final double percentual; // ex: 0.15 = 15%

    public DescontoConvenio(double percentual){
        this.percentual = percentual;
    }

    @Override
    public double aplicar(double valorAtual, Exame exame) {
        if (exame.getConvenio() != Convenio.SEM_CONVENIO) {
            return valorAtual * (1.0 - percentual);
        }
        return valorAtual;
    }

    @Override
    public String nome() { return "Desconto Convênio"; }
}
