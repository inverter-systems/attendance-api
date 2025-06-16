# 🕒 Attendance - Sistema de Registro de Ponto Eletrônico (SaaS) com Mensageria

**Attendance** é uma plataforma SaaS para controle e registro de ponto eletrônico (REP-P), em conformidade com legislações **brasileiras (Portaria 671/2021)**, **americanas (FLSA)** e **europeias (Diretiva 2003/88/CE)**. Desenvolvido com arquitetura moderna, segura, multi-cloud e escalável, o sistema integra mensageria, serviços serverless e banco de dados distribuído utilizando exclusivamente serviços gratuitos na fase inicial.

## ✨ Visão Geral

O sistema atende empresas que precisam controlar a jornada de trabalho de seus colaboradores. Com base legal e tecnologia de ponta, oferece:
- Registro seguro e auditável de ponto.
- Geração de relatórios e exportações compatíveis com a legislação.
- Painel administrativo em tempo real.
- Operação multi-regional em nuvens públicas gratuitas (Google, AWS, Azure, Oracle).

## 🧱 Arquitetura e Tecnologias

### 📌 Tecnologias
- **Frontend**: Angular 19
- **Backend**:
  - `Spring Boot` com `Spring Security` → Gerenciamento de autenticação/autorização, perfis de acesso, controle de sessão, proteção CSRF, CORS, JWT.
  - `Go` → Endpoints de alta performance para registro de ponto.
  - `Python` → Funções serverless para relatórios e exportações.
- **Mensageria**: `RabbitMQ`, `Kafka` ou `Google Pub/Sub` para integração desacoplada entre serviços.
- **API Gateway**: Controle de entrada com AWS API Gateway / Google API Gateway / Azure APIM.
- **Database**: `Firestore`, `DynamoDB`, `Autonomous DB` – uso gratuito com replicação.

### 🔐 Segurança (com Spring Security)
- **Autenticação via Firebase Auth e Spring Security JWT**
- **Criptografia de senhas e tokens**
- **Perfis (Admin, Supervisor, Funcionário)**
- **Controle de sessões, permissões e acesso baseado em roles**
- **CORS configurado para comunicação segura com frontend Angular**
- **Camada de segurança desacoplada com mensageria assíncrona protegida**

### ☁️ Multi-cloud
| Cloud         | Uso Principal             | Recursos Utilizados                             |
|---------------|---------------------------|--------------------------------------------------|
| Google Cloud  | Auth, Mensageria          | Firebase Auth, Cloud Functions, Pub/Sub         |
| AWS           | Registro de ponto         | Lambda, DynamoDB, API Gateway, SQS              |
| Azure         | Relatórios, Storage       | Azure Functions, Blob Storage, API Management   |
| Oracle Cloud  | Painel Admin, Banco       | Oracle Functions, Autonomous DB                 |

## 🔐 Conformidade Legal

### 🇧🇷 Brasil
- Portaria 671/2021 (MTP)
- Registro inviolável com logs e audit trails
- Exportação AFD e AFDT

### 🇺🇸 Estados Unidos
- Fair Labor Standards Act (FLSA)
- Retenção mínima de 3 anos
- Controle preciso de horas extras

### 🇪🇺 Europa
- Diretiva 2003/88/CE
- Descanso semanal, limites diários
- GDPR: proteção de dados pessoais

## 🚧 MVP: Roadmap de Desenvolvimento

### Semana 1–2
- Estrutura dos repositórios (Spring, Go, Python)
- Login e autenticação Firebase + Spring Security
- Setup do frontend Angular 19
- Primeira fila de mensageria: Kafka ou Pub/Sub

### Semana 3–4
- Registro de ponto (Go + DynamoDB)
- Painel administrativo básico (Angular)
- Eventual consistency entre serviços via mensagens

### Semana 5–6
- Geração de relatórios (PDF/CSV com Python serverless)
- Exportação para folha via mensagens
- Webhook/API para integração externa

### Semana 7–8
- Validações legais regionais
- Logging seguro e trilhas de auditoria
- Testes de escalabilidade e fallback multi-cloud

## 📊 Escalabilidade
- Serverless e bancos distribuídos gratuitos (Firestore, DynamoDB, Autonomous)
- Mensageria para escalabilidade horizontal
- Quando necessário, migração automática para planos pagos de acordo com a demanda

## 💸 Modelo de Negócio
- **Grátis**: até 5 funcionários, 1 filial
- **Assinaturas**: baseadas em quantidade de funcionários e integrações
- **Parcerias**: contadores, consultorias RH, empresas de software folha de pagamento

## 🧰 Configuração do Ambiente

Crie um `.env` com as seguintes variáveis:

```env
# Firebase
FIREBASE_API_KEY=...
FIREBASE_AUTH_DOMAIN=...

# Spring Security
JWT_SECRET=...
TOKEN_EXPIRATION=...

# AWS
AWS_ACCESS_KEY_ID=...
AWS_SECRET_ACCESS_KEY=...

# Google
GOOGLE_PROJECT_ID=...
```

## 🖼️ Diagrama de Arquitetura

![Diagrama de Arquitetura](https://yourimageurl.com/diagrama.png)

> Diagrama ilustrando a arquitetura com microsserviços, mensageria, bancos e autenticação.

## 📬 Contato

📧 [seu-email@dominio.com](mailto:seu-email@dominio.com)

**Feito com ☕ Java, 🐍 Python, 🦫 Go e muito ⚙️ distribuído.**