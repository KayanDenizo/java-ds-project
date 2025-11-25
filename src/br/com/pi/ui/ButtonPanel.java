// Ana Julia
package br.com.pi.ui;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton btnCadastrar;
    private JButton btnLimpar;
    private JButton btnSair;
    private ClientePanel clientePanel;
    private ProdutoPanel produtoPanel;
    
    public ButtonPanel(ClientePanel clientePanel) {
        this.clientePanel = clientePanel;
        initComponents();
    }
    
    public ButtonPanel(ProdutoPanel produtoPanel) {
        this.produtoPanel = produtoPanel;
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
            DialogHandler.showClienteCadastrado();
        } else if (produtoPanel != null) {
            DialogHandler.showProdutoCadastrado();
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
