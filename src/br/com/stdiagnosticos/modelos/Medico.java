package br.com.stdiagnosticos.modelos;

import java.sql.Date;

public class Medico {
    private String idMedico;
    private String nomeMedico;
    private String crm;
    private String especialidade;
    private String cpf;
    private String email;
    private String assinatura;

    // Construtor com parâmetros
    public Medico(String idMedico, String nomeMedico, String crm, String especialidade,
                  String cpf, String email) {
        this.idMedico = idMedico;
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.especialidade = especialidade;
        this.cpf = cpf;
        this.email = email;
        this.assinatura = "Dr. " + nomeMedico + ", CRM: " + crm;
    }

    public Medico() {}

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getAssinatura() {
        return assinatura;
    };

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico='" + idMedico + '\'' +
                ", nomeMedico='" + nomeMedico + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
