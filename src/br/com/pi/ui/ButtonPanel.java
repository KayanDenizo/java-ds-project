// Ana Julia
package br.com.pi.ui;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.dao.ProdutoDAO;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton btnCadastrar;
    private JButton btnLimpar;
    private JButton btnSair;
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
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        btnCadastrar = new JButton("Cadastrar");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");
        
        // Adiciona eventos aos botões
        btnCadastrar.addActionListener(e -> onCadastrar());
        btnLimpar.addActionListener(e -> onLimpar());
        btnSair.addActionListener(e -> onSair());
        
        add(btnCadastrar);
        add(btnLimpar);
        add(btnSair);
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
    
    public JButton getBtnCadastrar() {
        return btnCadastrar;
    }
    
    public JButton getBtnLimpar() {
        return btnLimpar;
    }
    
    public JButton getBtnSair() {
        return btnSair;
    }
}
