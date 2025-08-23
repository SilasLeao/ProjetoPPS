package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorDescricaoRessonancia extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRessonancia)) return;
        ExameRessonancia rm = (ExameRessonancia) exame;
        if (rm.getDescricao() == null || rm.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Ressonância: descrição do laudo não pode estar vazia.");
        }
    }
}
