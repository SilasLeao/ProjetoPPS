package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorRessonanciaMarcapassoImplantes extends ValidadorRessonancia {

    @Override
    public String validar(ExameRessonancia exame) {

        if (exame.isPossuiMarcapasso()) {
            return "Paciente possui marcapasso cardíaco. O exame de ressonância não pode ser realizado.";
        }

        if (exame.isPossuiImplantes()) {
            return "Paciente possui implantes metálicos. O exame de ressonância não pode ser realizado.";
        }
        
        return super.validar(exame);
    }
}