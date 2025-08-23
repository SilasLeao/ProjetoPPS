package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorProtocoloRessonancia extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRessonancia)) return;
        ExameRessonancia rm = (ExameRessonancia) exame;
        if (rm.getProtocolo() == null || rm.getProtocolo().isBlank()) {
            throw new IllegalArgumentException("Ressonância: protocolo deve ser indicado.");
        }
    }
}
