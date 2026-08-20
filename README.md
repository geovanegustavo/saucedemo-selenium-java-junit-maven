# saucedemo-selenium-java-junit-maven

Projeto de testes automatizados web utilizando **Selenium WebDriver + Java + JUnit 5 + Maven**, seguindo o padrão **Page Object Model (POM)**.
A aplicação alvo de teste é o site [SauceDemo](https://www.saucedemo.com/).

---

## 📋 Sumário

- [🛠️ Preparação do Ambiente](#-preparação-do-ambiente)
  - [📊 1. Resumo da Ordem de Instalação](#1-resumo-da-ordem-de-instalação)
  - [☕ 2. Instalar o JDK (Java Development Kit)](#2-instalar-o-jdk-java-development-kit)
  - [📦 3. Instalar o Apache Maven](#3-instalar-o-apache-maven)
  - [💻 4. Instalar o Visual Studio Code](#4-instalar-o-visual-studio-code)
  - [🧩 5. Instalar Extensões do VSCode](#5-instalar-extensões-do-vscode)
  - [✅ 6. Verificar o Ambiente](#6-verificar-o-ambiente)
  - [🚀 7. Criar o Projeto no VSCode](#7-criar-o-projeto-no-vscode)
  - [🎬 8. Instalar o Katalon Recorder (Opcional)](#8-instalar-o-katalon-recorder-opcional)
  - [📝 9. Instalar o TestCase Studio (Opcional)](#9-instalar-o-testcase-studio-opcional)
  - [🔍 10. Instalar o SelectorsHub (Opcional)](#10-instalar-o-selectorshub-opcional)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [⚙️ Configuração do pom.xml](#️-configuração-do-pomxml)
  - [1. Cabeçalho XML e declaração do projeto](#1-cabeçalho-xml-e-declaração-do-projeto)
  - [2. Identificação do Projeto](#2-identificação-do-projeto)
  - [3. Propriedades (Properties)](#3-propriedades-properties)
  - [4. Gerenciamento de Dependências (dependencyManagement)](#4-gerenciamento-de-dependências-dependencymanagement)
  - [5. Dependências (Dependencies)](#5-dependências-dependencies)
  - [6. Build e Plugins](#6-build-e-plugins)
- [▶️ Executar os Testes](#️-executar-os-testes)
- [🔄 CI/CD com GitHub Actions](#-cicd-com-github-actions)
- [📊 Allure Reports](#-allure-reports)

---

## 🛠️ Preparação do Ambiente

Siga a ordem abaixo para instalar e configurar todas as ferramentas necessárias na sua máquina Windows.

### 📊 1. Resumo da Ordem de Instalação

| Ordem | Ferramenta      | Justificativa                                              |
|:-----:|-----------------|------------------------------------------------------------|
| 1     | JDK 17          | Base para Java; Maven e Selenium dependem dele.            |
| 2     | Apache Maven    | Precisa do JDK instalado previamente para funcionar.       |
| 3     | VSCode          | Editor de código para manipular os arquivos do projeto.    |
| 4     | Extensões Java  | Suporte a Java, Maven e debug dentro do VSCode.           |

---

### ☕ 2. Instalar o JDK (Java Development Kit)

O Selenium e o Maven dependem do Java para funcionar. Instale o **JDK 11** ou superior.

#### Por que usar o Eclipse Temurin JDK 17 via Adoptium?

- **Organização neutra e sem fins lucrativos:** O Temurin é mantido pela [Eclipse Adoptium](https://adoptium.net/), uma organização que reúne empresas como Red Hat, IBM, Microsoft e Apple — sem vínculo com fornecedor de nuvem ou hardware.
- **Gratuito e open-source:** Diferente do Oracle JDK (que possui restrições de licença para uso comercial) e do Amazon Corretto (vinculado à AWS), o Temurin é totalmente gratuito e sem nenhuma restrição de uso.
- **LTS (Long-Term Support):** O JDK 17 é uma versão de suporte de longo prazo, garantindo atualizações de segurança e correções de bugs por anos — ideal para projetos que precisam de estabilidade.
- **Multiplataforma:** O mesmo instalador funciona em Windows, macOS e Linux, o que facilita o trabalho em equipe com ambientes diferentes.
- **Instalador `.msi` para Windows:** O pacote `.msi` configura automaticamente o `JAVA_HOME` e adiciona o Java ao `Path`, eliminando a necessidade de configurar variáveis de ambiente manualmente.

#### Passos de instalação

1. Acesse [https://adoptium.net/](https://adoptium.net/) e baixe o instalador do **Eclipse Temurin JDK 17** (LTS) para Windows (`.msi`).
2. Execute o instalador e siga o assistente. Marque a opção **"Set JAVA_HOME variable"** e selecione **"Will be installed on local hard drive"**.
3. Ao finalizar, a variável de ambiente `JAVA_HOME` será configurada automaticamente.

Após a instalação, abra o **Prompt de Comando (CMD)** e verifique:

```bash
java -version
javac -version
```

Saída esperada (versões variam conforme a versão instalada):

```
openjdk version "17.0.x" ...
```

> **Nota:** Se `JAVA_HOME` não foi configurado automaticamente, configure manualmente:
> - Pressione `Win + R`, digite `sysdm.cpl` e pressione Enter.
> - Vá em **Avançado** > **Variáveis de Ambiente**.
> - Em **Variáveis do sistema**, clique em **Novo** e adicione:
>   - Nome: `JAVA_HOME`
>   - Valor: caminho do JDK (ex: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`)
> - Edite a variável `Path` e adicione `%JAVA_HOME%\bin`.

---

### 📦 3. Instalar o Apache Maven

#### O que é o Maven?

O [Apache Maven](https://maven.apache.org/) é uma ferramenta de **automação de build** e **gerenciamento de dependências** para projetos Java. Ele resolve dois problemas fundamentais no desenvolvimento:

- **Gerenciamento de dependências:** Em vez de baixar e incluir manualmente os `.jar` do Selenium, JUnit e outras bibliotecas no projeto, você declara quais dependências deseja no arquivo `pom.xml` e o Maven baixa automaticamente as versões corretas — incluindo todas as dependências internas de cada biblioteca.
- **Ciclo de vida do build:** O Maven padroniza as etapas de compilação, execução de testes, empacotamento e geração de relatórios. Um único comando (`mvn test`) compila o código, executa todos os testes JUnit e gera um relatório de resultados.

#### Como funciona?

O Maven utiliza um arquivo chamado **`pom.xml`** (Project Object Model) como centro do projeto. Nele você define:

```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.27.0</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.4</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

O Maven identifica automaticamente todas as dependências transitivas (dependências das dependências), baixa-as e as disponibiliza para o projeto.

#### Por que não usar o Maven que vem com o VSCode?

Quando você instala a extensão **Extension Pack for Java** no VSCode, ela inclui suporte básico a Maven. No entanto, isso **não** instala o Maven como ferramenta de linha de comando. Para executar `mvn test` no terminal ou em pipelines de integração contínua (CI/CD), é necessário instalar o Maven de forma independente no sistema.

#### Passos de instalação

1. Acesse [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).
2. Baixe o arquivo `apache-maven-3.9.x-bin.zip`.
3. Extraia o conteúdo para uma pasta permanente, por exemplo: `C:\Program Files\Apache\maven`.
4. Configure a variável de ambiente `Path`:
   - Pressione `Win + R`, digite `sysdm.cpl` e pressione Enter.
   - Vá em **Avançado** > **Variáveis de Ambiente**.
   - Em **Variáveis do sistema**, edite a variável `Path` e adicione o caminho da pasta `bin` do Maven (ex: `C:\Program Files\Apache\maven\bin`).
5. Opcionalmente, crie a variável de sistema `M2_HOME` apontando para a pasta raiz do Maven (ex: `C:\Program Files\Apache\maven`).

> **`M2_HOME` vs `MAVEN_HOME`:** O nome original e documentado pelo Maven é `M2_HOME` (desde a versão 1.x). Os scripts internos do Maven (`mvn.cmd`) historicamente buscam essa variável. A partir do Maven 3.9.x, passou-se a aceitar também `MAVEN_HOME` como alternativa mais legível. Para máxima compatibilidade com todas as versões do Maven, use `M2_HOME`.

Após a instalação, verifique no CMD:

```bash
mvn -version
```

Saída esperada:

```
Apache Maven 3.9.x
Maven home: C:\Program Files\Apache\maven
Java version: 17.0.x, vendor: Eclipse Adoptium
```

> **Importante:** A instalação do Maven **deve** ser feita **depois** do JDK, pois o Maven precisa encontrar o `JAVA_HOME` para funcionar corretamente.

---

### 💻 4. Instalar o Visual Studio Code

1. Acesse [https://code.visualstudio.com/download](https://code.visualstudio.com/download).
2. Baixe o instalador para Windows.
3. Execute o instalador. Durante a instalação, marque as opções:
   - **"Add 'Open with Code' action to Windows Explorer file context menu"**
   - **"Add to PATH"** (adicione ao PATH)

Após a instalação, abra o VSCode para verificar se está funcionando.

---

### 🧩 5. Instalar Extensões do VSCode

Abra o VSCode e instale as seguintes extensões via **Extensions** (`Ctrl + Shift + X`):

1. **Extension Pack for Java** (`vscjava.vscode-java-pack`) — Pacote completo da Microsoft que inclui: linguagem Java, debugger, Maven for Java e suporte a testes.
2. **Language Support for Java™ by Red Hat** (`redhat.java`) — Autocomplete, navegação e erros em tempo real.
3. **Maven for Java** (`vscjava.vscode-maven`) — Suporte a projetos Maven dentro do VSCode.
4. **Test Runner for Java** (`vscjava.vscode-java-test`) — Permite **executar e depurar testes JUnit** diretamente no VSCode, com interface visual para rodar individualmente cada `@Test` e visualizar resultados na aba **Test Explorer**.

> As extensões 2, 3 e 4 são instaladas automaticamente ao instalar o **Extension Pack for Java**. No entanto, é importante confirmar que o **Test Runner for Java** está habilitado, pois é ele que fornece o suporte ao JUnit dentro do editor.

Após instalar, reinicie o VSCode. Aguarde o **"Java Language Server"** inicializar (ícone de engrenagem no canto inferior esquerdo).

---

### ✅ 6. Verificar o Ambiente

Abra o **Terminal integrado do VSCode** (`Ctrl + `` ` ``) ou o CMD e execute os comandos abaixo para validar que tudo está configurado:

```bash
java -version
javac -version
mvn -version
```

Todos os três comandos devem retornar informações de versão sem erros.

---

### 🚀 7. Criar o Projeto no VSCode

Após verificar o ambiente, crie o projeto Maven diretamente no VSCode:

1. Abra o VSCode.
2. Pressione `Ctrl + Shift + P` para abrir o **Command Palette**.
3. Digite **"Maven"** e selecione **"Maven: Create Maven Project"**.
4. Na lista de arquiteturas, selecione **"maven-archetype-quickstart"**.
5. Pressione **Enter** para usar a versão do arquétipo mais recente.
6. Informe os dados do projeto:
   - **GroupId:** `com.saucedemo`
   - **ArtifactId:** `saucedemo-selenium-java-junit-maven`
   - **Version:** `1.0-SNAPSHOT` (padrão)
7. Selecione uma pasta local para o projeto ser criado (ex: `C:\qa-automation\web-testing\selenium\`).
8. O VSCode irá gerar a estrutura do projeto Maven automaticamente.

Após a criação, o VSCode abrirá a pasta do projeto. Aguarde o **Java Language Server** reconhecer o projeto (ícone de engrenagem no canto inferior esquerdo) e, em seguida, substitua o conteúdo gerado pelos arquivos do projeto (ver seção abaixo).

> **Alternativa via terminal:** Caso prefira criar o projeto sem usar o Command Palette, abra o terminal na pasta desejada e execute:
> ```bash
> mvn archetype:generate -DgroupId=com.saucedemo -DartifactId=saucedemo-selenium-java-junit-maven -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
> ```

---

### 🎬 8. Instalar o Katalon Recorder (Opcional)

> **Nota:** Esta etapa é **opcional**. O Katalon Recorder é uma ferramenta de **auxílio** para quem está começando — não é necessária para o funcionamento do projeto. Ele pode ser útil para gerar scripts iniciais e identificar localizadores, mas todos os testes podem ser escritos manualmente sem ele.

O [Katalon Recorder](https://www.katalon.com/katalon-recorder-ide/) é uma extensão gratuita para Chrome e Firefox que grava as ações do usuário em um site e exporta automaticamente scripts de teste em Selenium (Java, Python, Ruby, etc.). Ele pode auxiliar em:

- **Gerar scripts iniciais rapidamente** — em vez de escrever o código de teste do zero, grave a navegação no site e exporte o script gerado.
- **Identificar localizadores (locators)** — o Katalon Recorder mostra os seletores CSS e XPath utilizados em cada elemento, facilitando a criação dos Page Objects.
- **Aprender a estrutura de um teste Selenium** — ao analisar o código exportado, é possível entender como o Selenium WebDriver funciona na prática.

#### Passos de instalação

1. Acesse a página oficial do Katalon Recorder:
   - **Chrome:** [Katalon Recorder no Chrome Web Store](https://chromewebstore.google.com/detail/katalon-recorder/lhdoppojpmngadmnindnejagiedpbmnf)
   - **Firefox:** [Katalon Recorder no Firefox Add-ons](https://addons.mozilla.org/en-US/firefox/addon/katalon-recorder/)
2. Clique em **"Add to Chrome"** (ou **"Add to Firefox"**).
3. Confirme a instalação clicando em **"Add extension"**.
4. O ícone do Katalon Recorder aparecerá na barra de extensões do navegador.

#### Como utilizar

1. Abra o navegador e acesse o site alvo de teste (ex: [https://www.saucedemo.com/](https://www.saucedemo.com/)).
2. Clique no ícone do **Katalon Recorder** na barra de extensões.
3. Clique no botão **"Record"** para iniciar a gravação.
4. Realize as ações desejadas no site (preencher campos, clicar em botões, navegar entre páginas).
5. Clique no botão **"Stop"** para finalizar a gravação.
6. Clique em **"Show"** para visualizar os passos gravados.
7. Clique em **"Export"** e selecione o formato **"Selenium Java JUnit"**.
8. O script será gerado. Copie o código e utilize como referência para criar as classes Page Object e os testes no projeto.

#### Exemplo de script exportado pelo Katalon Recorder

```java
// Script gerado pelo Katalon Recorder (referência — NÃO usar diretamente)
// O código abaixo serve como base para criar os Page Objects do projeto.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginScript {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        driver.quit();
    }
}
```

> **Importante:** O código exportado pelo Katalon Recorder é apenas uma **referência**. Para este projeto, ele deve ser adaptado para o padrão **Page Object Model**: os localizadores e ações ficam nas classes de página (`LoginPage.java`, etc.) e os testes ficam nas classes de teste (`LoginPageTest.java`, etc.) utilizando JUnit 5.

---

### 📝 9. Instalar o TestCase Studio (Opcional)

> **Nota:** Esta etapa é **opcional**. O TestCase Studio é uma ferramenta de **auxílio** complementar ao Katalon Recorder — não é necessária para o funcionamento do projeto. Ele pode ser útil para gerar documentação de testes em linguagem natural e capturar prints de cada etapa.

O [TestCase Studio](https://selectorshub.com/testcase-studio/) é uma extensão gratuita para Chrome e Firefox que grava as ações do usuário em um site e converte em **frases em inglês** (plain English), gerando automaticamente screenshots, XPath e CSS selectors para cada passo. Diferente do Katalon Recorder (que exporta código Selenium), o TestCase Studio foca em **documentação de testes manuais** e **identificação de localizadores**.

#### Diferença entre Katalon Recorder e TestCase Studio

| | Katalon Recorder | TestCase Studio |
|---|---|---|
| **Foco principal** | Gerar código de automação Selenium | Gerar documentação de testes em linguagem natural |
| **Saída** | Scripts Java, Python, Ruby, etc. | Texto em inglês com screenshots e XPath/CSS selectors |
| **Uso ideal** | Criar scripts iniciais de automação | Documentar testes manuais, identificar localizadores, criar bugs com evidências |
| **Replay (reexecução)** | Sim | Sim (versão gratuita) |

#### Passos de instalação

1. Acesse a página oficial do TestCase Studio:
   - **Chrome:** [TestCase Studio no Chrome Web Store](https://chromewebstore.google.com/detail/testcase-studio/loopjjegnlccnhgfehekecpanpmielcj)
   - **Firefox:** [TestCase Studio no Firefox Add-ons](https://addons.mozilla.org/en-US/firefox/addon/testcase-studio/)
2. Clique em **"Add to Chrome"** (ou **"Add to Firefox"**).
3. Confirme a instalação clicando em **"Add extension"**.
4. Clique no ícone de extensões (peça de quebra-cabeça) na barra do navegador e **fixe (pin)** o TestCase Studio na barra de ferramentas para acesso rápido.

#### Como utilizar

1. Abra o navegador e acesse o site alvo de teste (ex: [https://www.saucedemo.com/](https://www.saucedemo.com/)).
2. Clique no ícone do **TestCase Studio** na barra de ferramentas. A janela de gravação será aberta.
3. Realize as ações desejadas no site (preencher campos, clicar em botões, navegar entre páginas).
4. O TestCase Studio gravará automaticamente cada ação como uma frase em inglês, acompanhada de screenshot e localizadores (XPath, CSS Selector).
5. Para finalizar, clique no botão de **download** para exportar os passos gravados como arquivo.

#### O que o TestCase Studio gera para cada passo

| Coluna | Descrição |
|--------|-----------|
| **Step No** | Número sequencial do passo |
| **Sentence** | Ação descrita em linguagem natural (ex: "User enters 'standard_user' in username field") |
| **Command** | Comando Selenium correspondente (ex: `sendKeys`, `click`) |
| **Target** | Localizador do elemento (XPath ou CSS Selector) |
| **Value** | Valor informado pelo usuário (ex: texto digitado) |
| **Screenshot** | Print da tela com a área de ação destacada |

> **Dica:** Os XPath e CSS selectors gerados pelo TestCase Studio podem ser utilizados diretamente ao criar as classes Page Object do projeto (ex: `LoginPage.java`).

---

### 🔍 10. Instalar o SelectorsHub (Opcional)

> **Nota:** Esta etapa é **opcional**. O SelectorsHub é uma ferramenta de **auxílio** para quem deseja aprofundar na criação e validação de localizadores — não é necessária para o funcionamento do projeto. Ele é especialmente útil quando os localizadores gerados pelo Katalon Recorder ou TestCase Studio não são suficientes ou quando é necessário criar localizadores mais robustos.

O [SelectorsHub](https://selectorshub.com/selectorshub/) é uma extensão gratuita para Chrome e Firefox que funciona diretamente dentro do **DevTools** do navegador. Ele gera, escreve e verifica XPath, CSS Selectors e Playwright Locators de forma inteligente, com sugestões automáticas e tratamento de erros.

#### Por que usar o SelectorsHub?

Enquanto o Katalon Recorder e o TestCase Studio **geram** localizadores automaticamente durante a gravação, o SelectorsHub permite **escrever e validar** localizadores de forma interativa — como um editor inteligente dentro do DevTools. Ele é ideal para:

- **Gerar todos os tipos de localizadores** de um elemento com um único clique (XPath relativo, CSS Selector, ID, name, etc.).
- **Validar XPath e CSS Selectors** em tempo real — pressione Enter e veja os elementos correspondentes destacados na página.
- **Detectar erros** de sintaxe em localizadores com mensagens claras do que está errado.
- **Suportar elementos complexos** como Shadow DOM, iframes, SVG e elementos dinâmicos.
- **Funcionar como editor inteligente** — ao digitar um XPath, o SelectorsHub sugere automaticamente atributos, funções e ocorrências disponíveis.

#### Passos de instalação

1. Acesse a página oficial do SelectorsHub:
   - **Chrome:** [SelectorsHub no Chrome Web Store](https://chromewebstore.google.com/detail/selectorshub/ndgimibanhlabgdgjcpbbndiehljcpfh)
   - **Firefox:** [SelectorsHub no Firefox Add-ons](https://addons.mozilla.org/en-US/firefox/addon/selectorshub/)
2. Clique em **"Add to Chrome"** (ou **"Add to Firefox"**).
3. Confirme a instalação clicando em **"Add extension"**.
4. Reinicie o navegador.

#### Como utilizar

1. Abra o navegador e acesse o site alvo de teste (ex: [https://www.saucedemo.com/](https://www.saucedemo.com/)).
2. Abra o **DevTools** pressionando `F12` ou clicando com o botão direito > **"Inspecionar"**.
3. Na barra lateral do DevTools, clique na aba **"SelectorsHub"** (localizada ao lado da aba "Elements").
4. Inspecione um elemento na página (clique com o botão direito > Inspect). O SelectorsHub mostrará automaticamente todos os tipos de localizadores disponíveis para aquele elemento.
5. Para escrever seu próprio XPath ou CSS Selector, digite na caixa de entrada do SelectorsHub — ele sugerirá atributos e funções automaticamente.
6. Pressione **Enter** para validar — os elementos correspondentes serão destacados na página.

#### Exemplo de uso no projeto

```
1. Inspecionar o campo de username no SauceDemo (id="user-name")
2. O SelectorsHub gera automaticamente:
   - Rel XPath:    //input[@id='user-name']
   - CSS Selector: #user-name
   - ID:           user-name
3. Utilizar o localizador na classe LoginPage.java:
   @FindBy(id = "user-name")
   private WebElement usernameField;
```

#### Comparativo com as demais ferramentas

| | Katalon Recorder | TestCase Studio | SelectorsHub |
|---|---|---|---|
| **Tipo** | Gravador de ações | Gravador de ações | Editor/validador de localizadores |
| **Saída principal** | Scripts Selenium (Java, etc.) | Documentação em texto + screenshots | XPath, CSS Selector, Playwright Locator |
| **Funcionamento** | Grava durante a navegação | Grava durante a navegação | Dentro do DevTools (inspeção interativa) |
| **Uso ideal** | Gerar código inicial | Documentar testes manuais | Criar e validar localizadores robustos |
| **Suporte a elementos complexos** | Limitado | Limitado | Shadow DOM, iframe, SVG, dinâmicos |

> **Dica:** As três ferramentas são complementares. Uma abordagem comum é: (1) usar o Katalon Recorder para gerar o script inicial, (2) usar o TestCase Studio para documentar os passos com screenshots, e (3) usar o SelectorsHub para refinar e validar os localizadores antes de implementar os Page Objects.

---

## 📁 Estrutura do Projeto

O projeto segue o padrão **Page Object Model (POM)**, que separa a representação das páginas do site das classes de teste. Cada página web é representada por uma classe Java que encapsula os elementos (locators) e ações (métodos) disponíveis nessa página. As classes de teste utilizam esses objetos de página, sem acessar diretamente os elementos HTML.

```
saucedemo-selenium-java-junit-maven/
├── .git/
├── .github/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/saucedemo/
│   │           ├── pages/
│   │           │   ├── InventoryPage.java
│   │           │   └── LoginPage.java
│   │           └── utils/
│   │               ├── BrowserFactory.java
│   │               ├── BrowserType.java
│   │               └── Constants.java
│   └── test/
│       ├── java/
│       │   └── com/saucedemo/tests/
│       │       ├── BaseTest.java
│       │       ├── LoginPageTest.java
│       │       └── LogoutPageTest.java
│       └── resources/
│           ├── allure.properties
│           └── logging.properties
└── target/
```

#### Descrição dos pacotes

| Pacote | Caminho | Descrição |
|--------|---------|-----------|
| `pages` | `src/main/java/.../pages/` | Page Objects — cada classe representa uma página do site com seus elementos e ações |
| `utils` | `src/main/java/.../utils/` | Utilitários — constantes, factory de navegador e enum de tipos de browser |
| `tests` | `src/test/java/.../tests/` | Classes de teste — `BaseTest` com configuração comum e testes herdam dele |
| `resources` | `src/test/resources/` | Arquivos de configuração — `logging.properties` para suprimir avisos do Selenium |

#### Descrição dos arquivos

| Arquivo | Descrição |
|---------|-----------|
| `BaseTest.java` | Classe abstrata que configura e encerra o WebDriver. Todos os testes herdam dela. |
| `LoginPageTest.java` | Teste de login positivo e negativo no SauceDemo |
| `LogoutPageTest.java` | Teste de logout no SauceDemo |
| `LoginPage.java` | Page Object da página de login — localizadores e métodos de interação |
| `InventoryPage.java` | Page Object da página de inventário — menu lateral e logout |
| `Constants.java` | Constantes centralizadas (URL, usuário, senha, navegador) |
| `BrowserType.java` | Enum com os navegadores disponíveis (CHROME, FIREFOX, EDGE, SAFARI e versões headless) |
| `BrowserFactory.java` | Factory que cria o WebDriver correto baseado no BrowserType selecionado |
| `logging.properties` | Configuração de logging para suprimir avisos do CDP do Selenium |
| `allure.properties` | Configuração do diretório de resultados do Allure |

#### Page Object Model vs pom.xml

O nome parecido gera confusão. São coisas completamente diferentes:

| | Page Object Model (POM) | pom.xml |
|---|---|---|
| **O que é** | Padrão de design / arquitetura de código | Arquivo de configuração do Maven |
| **Onde está** | Código Java (`src/main/java/.../pages/`) | Raiz do projeto (`pom.xml`) |
| **Para quê** | Organizar o código de testes: cada página web vira uma classe Java com métodos que representam as ações do usuário | Definir dependências (Selenium, JUnit), versão do Java, plugins e regras de build |
| **Quem usa** | Desenvolvedor de testes ao escrever os testes | Maven (ferramenta de build) ao compilar, testar e empacotar o projeto |
| **Analogia** | É como organizar uma receita: ingredientes separados de utensílios | É como a lista de compras: quais ingredientes precisam ser comprados |

---

## ⚙️ Configuração do pom.xml

O arquivo `pom.xml` (Project Object Model) é o centro de qualquer projeto Maven. Ele define tudo que o Maven precisa saber sobre o projeto: identificação, dependências, plugins e configurações de build.

---

### 1. Cabeçalho XML e declaração do projeto

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
```

| Tag | O que é |
|-----|---------|
| `<?xml ... ?>` | Declaração do XML — define versão (1.0) e codificação (UTF-8). |
| `<project>` | Raiz do arquivo — declara os namespaces XML (XSD) que validam o formato do pom.xml. |
| `<modelVersion>` | Versão do modelo Maven. Sempre `4.0.0` — não muda entre versões do Maven. |

---

### 2. Identificação do Projeto

```xml
    <groupId>com.saucedemo</groupId>
    <artifactId>saucedemo-selenium-java-junit-maven</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <name>saucedemo-selenium-java-junit-maven</name>
```

| Tag | O que é | Exemplo |
|-----|---------|---------|
| `<groupId>` | Identificador único da organização. Segue o padrão de domínio invertido. | `com.saucedemo` |
| `<artifactId>` | Nome único do projeto dentro do grupo. É o nome do `.jar` gerado. | `saucedemo-selenium-java-junit-maven` |
| `<version>` | Versão atual. `-SNAPSHOT` = em desenvolvimento. | `1.0-SNAPSHOT` |
| `<packaging>` | Tipo de arquivo gerado. `jar` é o padrão para projetos Java. | `jar` |
| `<name>` | Nome legível do projeto (usado em relatórios e documentação). | `saucedemo-selenium-java-junit-maven` |

> **Nota:** O sufixo `-SNAPSHOT` indica versão em desenvolvimento. Ele é removido no release (ex: `1.0`). Para projetos de estudo, é comum manter indefinidamente.

---

### 3. Propriedades (Properties)

```xml
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <selenium.version>4.27.0</selenium.version>
        <junit.version>5.11.4</junit.version>
        <allure.version>2.35.3</allure.version>
        <allure.maven.version>2.12.0</allure.maven.version>
        <aspectj.version>1.9.25</aspectj.version>
        <slf4j.version>2.0.17</slf4j.version>
        <surefire.version>3.5.2</surefire.version>
    </properties>
```

As propriedades são **variáveis** reutilizáveis. Em vez de escrever a versão em vários lugares, define-se uma vez e referencia com `${nome}`.

| Tag | O que é | Por que usar |
|-----|---------|--------------|
| `<maven.compiler.source>` | Versão do Java-fonte que o compilador deve considerar. | Define que o código usa recursos do Java 17. |
| `<maven.compiler.target>` | Versão do bytecode gerado. | Garante compatibilidade com JVMs Java 17+. |
| `<project.build.sourceEncoding>` | Codificação dos arquivos fonte. | Evita problemas com acentos e caracteres especiais. |
| `<selenium.version>` | Versão do Selenium WebDriver. | Biblioteca principal para automação web. |
| `<junit.version>` | Versão do JUnit 5. | Framework de testes. |
| `<allure.version>` | Versão do Allure BOM. | Gerencia versões dos módulos Allure automaticamente. |
| `<allure.maven.version>` | Versão do plugin Allure Maven. | Gera e serve o relatório Allure. |
| `<aspectj.version>` | Versão do AspectJ Weaver. | Suporte a anotações `@Step` e `@Attachment` do Allure. |
| `<slf4j.version>` | Versão do SLF4J Simple. | Implementação de logging para suprimir avisos do Selenium. |
| `<surefire.version>` | Versão do Maven Surefire Plugin. | Executa os testes JUnit durante `mvn test`. |

---

### 4. Gerenciamento de Dependências (dependencyManagement)

```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-bom</artifactId>
                <version>${allure.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

| Tag | O que é |
|-----|---------|
| `<dependencyManagement>` | Define versões padrão para dependências — sem baixá-las ainda. |
| `<allure-bom>` | **Bill of Materials** — garante que todos os módulos Allure usem a mesma versão. |
| `<type>pom</type>` | Indica que é um POM (arquivo de configuração), não um `.jar`. |
| `<scope>import</scope>` | Importa as versões definidas no BOM para este projeto. |

> **Por que existe?** Sem o BOM, cada módulo Allure precisaria de versão explícita. Com ele, basta definir `<allure.version>` uma vez e todas as dependências Allure herdam automaticamente.

---

### 5. Dependências (Dependencies)

```xml
    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>${slf4j.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

Cada `<dependency>` declara uma biblioteca que o projeto precisa. O Maven baixa automaticamente todas as dependências (e dependências das dependências) do repositório central.

| Dependência | O que faz | Scope |
|-------------|-----------|-------|
| `selenium-java` | Biblioteca principal para automação web — controla o navegador. | *(vazio)* — disponível em compile e teste |
| `junit-jupiter` | Framework de testes JUnit 5 — anotações `@Test`, asserts, lifecycle. | `test` |
| `allure-jupiter` | Integração Allure com JUnit 5 — gera resultados para o relatório. | `test` |
| `slf4j-simple` | Implementação simples de logging — suprime avisos SLF4J do Selenium. | `test` |

**Tags de cada dependência:**

| Tag | O que é | Exemplo |
|-----|---------|---------|
| `<groupId>` | Organização que desenvolveu a biblioteca. | `org.seleniumhq.selenium` |
| `<artifactId>` | Nome da biblioteca. | `selenium-java` |
| `<version>` | Versão. Pode usar `${variável}` para referenciar uma propriedade. | `${selenium.version}` |
| `<scope>` | Fases em que está disponível. | `test` (só durante execução de testes) |

**Escopos mais comuns:**

| Escopo | Compile | Test | Runtime | Exemplo de uso |
|--------|:-------:|:----:|:-------:|----------------|
| *(vazio)* | Sim | Sim | Sim | Dependência padrão — disponível em todas as fases |
| `test` | Não | Sim | Não | JUnit — só usado nos testes, não no código de produção |
| `provided` | Sim | Sim | Não | Servlet API — fornecida pelo servidor na hora de rodar |
| `runtime` | Não | Sim | Sim | JDBC driver — só necessário na hora de executar |

---

### 6. Build e Plugins

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${surefire.version}</version>
                <configuration>
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                    </argLine>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.aspectj</groupId>
                        <artifactId>aspectjweaver</artifactId>
                        <version>${aspectj.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
            <plugin>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-maven</artifactId>
                <version>${allure.maven.version}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

| Plugin | O que faz |
|--------|-----------|
| `maven-surefire-plugin` | Executa os testes JUnit durante `mvn test`. Configurado com AspectJ para suporte a anotações Allure. |
| `allure-maven` | Gera e serve o relatório Allure a partir dos resultados dos testes. |

**Detalhes do Surefire Plugin:**

| Tag | O que é |
|-----|---------|
| `<configuration><argLine>` | Passa o AspectJ Weaver como javaagent — necessário para que as anotações Allure (`@Step`, `@Attachment`) funcionem em tempo de execução. |
| `<dependencies><dependency>` | Declara o AspectJ Weaver como dependência do plugin (não do projeto). |

> **Nota:** O `settings.localRepository` é o caminho local do repositório Maven (geralmente `~/.m2/repository`). O AspectJ Weaver é baixado automaticamente na primeira execução.

---

## ▶️ Executar os Testes

No terminal do VSCode ou CMD, na raiz do projeto, execute:

```bash
mvn test
```

O Maven irá baixar todas as dependências automaticamente na primeira execução e, em seguida, executará os testes da pasta `src/test/java`.

### Executar uma classe de teste específica

```bash
mvn test -Dtest=LoginPageTest
mvn test -Dtest=LogoutPageTest
```

### Executar um método específico

```bash
mvn test -Dtest=LoginPageTest#testLoginComUsuarioValido
mvn test -Dtest=LogoutPageTest#testLogoutComSucesso
```

### Executar com navegador específico

```bash
mvn test -Dbrowser=CHROME
mvn test -Dbrowser=CHROME_HEADLESS
mvn test -Dbrowser=FIREFOX
mvn test -Dbrowser=FIREFOX_HEADLESS
mvn test -Dbrowser=EDGE
mvn test -Dbrowser=EDGE_HEADLESS
mvn test -Dbrowser=SAFARI
```

---

## 🔄 CI/CD com GitHub Actions

O projeto inclui um workflow automatizado que executa os testes, gera o relatório Allure e publica no GitHub Pages a cada **push** ou **pull request** no branch `main`/`master`.

#### Arquivo: `.github/workflows/test.yml`

```yaml
name: Selenium Tests

on:
  push:
    branches: [ "main", "master" ]
  pull_request:
    branches: [ "main", "master" ]

permissions:
  contents: read
  pages: write
  id-token: write

concurrency:
  group: "pages"
  cancel-in-progress: false

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: 📥 Checkout repository
        uses: actions/checkout@v4

      - name: ☕ Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: 🧪 Run tests
        run: mvn clean test -Dbrowser=CHROME_HEADLESS

      - name: 📊 Install Allure CLI
        if: always()
        run: |
          npm install -g allure-commandline
          allure --version

      - name: 📊 Generate Allure Report
        if: always()
        run: |
          allure generate target/allure-results --clean -o target/allure-report

      - name: 📤 Upload artifact
        if: always()
        uses: actions/upload-pages-artifact@v3
        with:
          path: target/allure-report

  deploy:
    needs: test
    if: always()
    runs-on: ubuntu-latest

    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}

    steps:
      - name: 🚀 Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

#### Como funciona

| Etapa | Descrição |
|-------|-----------|
| **Trigger** | Executa ao fazer push ou criar PR no branch `main`/`master` |
| **Runner** | `ubuntu-latest` — máquina virtual do GitHub com Chrome pré-instalado |
| **JDK** | Eclipse Temurin JDK 17 (mesma versão usada no projeto) |
| **Navegador** | `CHROME_HEADLESS` — Chrome sem interface gráfica, ideal para ambientes CI |
| **Allure Report** | Gera o relatório HTML a partir dos resultados dos testes |
| **GitHub Pages** | Publica o relatório no branch `gh-pages` — acessível em `https://<usuario>.github.io/<repo>/` |

> **Nota:** O step de relatório e deploy usa `if: always()`, ou seja, executa mesmo se os testes falharem — útil para analisar o que deu errado.

#### Configuração necessária no GitHub

Para que o deploy funcione, habilite o **GitHub Pages** no repositório:

1. Vá em **Settings** → **Pages**
2. Em **Source**, selecione **GitHub Actions**
3. Salve

O relatório será acessível em: `https://geovanegustavo.github.io/saucedemo-selenium-java-junit-maven/`

#### Executando os testes localmente com headless

```bash
mvn test -Dbrowser=CHROME_HEADLESS
```

---

## 📊 Allure Reports

O projeto utiliza **Allure Report 3** para gerar relatórios visuais detalhados dos testes.

#### Anotações Allure utilizadas

| Anotação | Onde | O que faz |
|----------|------|-----------|
| `@Epic` | `BaseTest` | Agrupa todos os testes do projeto sob um épico |
| `@Feature` | Classe de teste | Define a funcionalidade testada (Login, Logout, etc.) |
| `@Story` | Método de teste | Descreve o cenário específico |
| `@Severity` | Método de teste | Define a criticidade do teste |

#### Severidades por tipo de teste

| Severidade | Quando usar | Exemplo neste projeto |
|------------|-------------|----------------------|
| `BLOCKER` | Funcionalidade crítica — se falhar, o sistema principal está inutilizável | Login com credenciais válidas, Logout |
| `CRITICAL` | Funcionalidade importante — se falhar, afeta a experiência do usuário | Login com credenciais inválidas (verificação de erro) |
| `MAJOR` | Funcionalidade secundária — falha impacta parcialmente o usuário | Adicionar item ao carrinho |
| `MINOR` | Funcionalidade de baixo impacto — falha cosmetica ou inconveniente | Ordenação de itens |
| `TRIVIAL` | Teste exploratório ou documentação — falha não afeta o usuário | Teste de UI, responsividade |

> **Nota:** Neste projeto, usamos `BLOCKER` para fluxos principais (login/logout) e `CRITICAL` para validações de erro. Conforme novos testes são adicionados, as severidades devem seguir esta mesma lógica.

#### Exemplo de aplicação

```java
@Test
@Story("Login com usuário válido")
@Severity(SeverityLevel.BLOCKER)    // fluxo principal do sistema
public void testLoginComUsuarioValido() { ... }

@Test
@Story("Login com usuário inválido")
@Severity(SeverityLevel.CRITICAL)   // validação de regra de negócio
public void testLoginComUsuarioInvalido() { ... }
```

#### Gerar e visualizar o relatório

```bash
mvn clean test                  # executa os testes e gera os resultados
mvn allure:serve                # abre o relatório no navegador
```

> **Nota:** O `allure:serve` cria o relatório e abre automaticamente no navegador. O relatório fica em `target/allure-report/`.

#### Gerar relatório HTML sem abrir

```bash
mvn allure:report               # gera em target/allure-report/
```

#### Estrutura gerada

```
target/
├── allure-results/        ← resultados brutos (JSON/XML) gerados pelo Allure
└── allure-report/         ← relatório HTML gerado pelo plugin
```