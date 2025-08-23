package br.com.stdiagnosticos.modelos;

import br.com.stdiagnosticos.exame.Exame;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String idPaciente;
    private String nomePaciente;
    private String dataNasc;
    private String cpf;
    private String email;

    private List<Exame> exames;

    public Paciente(String idPaciente, String nomePaciente, String dataNasc,
                    String cpf, String email) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.email = email;
        this.exames = new ArrayList<>();
    }

    public Paciente() {
        this.exames = new ArrayList<>();
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void addExame(Exame exame) {
        this.exames.add(exame);
    }

    public void removeExame(Exame exame) {
        this.exames.remove(exame);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente='" + idPaciente + '\'' +
                ", nomePaciente='" + nomePaciente + '\'' +
                ", dataNasc=" + dataNasc +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
