package br.com.stdiagnosticos;

import br.com.stdiagnosticos.common.Convenio;
import br.com.stdiagnosticos.common.Prioridade;
import br.com.stdiagnosticos.common.UnidadeMedida;
import br.com.stdiagnosticos.exame.*;
import br.com.stdiagnosticos.laudo.*;
import br.com.stdiagnosticos.modelos.Medico;
import br.com.stdiagnosticos.modelos.Paciente;
import br.com.stdiagnosticos.notificacao.*;
import br.com.stdiagnosticos.pagamento.*;
import br.com.stdiagnosticos.prioridade.FilaPrioridadeExames;
import br.com.stdiagnosticos.validacao.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // === Geradores de laudo (Bridge + Template) ===
        GeradorDeLaudo geradorTexto = new GeradorLaudoTexto();
        GeradorDeLaudo geradorHTML  = new GeradorLaudoHTML();
        GeradorDeLaudo geradorPDF   = new GeradorLaudoPDF(); // mock interno de PDF

        // === Notificadores (Observer) ===
        ObservadorNotificacao email = new NotificadorEmail();
        ObservadorNotificacao sms   = new NotificadorSMS();

        // === Estratégias de desconto (Strategy) ===
        Desconto descConvenio = new DescontoConvenio(0.15);
        Desconto descIdoso    = new DescontoIdoso(0.08);
        Desconto descCampanha = new DescontoCampanha("Novembro Azul", 0.10);

        CalculadoraPreco calcPreco = new CalculadoraPreco()
                .adicionar(descConvenio)
                .adicionar(descIdoso)
                .adicionar(descCampanha);

        // === Fila de prioridade ===
        FilaPrioridadeExames fila = new FilaPrioridadeExames();

        Paciente paciente1 = new Paciente("P001", "Carlos Silva", "21/09/1987", "123.456.789-00", "carlos.silva@email.com", "+5583999999999");
        paciente1.adicionarNotificador(email);

        Paciente paciente2 = new Paciente("P002", "Mariana Souza", "03/02/1998", "987.654.321-00", "mariana.souza@email.com", "+5583988888888");
        paciente2.adicionarNotificador(email);

        Paciente paciente3 = new Paciente("P003", "Lucas Pereira", "15/11/1976", "456.789.123-00", "lucas.pereira@email.com", "+5583977777777");
        paciente3.adicionarNotificador(email);
        paciente3.adicionarNotificador(sms);

        Medico medico1 = new Medico("M001", "Ana Torres", "CRM12345", "Cardiologia", "123.456.789-01", "ana.torres@email.com");
        Medico medico2 = new Medico("M002", "Bruno Lima", "CRM67890", "Ortopedia", "987.654.321-02", "bruno.lima@email.com");
        Medico medico3 = new Medico("M003", "Carla Mendes", "CRM54321", "Pediatria", "456.789.123-03", "carla.mendes@email.com");

        // === Exame Sanguíneo: Glicose ===
        ExameSanguineo exGlicose = new ExameSanguineo(paciente1, medico1, Convenio.AMAIS, LocalDate.now(), Prioridade.ROTINA, geradorTexto);
        exGlicose.addIndicador(new Indicador("GLICOSE", 83.0, UnidadeMedida.MG_DL));
        exGlicose.setPrecoBase(60.0);

        // Validação chain sanguíneo
        ValidadorExame valSang = new ValidadorSanguineoBase()
                .encadear(new ValidadorGlicose())
                .encadear(new ValidadorCreatinina()); // será ignorado se não houver indicador CRETININA
        exGlicose.setCadeiaValidador(valSang);

        // === Exame Raio-X ===
        ExameRaioX exRaioX = new ExameRaioX(paciente2, medico2, Convenio.PLANO_TOP, LocalDate.now(), Prioridade.URGENTE, geradorHTML);
        exRaioX.setCaminhoImagem("/imagens/torax-001.png"); // simulado
        exRaioX.setPrecoBase(140.0);
        exRaioX.setCadeiaValidador(new ValidadorRaioXAssinatura());

        // === Exame Ressonância ===
        ExameRessonancia exResson = new ExameRessonancia(paciente3, medico3, Convenio.SEM_CONVENIO, LocalDate.now(), Prioridade.POUCO_URGENTE, geradorPDF);
        exResson.setDescricao("Dor no joelho direito há 3 semanas.");
        exResson.setProtocolo("RM de joelho");
        exResson.setUsoContraste(true);
        exResson.setTipoContraste("Gadolínio");
        exResson.setDosagemContraste("10 ml");
        exResson.setPossuiMarcapasso(false);
        exResson.setPossuiImplantes(false);
        exResson.setPrecoBase(780.0);
        exResson.setCadeiaValidador(new ValidadorRessonanciaRegras());

        // === Inserção na fila de prioridade ===
        fila.inserir(exGlicose);   // ROTINA → fim
        fila.inserir(exRaioX);     // URGENTE → início
        fila.inserir(exResson);    // POUCO_URGENTE → após último URGENTE

        // === Simulação de processamento (R9) ===
        while (!fila.vazia()) {
            Exame exame = fila.retirar(); // respeita a regra de prioridade

            // 1) Validação (Chain)
            exame.validar();

            // 2) Gera Laudo (Bridge + Template) -> Observer dispara automaticamente
            exame.emitirLaudo();
            // 3) Calcula preço com descontos (Strategy)
            double precoFinal = calcPreco.calcular(exame.getPrecoBase(), exame);
            System.out.printf("[Pagamento] Exame #%d - Base: R$ %.2f, Final: R$ %.2f%n",
                    exame.getNumeroExame(), exame.getPrecoBase(), precoFinal);


            System.out.println("-------------------------------------------------------");
        }

        System.out.println("Processamento concluído.");
    }
}
