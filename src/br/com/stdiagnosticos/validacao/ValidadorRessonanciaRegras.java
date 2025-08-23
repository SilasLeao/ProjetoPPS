package br.com.stdiagnosticos.validacao;

import br.com.stdiagnosticos.exame.Exame;
import br.com.stdiagnosticos.exame.ExameRessonancia;

public class ValidadorRessonanciaRegras extends ValidadorExame {
    @Override
    protected void processar(Exame exame) {
        if (!(exame instanceof ExameRessonancia)) return;
        ExameRessonancia rm = (ExameRessonancia) exame;

        if (rm.getDescricao() == null || rm.getDescricao().isBlank())
            throw new IllegalArgumentException("Ressonância: descrição do laudo não pode ser vazia.");
        if (rm.getAssinaturaRadiologista() == null || rm.getAssinaturaRadiologista().isBlank())
            throw new IllegalArgumentException("Ressonância: assinatura do radiologista é obrigatória.");
        if (rm.getProtocolo() == null || rm.getProtocolo().isBlank())
            throw new IllegalArgumentException("Ressonância: protocolo deve ser indicado.");

        if (rm.isUsoContraste()) {
            if (rm.getTipoContraste() == null || rm.getTipoContraste().isBlank())
                throw new IllegalArgumentException("Ressonância: contraste utilizado deve ser indicado.");
            if (rm.getDosagemContraste() == null || rm.getDosagemContraste().isBlank())
                throw new IllegalArgumentException("Ressonância: dosagem do contraste deve ser indicada.");
        }

        if (rm.isPossuiMarcapasso() || rm.isPossuiImplantes()) {
            throw new IllegalArgumentException("Ressonância: paciente com marcapasso/implantes metálicos não pode realizar o exame.");
        }
    }
}
