package br.com.stdiagnosticos.exame.estado;

import br.com.stdiagnosticos.exame.Exame;

public interface EstadoExame {
    void validar(Exame exame);
    void gerarLaudo(Exame exame);
    String nome();
}
