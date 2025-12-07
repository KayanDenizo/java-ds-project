# 🔧 Como Adicionar o Driver SQLite ao Projeto (Eclipse)

## ⚠️ ERRO ATUAL
```
No suitable driver found for jdbc:sqlite:sistema_cadastro.db
```

## ✅ SOLUÇÃO - Passo a Passo

### Passo 1: Baixar o Driver SQLite

1. Acesse: https://github.com/xerial/sqlite-jdbc/releases
2. Baixe o arquivo mais recente: `sqlite-jdbc-X.X.X.jar`
   - Exemplo: `sqlite-jdbc-3.44.1.0.jar`
3. Salve o arquivo em um local fácil de encontrar (ex: Desktop ou pasta do projeto)

### Passo 2: Adicionar ao Projeto no Eclipse

#### Método 1: Via Build Path (Recomendado)

1. **No Eclipse, clique com botão direito no projeto** `javaDS-project`
2. Selecione: **Properties** (ou **Propriedades**)
3. No menu esquerdo, clique em: **Java Build Path**
4. Clique na aba: **Libraries**
5. Clique no botão: **Add External JARs...**
6. Navegue até onde você salvou o arquivo `sqlite-jdbc-X.X.X.jar`
7. Selecione o arquivo e clique em **Open**
8. Clique em **Apply and Close**

#### Método 2: Criar pasta lib no projeto

1. **Crie uma pasta `lib` na raiz do projeto** (mesmo nível de `src`)
2. **Copie o arquivo `sqlite-jdbc-X.X.X.jar` para dentro da pasta `lib`**
3. **No Eclipse, clique com botão direito no projeto**
4. Selecione: **Properties** → **Java Build Path** → **Libraries**
5. Clique em: **Add JARs...** (não External JARs)
6. Expanda o projeto e selecione: `lib/sqlite-jdbc-X.X.X.jar`
7. Clique em **Apply and Close**

### Passo 3: Verificar se Funcionou

1. **Limpe e recompile o projeto:**
   - Menu: **Project** → **Clean...** → Selecione seu projeto → **Clean**
2. **Execute novamente:**
   - Execute `Main.java`
3. **Teste o login:**
   - ID: `1`
   - Senha: `1234`

## 📋 Verificação Rápida

Após adicionar o driver, você deve ver:
- ✅ O arquivo `.jar` aparece em **Referenced Libraries** no Eclipse
- ✅ Não aparece mais o erro "No suitable driver found"
- ✅ A tela de login funciona normalmente

## 🔍 Como Verificar se o Driver Está Adicionado

1. No Eclipse, expanda o projeto
2. Procure por **Referenced Libraries**
3. Você deve ver: `sqlite-jdbc-X.X.X.jar`

Se não aparecer, o driver não foi adicionado corretamente.

## 💡 Dica

Se ainda der erro após adicionar:
1. Feche o Eclipse completamente
2. Abra novamente
3. Limpe o projeto (Project → Clean)
4. Execute novamente

## 📥 Link Direto para Download

**Versão Recomendada (3.44.1.0):**
https://github.com/xerial/sqlite-jdbc/releases/download/3.44.1.0/sqlite-jdbc-3.44.1.0.jar

Ou baixe a versão mais recente em:
https://github.com/xerial/sqlite-jdbc/releases

