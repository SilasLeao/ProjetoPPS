# Projeto-PPS
Projeto IFPB - Padrões de Projeto de Sistemas

---

## Grupo:
- João Vittor Pereira Menezes
- Silas Leão Rocha Albuquerque
- Kauã Victor Gomes Paiva


---
![padroes drawio](https://github.com/user-attachments/assets/fe6498bf-5722-4754-99e4-a596cc7ed9c0)


- Link para melhor visualização:  https://app.diagrams.net/#G1GZlBjgNTW3PGSctl2otyRjK1lvXn3RKa#%7B%22pageId%22%3A%22C5RBs43oDa-KdzZeNtuy%22%7D



## Padrões Utilizados:

### Bridge:

![image](https://github.com/user-attachments/assets/3c4c417d-2e72-477d-9c81-306ba74c8cd9)



**R3** - Emitir laudo dos seguintes exames: Sanguíneo, Raio-X e Ressonância Magnética. Outros tipos de exames poderão surgir no futuro, como por exemplo a Tomografia. Os novos tipos de exames a serem adicionados não devem impactar o funcionamento do código já existente. Em um exame sanguíneo, pode ser pedido: hemograma completo, colesterol total, colesterol LDL, colesterol HDL, glicose, creatinina, ureia, triglicerídio, ácido úrico, etc.)


**R4** - Gerar laudos para diferentes exames, inicialmente nos seguintes formatos: texto puro, HTML e PDF. Outros tipos de formato poderão surgir no futuro, como por exemplo o JSON. Os novos formatos de exibição de laudos de exames a serem adicionados não devem impactar o funcionamento do código já existente. Qualquer tipo de laudo deve ser gerado em seu formato específico, inclusive em PDF (utilize a API de sua preferência).

**A Solução com Bridge**  
O padrão Bridge resolve isso desacoplando a abstração (o conceito do Exame) da sua implementação (o GeradorDeLaudo que formata a saída).

Criamos duas hierarquias de classes independentes:
- Hierarquia de Exames (Abstração): Exame -> Sanguineo, Raio-X, etc.
- Hierarquia de Geradores (Implementação): GeradorDeLaudo -> GeradorLaudoTexto, GeradorLaudoHTML, etc.

A "ponte" é a referência que um objeto Exame tem para um objeto GeradorDeLaudo, delegando a ele todo o trabalho de formatação.

**Como a Extensibilidade é Garantida**  
Para adicionar um novo exame (R3): basta criar uma nova classe que herda de Exame. Ela automaticamente funcionará com todos os formatos já existentes.

Para adicionar um novo formato (R4): basta criar uma nova classe que implementa GeradorDeLaudo. Todos os exames existentes poderão gerar laudos neste novo formato, sem qualquer modificação.

---

### Chain of Responsibility:

![image](https://github.com/user-attachments/assets/792b257b-bfcb-462e-98f3-993afbbe1f80)

 

**R5** - Adicionar as regras de validação de cada exame, de maneira extensível. Observar alguns exemplos apresentados na subseção de laudo. Novas validações estão livres no escopo deste projeto, desde que sejam coerentes.

Foi usado esse padrão nesse requisito de validação porque cada exame tem um validador específico, então há a classe abstrata `ValidadorExame` que tem a variável de instância para cada tipo de validador e seus métodos que serão utilizados pelas classes concretas: o `pmo`, que é padrão para todos, e o método `processar()`, que terá uma lógica específica em cada.
### Observer:


![image](https://github.com/user-attachments/assets/3782010c-d673-459d-83e1-fd5df875d77c)


**R6** - Notificar o paciente quando um laudo for emitido (inserido no sistema), por e-mail. Outros mecanismos de notificação serão adicionados futuramente (e.g. SMS ou Telegram, caso seja de uso gratuito para envio de mensagens) e não devem impactar o funcionamento do código já existente. Qualquer tipo de notificação deve ser gerado de maneira real.



A classe `GeradorDeLaudo` (classe Publicadora) é responsável por gerenciar a lista de assinantes interessados em receber notificações quando um laudo é gerado. Ela mantém a coleção `assinantes` que é um  `Map<String, ObserverNotificacao>`, onde a chave representa o identificador do assinante e o valor é a implementação concreta de `ObserverNotificacao`. Sempre que um exame é processado e um laudo é gerado, o `GeradorDeLaudo` invoca o método `notificar`, acionando todos os observadores cadastrados. Esses observadores são classes concretas que implementam a interface `ObserverNotificacao`, como `NotificadorSMS` e `NotificadorEmail`, cada um responsável por enviar a mensagem pelo canal apropriado.

---

### Strategy:

![image](https://github.com/user-attachments/assets/1bddde7a-ee6c-406b-9d67-9b703aad050f)


**R7** - O sistema deve permitir a realização de descontos para o custo de exames conforme política definida pelo laboratório. Podem ser concedidos, por exemplo,  descontos para convênio (15%) e desconto para idoso (8%). Os percentuais podem ser modificados. Outros tipos de descontos poderão surgir no futuro, como por exemplo, no mês de campanha do “outubro rosa” (prevenção de câncer de mama). Cada equipe deve planejar sua estratégia de como aplicar os descontos.

Para isso, foi utilizado o padrão Strategy, implementado na classe `CalculadoraPreco` (Contexto). Essa classe mantém uma coleção de estratégias de desconto (descontos) e é responsável por adicionar novas estratégias por meio do método `adicionar`, além de calcular o preço final do exame através do método `calcular`, que aplica todos os descontos definidos.

A interface `Desconto` representa a Strategy, definindo o contrato comum a todas as estratégias, com os métodos `aplicar` e `nome`. As classes concretas `DescontoConvenio`, `DescontoIdoso` e `DescontoCampanha` implementam a interface e encapsulam as regras específicas de cada tipo de desconto.

Esse desenho garante flexibilidade e aderência ao princípio Open/Closed, pois a cada novo tipo de desconto basta criar uma nova implementação de `Desconto`, sem alterar o funcionamento da `CalculadoraPreco` ou das classes já existentes.

---

### Requisito sem padrão de projeto:

**R8** – Implementar a solução de priorização de exames usando fila de prioridade. As regras já foram indicadas anteriormente e considerar-se-á as prioridades URGENTE, POUCO URGENTE e ROTINA.

Para esse requisito, não julgamos necessário o uso de um padrão de projeto, pois a estrutura de dados fila já pode ser suficiente para implementar o requisito.

---

### Facade:

![image](https://github.com/user-attachments/assets/7c4079ff-ea3e-4da5-8822-b1324fd9a54a)


**R9** – Implementar o programa principal que simule a execução da aplicação e atendimento de todos os requisitos funcionais.

Para a implementação do programa principal, foi utilizada a classe `Main`, que atua como ponto de entrada da aplicação e representa uma fachada central para o sistema. Através dela, o uso dos subsistemas é simplificado, já que os detalhes internos ficam encapsulados por meio das fachadas específicas: `FachadaPaciente`, `FachadaMedico`, `FachadaExame` e `FachadaPagamento`.

---

### State:

![image](https://github.com/user-attachments/assets/e9c6ce36-9fab-44b3-a6ca-e6e2d97d78e7)


**R10** - Adicione um requisito funcional a mais, dentro do escopo do problema, que justifique a inclusão de um padrão de projeto adicional (que antes estivesse fora dos requisitos).

Como requisito bônus, foi adicionado o Gerenciamento do Ciclo de Vida do Exame utilizando o padrão State. Nesse requisito, o Contexto é a classe `Exame` (já definida no padrão Bridge), enquanto a interface `EstadoExame` representa o contrato do padrão State, com os métodos `validar`, `gerarLaudo` e `nome`.

As implementações concretas de `EstadoExame` são:

`EstadoSolicitado`: representa a situação em que o exame foi solicitado e aguarda processamento.

`EstadoEmAnalise`: representa o momento em que o exame está sendo analisado.

`EstadoLaudoPronto`: indica que o exame foi finalizado e já possui laudo pronto para o paciente.

Esse modelo garante que cada estado do exame encapsule seu próprio comportamento, eliminando condicionais espalhadas pelo código e centralizando a lógica em classes específicas. Além disso, o princípio Open/Closed é atendido, pois novos estados podem ser adicionados ou removidos sem impactar as classes já existentes.
