// Vinicius
package br.com.pi.ui;

import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private JMenu menuArquivo;
    private JMenu menuEditar;
    private JMenu menuVisualizar;
    private JMenu menuAjuda;

    // Itens do menu Arquivo
    private JMenuItem itemNovoCliente;
    private JMenuItem itemNovoProduto;
    private JMenuItem itemSair;

    // Itens do menu Editar
    private JMenuItem itemLimparCampos;
    private JMenuItem itemAtualizarDados;

    // Itens do menu Visualizar
    private JMenuItem itemVerClientes;
    private JMenuItem itemVerProdutos;
    private JCheckBoxMenuItem itemModoEscuro;

    // Itens do menu Ajuda
    private JMenuItem itemSobre;
    private JMenuItem itemAjuda;
    private JMenuItem itemContato;

    public MenuBar() {
        initComponents();
    }

    private void initComponents() {
        setBackground(ModernTheme.SECONDARY_BG);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ModernTheme.BORDER_LIGHT));

        // Cria os menus principais
        menuArquivo = criarMenu("Arquivo", KeyEvent.VK_A);
        menuEditar = criarMenu("Editar", KeyEvent.VK_E);
        menuVisualizar = criarMenu("Visualizar", KeyEvent.VK_V);
        menuAjuda = criarMenu("Ajuda", KeyEvent.VK_J);

        // ========== MENU ARQUIVO ==========
        itemNovoCliente = criarMenuItem("Novo Cliente", "Criar novo cadastro de cliente",
                                       KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        itemNovoProduto = criarMenuItem("Novo Produto", "Criar novo cadastro de produto",
                                       KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
        itemSair = criarMenuItem("Sair", "Fechar a aplicação",
                                KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);

        itemNovoCliente.addActionListener(e -> mostrarMensagemInfo("Novo cliente", "Função para criar novo cliente"));
        itemNovoProduto.addActionListener(e -> mostrarMensagemInfo("Novo produto", "Função para criar novo produto"));
        itemSair.addActionListener(e -> confirmarSaida());

        menuArquivo.add(itemNovoCliente);
        menuArquivo.add(itemNovoProduto);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);

        // ========== MENU EDITAR ==========
        itemLimparCampos = criarMenuItem("Limpar Campos", "Limpar todos os campos do formulário",
                                        KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK);
        itemAtualizarDados = criarMenuItem("Atualizar Dados", "Atualizar lista de registros",
                                          KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);

        itemLimparCampos.addActionListener(e -> mostrarMensagemInfo("Limpar campos", "Campos limpos com sucesso!"));
        itemAtualizarDados.addActionListener(e -> mostrarMensagemInfo("Atualizar dados", "Dados atualizados!"));

        menuEditar.add(itemLimparCampos);
        menuEditar.add(itemAtualizarDados);

        // ========== MENU VISUALIZAR ==========
        itemVerClientes = criarMenuItem("Ver Clientes", "Visualizar lista de clientes",
                                       KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK);
        itemVerProdutos = criarMenuItem("Ver Produtos", "Visualizar lista de produtos",
                                       KeyEvent.VK_P, KeyEvent.ALT_DOWN_MASK);

        itemModoEscuro = new JCheckBoxMenuItem("Modo Escuro");
        itemModoEscuro.setFont(ModernTheme.FONT_REGULAR);
        itemModoEscuro.setBackground(ModernTheme.SECONDARY_BG);
        itemModoEscuro.addActionListener(e -> alternarModoEscuro());

        itemVerClientes.addActionListener(e -> mostrarMensagemInfo("Ver clientes", "Abrindo lista de clientes..."));
        itemVerProdutos.addActionListener(e -> mostrarMensagemInfo("Ver produtos", "Abrindo lista de produtos..."));

        menuVisualizar.add(itemVerClientes);
        menuVisualizar.add(itemVerProdutos);
        menuVisualizar.addSeparator();
        menuVisualizar.add(itemModoEscuro);

        // ========== MENU AJUDA ==========
        itemSobre = criarMenuItem("Sobre", "Informações sobre o sistema", 0, 0);
        itemAjuda = criarMenuItem("Ajuda", "Ajuda e documentação", KeyEvent.VK_F1, 0);
        itemContato = criarMenuItem("Contato", "Informações de contato", KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK);

        itemSobre.addActionListener(e -> mostrarSobre());
        itemAjuda.addActionListener(e -> mostrarAjuda());
        itemContato.addActionListener(e -> mostrarContato());

        menuAjuda.add(itemSobre);
        menuAjuda.add(itemAjuda);
        menuAjuda.addSeparator();
        menuAjuda.add(itemContato);

        // Adiciona todos os menus na barra
        add(menuArquivo);
        add(menuEditar);
        add(menuVisualizar);
        add(Box.createHorizontalGlue()); // Espaçador
        add(menuAjuda);
    }

    private JMenu criarMenu(String texto, int mnemonic) {
        JMenu menu = new JMenu(texto);
        menu.setFont(ModernTheme.FONT_BOLD);
        menu.setMnemonic(mnemonic);
        menu.setBackground(ModernTheme.SECONDARY_BG);
        return menu;
    }

    private JMenuItem criarMenuItem(String texto, String tooltip, int keyCode, int modifiers) {
        JMenuItem item = new JMenuItem(texto);
        item.setFont(ModernTheme.FONT_REGULAR);
        item.setToolTipText(tooltip);
        if (keyCode != 0) {
            item.setAccelerator(KeyStroke.getKeyStroke(keyCode, modifiers));
        }
        item.setBackground(ModernTheme.SECONDARY_BG);

        // Hover effect
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item.setBackground(ModernTheme.PRIMARY_LIGHT);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item.setBackground(ModernTheme.SECONDARY_BG);
            }
        });

        return item;
    }

    private void mostrarMensagemInfo(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            titulo,
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void confirmarSaida() {
        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente sair da aplicação?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void mostrarSobre() {
        String sobre = """
            Sistema de Cadastro Java
            Versão 2.0 - Design Moderno

            Desenvolvido com Java Swing
            Interface moderna e intuitiva
            Gerenciamento completo de clientes e produtos

            © 2025 - Todos os direitos reservados
            """;

        JOptionPane.showMessageDialog(
            this,
            sobre,
            "Sobre o Sistema",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void mostrarAjuda() {
        String ajuda = """
            📖 Ajuda do Sistema

            📝 CADASTRO:
            • Preencha todos os campos obrigatórios
            • Clique em "Cadastrar" para salvar

            📊 GERENCIAMENTO:
            • Use as abas para navegar
            • Botão direito para opções adicionais
            • Filtros disponíveis na barra superior

            ⌨️ ATALHOS:
            • Ctrl+N: Novo cliente
            • Ctrl+P: Novo produto
            • Ctrl+L: Limpar campos
            • F1: Esta ajuda

            Para mais informações, consulte a documentação.
            """;

        JOptionPane.showMessageDialog(
            this,
            ajuda,
            "Ajuda do Sistema",
            JOptionPane.QUESTION_MESSAGE
        );
    }

    private void mostrarContato() {
        String contato = """
            📧 Informações de Contato

            Suporte Técnico:
            Email: suporte@sistema.com
            Telefone: (11) 9999-9999

            Desenvolvimento:
            Email: dev@sistema.com

            Horário de atendimento:
            Segunda a Sexta: 8h às 18h
            """;

        JOptionPane.showMessageDialog(
            this,
            contato,
            "Contato",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void alternarModoEscuro() {
        ModernTheme.toggleDarkMode();

        // Atualizar o texto do checkbox
        if (ModernTheme.isDarkMode()) {
            itemModoEscuro.setText("Modo Claro");
            itemModoEscuro.setSelected(true);
        } else {
            itemModoEscuro.setText("Modo Escuro");
            itemModoEscuro.setSelected(false);
        }
    }

    // Getters para acessar os itens de menu (caso precise adicionar mais funcionalidades)
    public JMenuItem getItemNovoCliente() {
        return itemNovoCliente;
    }

    public JMenuItem getItemNovoProduto() {
        return itemNovoProduto;
    }

    public JMenuItem getItemLimparCampos() {
        return itemLimparCampos;
    }

    public JMenuItem getItemAtualizarDados() {
        return itemAtualizarDados;
    }

    public JMenuItem getItemVerClientes() {
        return itemVerClientes;
    }

    public JMenuItem getItemVerProdutos() {
        return itemVerProdutos;
    }

    public JCheckBoxMenuItem getItemModoEscuro() {
        return itemModoEscuro;
    }

    public JMenuItem getItemSobre() {
        return itemSobre;
    }

    public JMenuItem getItemAjuda() {
        return itemAjuda;
    }

    public JMenuItem getItemContato() {
        return itemContato;
    }
}


