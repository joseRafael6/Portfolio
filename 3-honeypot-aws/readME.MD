# Projeto: Honeypot Simples na AWS

Este repositório documenta a implementação de um honeypot de baixa interação na nuvem da Amazon Web Services (AWS) para fins educacionais. O objetivo é registrar e analisar tentativas de acesso não autorizado provenientes de bots e scanners automatizados na internet.

### Conceito
Um **honeypot** é um sistema de computador sacrificial que atua como uma isca para atrair cibercriminosos. Ao simular vulnerabilidades, podemos observar as técnicas e ferramentas utilizadas pelos atacantes em um ambiente seguro e controlado.

### Tecnologias Utilizadas
- **Amazon EC2 (Elastic Compute Cloud):** Para provisionar o servidor virtual que atuará como isca.
- **AWS Security Groups:** Para configurar o firewall da instância, expondo portas comumente atacadas.
- **Amazon CloudWatch:** Para centralizar, monitorar e analisar os logs de autenticação do servidor.

### Arquitetura da Solução
1.  **Instância EC2:** Uma instância `t2.micro` com Amazon Linux é lançada.
2.  **Firewall (Security Group):** Portas como **SSH (22)**, **HTTP (80)** e **Telnet (23)** são deliberadamente abertas para `0.0.0.0/0` (toda a internet) para atrair tráfego.
3.  **Coleta de Logs:** O agente do CloudWatch é instalado na instância EC2 para enviar os logs de tentativas de autenticação (`/var/log/secure`) para o serviço Amazon CloudWatch Logs.
4.  **Análise:** No CloudWatch, é possível criar painéis e alarmes para visualizar e ser notificado sobre a atividade maliciosa, como ataques de força bruta de SSH.

### Passo a Passo da Implementação
1.  **Lançamento da Instância EC2:**
    -   Acesse o console da AWS e inicie uma instância EC2.
    -   Selecione a AMI "Amazon Linux 2".
    -   Escolha o tipo de instância `t2.micro` (elegível para o nível gratuito).
    -   Crie um novo par de chaves e salve o arquivo `.pem` em um local seguro.

2.  **Configuração do Security Group:**
    -   Crie um novo Security Group.
    -   Adicione regras de entrada (Inbound Rules) para permitir tráfego de `Qualquer Lugar (0.0.0.0/0)` nas seguintes portas:
        -   `SSH (TCP, porta 22)`
        -   `HTTP (TCP, porta 80)`
        -   `TELNET (TCP, porta 23)`
    
    > **AVISO DE SEGURANÇA:** Esta configuração é intencionalmente insegura e só deve ser aplicada a uma instância isolada, dedicada exclusivamente para este estudo. Não utilize esta configuração em ambientes de produção.

3.  **Instalação do Agente CloudWatch:**
    -   Conecte-se à sua instância via SSH usando o par de chaves.
    -   Siga a [documentação oficial da AWS](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/QuickStartEC2Instance.html) para instalar e configurar o agente do CloudWatch para monitorar o arquivo `/var/log/secure`.

4.  **Análise dos Logs:**
    -   No console da AWS, navegue até CloudWatch > Log Groups.
    -   Selecione o grupo de logs criado (ex: `/var/log/secure`).
    -   Observe o fluxo de eventos. Em poucas horas, você começará a ver logs de várias partes do mundo tentando adivinhar seu nome de usuário e senha via SSH.

### Resultados Observados
-   **Ataques de Força Bruta:** Múltiplas tentativas de login SSH falhadas de uma variedade de endereços IP.
-   **Varredura de Portas:** Tráfego de entrada em outras portas abertas sendo registrado.
-   **Geolocalização dos Atacantes:** Usando ferramentas de análise de IP, foi possível identificar que as tentativas de ataque se originaram de diversos países, demonstrando a natureza global das ameaças automatizadas.

Este projeto prático oferece uma visão valiosa sobre a paisagem de ameaças na internet e serve como uma excelente introdução aos conceitos de segurança defensiva e monitoramento em nuvem.
