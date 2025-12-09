package br.com.pi.ui;

import br.com.pi.dao.AdminDAO;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField campoId;
    private JPasswordField campoSenha;
    private JButton btnLogin;
    private AdminDAO adminDAO;
    
    public LoginFrame() {
        adminDAO = new AdminDAO();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Login - Sistema de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Label e campo ID
        gbc.gridx = 0; gbc.gridy = 0;
        painelPrincipal.add(new JLabel("ID:"), gbc);
        campoId = new JTextField(15);
        gbc.gridx = 1;
        painelPrincipal.add(campoId, gbc);
        
        // Label e campo Senha
        gbc.gridx = 0; gbc.gridy = 1;
        painelPrincipal.add(new JLabel("Senha:"), gbc);
        campoSenha = new JPasswordField(15);
        gbc.gridx = 1;
        painelPrincipal.add(campoSenha, gbc);
        
        // Botão Login
        btnLogin = new JButton("Entrar");
        btnLogin.addActionListener(e -> onLogin());
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(btnLogin, gbc);
        
        // Enter no campo senha também faz login
        campoSenha.addActionListener(e -> onLogin());
        
        add(painelPrincipal);
    }
    
    private void onLogin() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            String senha = new String(campoSenha.getPassword());
            
            if (adminDAO.validarLogin(id, senha)) {
                // Login válido - abrir MainFrame
                this.dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "ID ou senha incorretos!",
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE
                );
                campoSenha.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "ID deve ser um número!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}





