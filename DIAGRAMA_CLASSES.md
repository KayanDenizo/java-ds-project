# 📊 Diagrama de Classes - Sistema de Cadastro

## Diagrama UML

```mermaid
classDiagram

    class Main {
        +main(String[] args) : void
    }

    class LoginFrame {
        -campoId : JTextField
        -campoSenha : JPasswordField
        -btnLogin : JButton
        -adminDAO : AdminDAO
        +LoginFrame()
        -initComponents() : void
        -onLogin() : void
    }

    class MainFrame {
        -menuBar : MenuBar
        -tabbedPaneComponent : TabbedPaneComponent
        +MainFrame()
        -initComponents() : void
        -setupFrame() : void
        +getCustomMenuBar() : MenuBar
        +getTabbedPaneComponent() : TabbedPaneComponent
    }

    class MenuBar {
        -menuArquivo : JMenu
        -menuAjuda : JMenu
        -itemNovo : JMenuItem
        -itemTodos : JMenuItem
        -itemTermos : JMenuItem
        -itemDuvidas : JMenuItem
        +MenuBar()
        -initComponents() : void
    }

    class TabbedPaneComponent {
        -tabbedPane : JTabbedPane
        -painelClientes : ClientePanel
        -painelProdutos : ProdutoPanel
        +TabbedPaneComponent()
        +getTabbedPane() : JTabbedPane
        +getPainelClientes() : ClientePanel
        +getPainelProdutos() : ProdutoPanel
    }

    class ClientePanel {
        -campoNome : JTextField
        -campoEmail : JTextField
        -campoCpf : JTextField
        -checkNewsletter : JCheckBox
        -radioMasc : JRadioButton
        -radioFem : JRadioButton
        -radioOutro : JRadioButton
        -comboUf : JComboBox~String~
        +ClientePanel()
        -criarPainelFormulario() : JPanel
        +getCampoNome() : JTextField
        +getCampoEmail() : JTextField
        +getCampoCpf() : JTextField
        +limparCampos() : void
    }

    class ProdutoPanel {
        -campoNomeProduto : JTextField
        -campoCodigo : JTextField
        -campoPreco : JFormattedTextField
        -comboCategoria : JComboBox~String~
        -checkDisponivel : JCheckBox
        -radioConsumivel : JRadioButton
        -radioDuravel : JRadioButton
        +ProdutoPanel()
        -criarPainelFormulario() : JPanel
        +getCampoNomeProduto() : JTextField
        +getCampoCodigo() : JTextField
        +getCampoPreco() : JFormattedTextField
        +limparCampos() : void
    }

    class ButtonPanel {
        -btnCadastrar : JButton
        -btnLimpar : JButton
        -btnSair : JButton
        -clientePanel : ClientePanel
        -produtoPanel : ProdutoPanel
        -clienteDAO : ClienteDAO
        -produtoDAO : ProdutoDAO
        +ButtonPanel(ClientePanel)
        +ButtonPanel(ProdutoPanel)
        -initComponents() : void
        -onCadastrar() : void
        -cadastrarCliente() : void
        -cadastrarProduto() : void
        -onLimpar() : void
        -onSair() : void
    }

    class DialogHandler {
        +showClienteCadastrado() : void
        +showProdutoCadastrado() : void
        +showConfirmarLimpar() : int
        +showInfoMessage(String, String) : void
        +showWarningMessage(String, String) : void
        +showErrorMessage(String, String) : void
    }

    class AdminDAO {
        +validarLogin(int, String) : boolean
    }

    class ClienteDAO {
        +cadastrar(String, String, String, String, String, boolean) : boolean
    }

    class ProdutoDAO {
        +cadastrar(String, String, double, String, String, boolean) : boolean
    }

    class DatabaseConnection {
        -HOST : String
        -PORT : String
        -DATABASE : String
        -USER : String
        -PASSWORD : String
        -connection : Connection
        -tabelasCriadas : boolean
        +getConnection() : Connection
        -criarTabelas() : void
        +closeConnection() : void
    }

    Main --> LoginFrame : cria
    LoginFrame --> AdminDAO : usa
    LoginFrame --> MainFrame : cria
    MainFrame *-- MenuBar : contém
    MainFrame *-- TabbedPaneComponent : contém
    TabbedPaneComponent *-- ClientePanel : contém
    TabbedPaneComponent *-- ProdutoPanel : contém
    ClientePanel *-- ButtonPanel : contém
    ProdutoPanel *-- ButtonPanel : contém
    ButtonPanel --> ClienteDAO : usa
    ButtonPanel --> ProdutoDAO : usa
    ButtonPanel --> DialogHandler : usa
    ButtonPanel ..> ClientePanel : referencia
    ButtonPanel ..> ProdutoPanel : referencia
    AdminDAO --> DatabaseConnection : usa
    ClienteDAO --> DatabaseConnection : usa
    ProdutoDAO --> DatabaseConnection : usa
```

## 📦 Estrutura de Pacotes

```
br.com.pi
├── Main
│
├── ui (Interface Gráfica)
│   ├── LoginFrame
│   ├── MainFrame
│   ├── MenuBar
│   ├── TabbedPaneComponent
│   ├── ClientePanel
│   ├── ProdutoPanel
│   ├── ButtonPanel
│   └── DialogHandler
│
├── dao (Data Access Objects)
│   ├── AdminDAO
│   ├── ClienteDAO
│   └── ProdutoDAO
│
└── db (Banco de Dados)
    └── DatabaseConnection
```

## 🔗 Relacionamentos Principais

1. **Main** → Cria e inicia **LoginFrame**
2. **LoginFrame** → Usa **AdminDAO** para validar login → Cria **MainFrame** se válido
3. **MainFrame** → Contém **MenuBar** e **TabbedPaneComponent**
4. **TabbedPaneComponent** → Contém **ClientePanel** e **ProdutoPanel**
5. **ClientePanel/ProdutoPanel** → Contém **ButtonPanel**
6. **ButtonPanel** → Usa **ClienteDAO/ProdutoDAO** e **DialogHandler**
7. **Todos os DAOs** → Usam **DatabaseConnection** para acessar o banco

## 📝 Legenda

- **+** = público (public)
- **-** = privado (private)
- **→** = relacionamento de uso/criação
- **contém** = composição (um objeto contém outro)
- **usa** = dependência (um objeto usa outro)

