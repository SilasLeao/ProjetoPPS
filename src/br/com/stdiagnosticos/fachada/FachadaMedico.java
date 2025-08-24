package br.com.stdiagnosticos.fachada;

import br.com.stdiagnosticos.modelos.Medico;
import java.util.ArrayList;
import java.util.List;

public class FachadaMedico {
    private final List<Medico> medicos = new ArrayList<>();

    // Criação de médico dentro da fachada
    public Medico criarMedico(String id, String nome, String crm, String especialidade, String cpf, String email) {
        Medico m = new Medico(id, nome, crm, especialidade, cpf, email);
        medicos.add(m);
        return m;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }
}
