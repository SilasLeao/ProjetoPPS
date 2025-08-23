package br.com.stdiagnosticos.prioridade;

import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.exame.Exame;

import java.util.LinkedList;

public class FilaPrioridadeExames {
    private final LinkedList<Exame> fila = new LinkedList<>();

    public void inserir(Exame exame){
        Prioridade p = exame.getPrioridade();
        if (p == Prioridade.URGENTE) {
            fila.addFirst(exame); // início da fila
            return;
        }
        if (p == Prioridade.POUCO_URGENTE) {
            // inserir após o último URGENTE no início da fila
            int idx = 0;
            while (idx < fila.size() && fila.get(idx).getPrioridade() == Prioridade.URGENTE) {
                idx++;
            }
            fila.add(idx, exame);
            return;
        }
        // ROTINA → final
        fila.addLast(exame);
    }

    public Exame retirar(){
        return fila.pollFirst();
    }

    public boolean vazia(){ return fila.isEmpty(); }

    public int tamanho(){ return fila.size(); }
}
