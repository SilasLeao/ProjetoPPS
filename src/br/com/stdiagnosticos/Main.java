package br.com.stdiagnosticos;

import br.com.stdiagnosticos.common.*;
import br.com.stdiagnosticos.exame.*;
import br.com.stdiagnosticos.exame.estado.EstadoEmAnalise;
import br.com.stdiagnosticos.laudo.*;
import br.com.stdiagnosticos.modelos.*;
import br.com.stdiagnosticos.notificacao.*;
import br.com.stdiagnosticos.fachada.*;
import br.com.stdiagnosticos.pagamento.*;
import br.com.stdiagnosticos.validacao.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // === Fachadas ===
        FachadaPaciente fachadaPaciente = new FachadaPaciente();
        FachadaMedico fachadaMedico = new FachadaMedico();
        FachadaExame fachadaExame = new FachadaExame();
        FachadaPagamento fachadaPagamento = new FachadaPagamento();

        // === Descontos ===
        fachadaPagamento.adicionarDesconto(new DescontoConvenio(0.15));
        fachadaPagamento.adicionarDesconto(new DescontoIdoso(0.08));
        fachadaPagamento.adicionarDesconto(new DescontoCampanha("Novembro Azul", 0.10));

        // === Criar pacientes via Fachada ===
        Paciente paciente1 = fachadaPaciente.criarPaciente("P001","Carlos Silva","21/09/1987","123.456.789-00","carlos.silva@email.com","+5583999999999");
        Paciente paciente2 = fachadaPaciente.criarPaciente("P002","Mariana Souza","03/02/1998","987.654.321-00","mariana.souza@email.com","+5583988888888");
        Paciente paciente3 = fachadaPaciente.criarPaciente("P003","Lucas Pereira","15/11/1976","456.789.123-00","lucas.pereira@email.com","+5583988888888");

        // === Criar médicos via Fachada ===
        Medico medico1 = fachadaMedico.criarMedico("M001","Ana Torres","CRM12345","Cardiologia","123.456.789-01","ana.torres@email.com");
        Medico medico2 = fachadaMedico.criarMedico("M002","Bruno Lima","CRM67890","Ortopedia","987.654.321-02","bruno.lima@email.com");
        Medico medico3 = fachadaMedico.criarMedico("M003","Carla Mendes","CRM54321","Pediatria","456.789.123-03","carla.mendes@email.com");

        // === Geradores de Laudo ===
        GeradorDeLaudo geradorTexto = new GeradorLaudoTexto();
        GeradorDeLaudo geradorHTML = new GeradorLaudoHTML();
        GeradorDeLaudo geradorPDF = new GeradorLaudoPDF();

        // === Registrar notificadores nos geradores (Observer) ===
        ObservadorNotificacao email = new NotificadorEmail();
        ObservadorNotificacao sms   = new NotificadorSMS();

        geradorTexto.registrarAssinante(paciente1.getIdPaciente(), email);

        geradorHTML.registrarAssinante(paciente2.getIdPaciente(), email);

        geradorPDF.registrarAssinante(paciente3.getIdPaciente(), email);
        geradorPDF.registrarAssinante(paciente3.getIdPaciente(), sms);

        // === Criar exames via FachadaExame ===
        ExameSanguineo exGlicose = fachadaExame.criarExameSanguineo(
                paciente1, medico1, Convenio.AMAIS, LocalDate.now(), Prioridade.ROTINA, geradorTexto
        );
        exGlicose.addIndicador(new Indicador("GLICOSE", 83.0, UnidadeMedida.MG_DL));
        exGlicose.addIndicador(new Indicador("CREATININA", 1.02, UnidadeMedida.MG_DL));
        exGlicose.setPrecoBase(60.0);
        ValidadorExame valSang = new ValidadorSanguineoBase()
                .encadear(new ValidadorGlicose())
                .encadear(new ValidadorCreatinina());
        exGlicose.setCadeiaValidador(valSang);

        ExameRaioX exRaioX = fachadaExame.criarExameRaioX(
                paciente2, medico2, Convenio.PLANO_TOP, LocalDate.now(), Prioridade.URGENTE, geradorHTML
        );
        exRaioX.setCaminhoImagem("/imagens/torax-001.png");
        exRaioX.setPrecoBase(140.0);
        ValidadorExame valImagemRX = new ValidadorImagemRaioX().encadear(new ValidadorAssinaturaRaioX());
        exRaioX.setCadeiaValidador(valImagemRX);

        ExameRessonancia exResson = fachadaExame.criarExameRessonancia(
                paciente3, medico3, Convenio.SEM_CONVENIO, LocalDate.now(), Prioridade.POUCO_URGENTE, geradorPDF
        );
        exResson.setDescricao("Dor no joelho direito há 3 semanas.");
        exResson.setProtocolo("RM de joelho");
        exResson.setUsoContraste(true);
        exResson.setTipoContraste("Gadolínio");
        exResson.setDosagemContraste("10 ml");
        exResson.setPossuiMarcapasso(false);
        exResson.setPossuiImplantes(false);
        exResson.setPrecoBase(780.0);
        ValidadorExame valResson = new ValidadorDescricaoRessonancia()
                .encadear(new ValidadorAssinaturaRessonancia())
                .encadear(new ValidadorProtocoloRessonancia())
                .encadear(new ValidadorContrasteRessonancia())
                .encadear(new ValidadorMarcapassoRessonancia());
        exResson.setCadeiaValidador(valResson);

        // === Processamento dos exames ===
        while(!fachadaExame.filaVazia()){
            Exame exame = fachadaExame.processarProximo();

            // Validação
            if(exame.getCadeiaValidador() != null){
                try{
                    exame.getCadeiaValidador().validar(exame);
                    System.out.println("[Validação] Exame #" + exame.getNumeroExame() + " validado!");
                }catch(IllegalArgumentException e){
                    System.out.println("[Validação] Exame #" + exame.getNumeroExame() + " falhou: " + e.getMessage());
                    continue;
                }
            }

            // Emissão de laudo (log dentro do estado)
            exame.setEstadoAtual(new EstadoEmAnalise());
            exame.emitirLaudo();

            // Pagamento
            double precoFinal = fachadaPagamento.calcular(exame);
            System.out.printf("[Pagamento] Exame #%d - Final: %.2f%n", exame.getNumeroExame(), precoFinal);
        }

        System.out.println("Processamento concluído.");
    }
}
