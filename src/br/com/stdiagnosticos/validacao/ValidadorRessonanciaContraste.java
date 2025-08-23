package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorRessonanciaContraste extends ValidadorRessonancia {

    @Override
    public String validar(ExameRessonancia exame) {
        
        if (!exame.isUsoContraste()) {
            return super.validar(exame);
        }

        if (exame.getTipoContraste() == null || exame.getTipoContraste().isBlank()) {
            return "Se utilizou contraste, o tipo de contraste deve ser indicado.";
        }

        if (exame.getDosagemContraste() == null || exame.getDosagemContraste().isBlank()) {
            return "Se utilizou contraste, a dosagem administrada deve ser indicada.";
        }
        
        return super.validar(exame);
    }
}