// Ana Julia
package br.com.pi.ui;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.dao.ProdutoDAO;
import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private ModernComponents.ModernButton btnCadastrar;
    private ModernComponents.ModernButton btnLimpar;
    private ModernComponents.ModernButton btnSair;
    private ClientePanel clientePanel;
    private ProdutoPanel produtoPanel;
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;

    public ButtonPanel(ClientePanel clientePanel) {
        this.clientePanel = clientePanel;
        this.clienteDAO = new ClienteDAO();
        initComponents();
    }

    public ButtonPanel(ProdutoPanel produtoPanel) {
        this.produtoPanel = produtoPanel;
        this.produtoDAO = new ProdutoDAO();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(ModernTheme.PRIMARY_BG);
        setBorder(BorderFactory.createEmptyBorder(ModernTheme.PADDING_LARGE, ModernTheme.PADDING_LARGE,
                                                ModernTheme.PADDING_LARGE, ModernTheme.PADDING_LARGE));

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, ModernTheme.PADDING_MEDIUM, 0));
        painelBotoes.setOpaque(false);

        // Botão Cadastrar (primário)
        btnCadastrar = ModernComponents.createPrimaryButton("Cadastrar");
        btnCadastrar.setToolTipText("Salvar os dados no banco");

        // Botão Limpar (secundário)
        btnLimpar = ModernComponents.createSecondaryButton("Limpar");
        btnLimpar.setToolTipText("Limpar todos os campos");

        // Botão Sair (secundário)
        btnSair = ModernComponents.createSecondaryButton("Sair");
        btnSair.setToolTipText("Fechar a aplicação");

        // Adiciona eventos aos botões
        btnCadastrar.addActionListener(e -> onCadastrar());
        btnLimpar.addActionListener(e -> onLimpar());
        btnSair.addActionListener(e -> onSair());

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.CENTER);

        // Linha separadora superior
        add(criarLinhaSeparadora(), BorderLayout.NORTH);
    }

    private JPanel criarLinhaSeparadora() {
        JPanel linha = new JPanel();
        linha.setBackground(ModernTheme.BORDER_LIGHT);
        linha.setPreferredSize(new Dimension(linha.getWidth(), 1));
        return linha;
    }
    
    private void onCadastrar() {
        if (clientePanel != null) {
            cadastrarCliente();
        } else if (produtoPanel != null) {
            cadastrarProduto();
        }
    }
    
    private void cadastrarCliente() {
        String nome = clientePanel.getCampoNome().getText().trim();
        String email = clientePanel.getCampoEmail().getText().trim();
        String cpf = clientePanel.getCampoCpf().getText().trim();
        
        // Validação básica
        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
            DialogHandler.showWarningMessage("Preencha Nome, Email e CPF.", "Validação");
            return;
        }
        
        // Obter sexo
        String sexo = "Não informado";
        if (clientePanel.getRadioMasc().isSelected()) {
            sexo = "Masculino";
        } else if (clientePanel.getRadioFem().isSelected()) {
            sexo = "Feminino";
        } else if (clientePanel.getRadioOutro().isSelected()) {
            sexo = "Outro";
        }
        
        String uf = (String) clientePanel.getComboUf().getSelectedItem();
        boolean newsletter = clientePanel.getCheckNewsletter().isSelected();
        
        // Salvar no banco
        if (clienteDAO.cadastrar(nome, email, cpf, sexo, uf, newsletter)) {
            DialogHandler.showClienteCadastrado();
            clientePanel.limparCampos();
        } else {
            DialogHandler.showErrorMessage("Erro ao cadastrar cliente no banco de dados.", "Erro");
        }
    }
    
    private void cadastrarProduto() {
        String nome = produtoPanel.getCampoNomeProduto().getText().trim();
        String codigo = produtoPanel.getCampoCodigo().getText().trim();
        String precoTexto = produtoPanel.getCampoPreco().getText().trim();
        
        // Validação básica
        if (nome.isEmpty() || codigo.isEmpty() || precoTexto.isEmpty()) {
            DialogHandler.showWarningMessage("Preencha Nome, Código e Preço.", "Validação");
            return;
        }
        
        double preco;
        try {
            preco = Double.parseDouble(precoTexto.replace(",", "."));
        } catch (NumberFormatException e) {
            DialogHandler.showErrorMessage("Preço inválido!", "Erro");
            return;
        }
        
        String categoria = (String) produtoPanel.getComboCategoria().getSelectedItem();
        
        // Obter tipo
        String tipo = "Não informado";
        if (produtoPanel.getRadioConsumivel().isSelected()) {
            tipo = "Consumível";
        } else if (produtoPanel.getRadioDuravel().isSelected()) {
            tipo = "Durável";
        }
        
        boolean disponivel = produtoPanel.getCheckDisponivel().isSelected();
        
        // Salvar no banco
        if (produtoDAO.cadastrar(nome, codigo, preco, categoria, tipo, disponivel)) {
            DialogHandler.showProdutoCadastrado();
            produtoPanel.limparCampos();
        } else {
            DialogHandler.showErrorMessage("Erro ao cadastrar produto no banco de dados.", "Erro");
        }
    }
    
    private void onLimpar() {
        int resposta = DialogHandler.showConfirmarLimpar();
        if (resposta == JOptionPane.OK_OPTION) {
            if (clientePanel != null) {
                clientePanel.limparCampos();
            } else if (produtoPanel != null) {
                produtoPanel.limparCampos();
            }
            DialogHandler.showInfoMessage("Formulário limpo com sucesso.", "Limpar");
        }
    }
    
    private void onSair() {
        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja sair da aplicação?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (resposta == JOptionPane.YES_OPTION) {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        }
    }
    
    public ModernComponents.ModernButton getBtnCadastrar() {
        return btnCadastrar;
    }

    public ModernComponents.ModernButton getBtnLimpar() {
        return btnLimpar;
    }

    public ModernComponents.ModernButton getBtnSair() {
        return btnSair;
    }
}
