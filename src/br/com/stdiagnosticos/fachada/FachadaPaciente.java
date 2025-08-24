package br.com.stdiagnosticos.fachada;

import br.com.stdiagnosticos.modelos.Paciente;
import br.com.stdiagnosticos.notificacao.ObservadorNotificacao;

import java.util.ArrayList;
import java.util.List;

public class FachadaPaciente {
    private final List<Paciente> pacientes = new ArrayList<>();

    public Paciente criarPaciente(String id, String nome, String dataNasc, String cpf, String email, String telefone) {
        Paciente p = new Paciente(id, nome, dataNasc, cpf, email, telefone);
        pacientes.add(p);
        return p;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
