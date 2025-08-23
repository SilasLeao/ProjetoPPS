package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorMarcapassoRessonancia extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRessonancia)) return;
        ExameRessonancia rm = (ExameRessonancia) exame;
        if (rm.isPossuiMarcapasso() || rm.isPossuiImplantes()) {
            throw new IllegalArgumentException("Ressonância: paciente com marcapasso/implantes metálicos não pode realizar o exame.");
        }
    }
}
