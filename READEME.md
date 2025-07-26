```markdown
# Projeto Backend JDBC - MySQL
# CRUD Completo 

Este projeto foi desenvolvido com o objetivo de **ensinar como conectar uma aplicação Java ao banco de dados MySQL usando JDBC**, além de mostrar duas formas de conexão: usando **arquivo `.properties`** e **variáveis de ambiente**.

---

## 🎯 Objetivos

- Aprender a usar JDBC com Java  
- Conectar com MySQL de forma segura  
- Utilizar propriedades externas ou variáveis de ambiente para conexão  
- Gerenciar a conexão e seu encerramento corretamente  

---

## ⚙️ Requisitos

- JDK 17+ instalado  
- Eclipse IDE  
- Conector JDBC (MySQL)  
- MySQL instalado  
- Variáveis de ambiente configuradas (opcional)  

---

## 📁 Estrutura do Projeto

```

JDBC-ConnectionDB/
├── src/
│   ├── apication/
│   │   └── app.java                   # Classe principal que executa a conexão e desconexão
│   ├── db/
│   │   ├── DB.java                    # Classe principal de conexão via properties
│   │   ├── Exceptions/
│   │   │   └── DbException.java       # Exceção personalizada
│   │   ├── closes/
│   │   │   └── CloseConection.java    # Encerramento seguro da conexão
│   │   └── data/
│   │       ├── LoadProperties.java           # Carregamento do arquivo .properties
│   │       └── LoadVariaveisAmbiente.java    # Carregamento das variáveis de ambiente
├── properties/
│   └── db.properties                 # Arquivo de configuração de conexão

```

---

## 🔧 Configuração do Projeto

### 1. Baixe o Conector JDBC

Você pode usar conectores diferentes conforme o banco de dados:

- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)  
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download.html)  

Salve o `.jar` em `C:\Program Files\Java\lib` ou em outro local conveniente.

---

### 2. Configure o Eclipse

1. **Importe o projeto:**  
   - `File > Open Projects from File System`  

2. **Crie uma biblioteca:**  
   - `Window > Preferences > Java > Build Path > User Libraries`  
   - Clique em **"New..."**, dê um nome (ex: `MySQLConnector`)  
   - Selecione a biblioteca criada e clique em **"Add External JARs..."**, depois selecione o conector `.jar`  

3. **Adicione a biblioteca ao projeto:**  
   - Botão direito no projeto > `Build Path > Configure Build Path`  
   - Aba **Libraries > Add Library > User Library**  
   - Escolha a biblioteca personalizada criada  

---

## 🔐 Formas de Conexão

### 1. Usando `db.properties`

#### 📁 Arquivo: `properties/db.properties`

Crie uma pasta chamada `properties` na raiz do projeto e dentro dela um arquivo `db.properties` com o seguinte conteúdo:

```

dburl=jdbc\:mysql://localhost:3306/meu\_banco
user=root
password=123456

````

**Observação:** o banco de dados `meu_banco` deve estar criado no seu MySQL.

---

#### 📦 Classe: `LoadProperties.java`

```java
public static Properties dbProperties() {
    try (FileInputStream fs = new FileInputStream("properties/db.properties")) {
        Properties props = new Properties();
        props.load(fs);
        return props;
    } catch (IOException e) {
        throw new DbException(e.getMessage());
    }
}
````

---

#### 📦 Classe: `DB.java`

```java
public static Connection getConectComProperties() {
    if (conn == null) {
        try {
            Properties props = LoadProperties.dbProperties();
            String url = props.getProperty("dburl");
            System.out.println("Conectando ao banco de dados: " + url);
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    return conn;
}
```

---

### 2. Usando Variáveis de Ambiente

Você pode optar por não usar um arquivo `.properties`, e sim configurar dados sensíveis diretamente nas **variáveis de ambiente do sistema**.

#### 📦 Classe: `LoadVariaveisAmbiente.java`

```java
public static Connection getConectVariaveisAmbiente(Connection conn) {
    if (conn == null) {
        try {
            String host = System.getenv("DB_HOST");
            String port = System.getenv("DB_PORT");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            String dbName = System.getenv("DB_NAME");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
            System.out.println("Conectando ao banco de dados: " + url);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage(), e);
        }
    }
    return conn;
}
```

---

## 🧱 Como Configurar Variáveis de Ambiente no Windows

### ▶️ Passo a Passo

1. Pressione `Win + S` e digite **“variáveis de ambiente”**
2. Clique em **“Editar variáveis de ambiente do sistema”**
3. Na nova janela, clique em **“Variáveis de ambiente...”**
4. Em **Variáveis de usuário**, clique em **"Nova..."** para cada item abaixo:

| Nome da variável | Valor exemplo |
| ---------------- | ------------- |
| `DB_HOST`        | `localhost`   |
| `DB_PORT`        | `3306`        |
| `DB_NAME`        | `meu_banco`   |
| `DB_USER`        | `root`        |
| `DB_PASSWORD`    | `123456`      |

5. Clique em **OK** em todas as janelas para salvar.

### 🔁 Importante:

* **Reinicie o computador** depois de definir as variáveis para garantir que o Java consiga acessá-las com `System.getenv()`.

---

## ❌ Encerrando a Conexão

### 📦 Classe: `CloseConection.java`

```java
public static void closeConection(Connection conn) {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
```

---

## ▶️ Executando o Projeto

### 📦 Classe: `app.java`

```java
public class app {
    public static void main(String[] args) {
        System.out.println("====================");	
        System.out.println("Banco Conectado");	
        System.out.println("====================");	

        // Conexão com arquivo .properties
        Connection conn = DB.getConectComProperties();

        // Ou, se quiser usar variáveis de ambiente:
        // Connection conn = LoadVariaveisAmbiente.getConectVariaveisAmbiente(DB.conn);

        CloseConection.closeConection(conn);

        System.out.println("====================");	
        System.out.println("Banco Desconectado");	
        System.out.println("====================");	
    }
}
```

---

## 📦 CRUD via RepositoryNivelUser

Esta classe abstrai operações básicas no banco para a tabela `Tbl_Nivel`. Veja abaixo os métodos disponíveis, o que fazem e a saída esperada:

| Método                                             | O que faz                                               | Retorno / Saída                                       |
| -------------------------------------------------- | ------------------------------------------------------- | ----------------------------------------------------- |
| `List<Nivel> queryAll()`                           | Retorna todos os registros da tabela `Tbl_Nivel`        | Lista de objetos `Nivel`                              |
| `Integer insert(String novoName)`                  | Insere um novo registro com o nome informado            | ID do novo registro inserido (int)                    |
| `Nivel updateNivelId(Integer id, String novoNome)` | Atualiza o registro com o ID informado para o novo nome | Objeto `Nivel` atualizado                             |
| `Boolean daleteNivelId(Integer id)`                | Deleta o registro pelo ID informado                     | `true` se deletou com sucesso, `false` caso contrário |

---

### Exemplo de uso no `app.java`:

```java
public class app {
    public static void main(String[] args) {
        
        RepositoryNivelUser nivelUser = new RepositoryNivelUser();
        
        // Listar todos os níveis (queryAll)
        List<Nivel> niveis = nivelUser.queryAll();		
        for(Nivel n : niveis) {
            System.out.println("Código: " + n.getIdNivel());
            System.out.println("Nome: " + n.getNomeNivel());
            System.out.println();
        }
        
        // Inserir um novo nível (insert)
        int novoId = nivelUser.insert("Novo Nível");
        System.out.println("ID inserido: " + novoId);
        
        // Atualizar um nível existente pelo ID (updateNivelId)
        int idNivel = novoId; // por exemplo
        String novoNome = "Nome Atualizado";
        Nivel nivelAtualizado = nivelUser.updateNivelId(idNivel, novoNome);
        System.out.println("Código: " + nivelAtualizado.getIdNivel());
        System.out.println("Nome: " + nivelAtualizado.getNomeNivel());
        
        // Deletar um nível pelo ID (daleteNivelId)
        Boolean sucesso = nivelUser.daleteNivelId(idNivel);
        System.out.println("Deletado com sucesso? " + sucesso);
    }
}
```

---

### Saídas Esperadas para cada operação:

* **queryAll():**

```
Código: 1
Nome: Básico

Código: 2
Nome: Intermediário

... (lista completa)
```

* **insert("Novo Nível"):**

```
45  (retorna o id gerado para o novo registro)
```

* **updateNivelId(45, "Nome Atualizado"):**

```
Linhas Afetadas 1
Código: 45
Nome: Nome Atualizado
```

* **daleteNivelId(45):**

```
true
```

---

## 📌 Observações

* Nunca salve senhas reais em arquivos públicos ou repositórios abertos
* Sempre feche suas conexões com `close()` para evitar vazamento de recursos
* O uso de `Properties` ou variáveis de ambiente melhora a segurança e flexibilidade

---

## 📫 Contato

Se tiver dúvidas ou quiser contribuir, sinta-se à vontade para abrir uma **issue** ou enviar um **pull request**.

---

**Desenvolvido com fins didáticos.**

```

---

