package br.com.pi.ui;

import br.com.pi.dao.AdminDAO;
import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private ModernComponents.ModernTextField campoId;
    private ModernComponents.ModernPasswordField campoSenha;
    private ModernComponents.ModernButton btnLogin;
    private AdminDAO adminDAO;

    public LoginFrame() {
        // Inicializar adminDAO apenas quando necessário para evitar travamentos na inicialização
        // adminDAO = new AdminDAO();
        initComponents();
        setupFrame();
    }

    private void initComponents() {
        setTitle("Sistema de Cadastro - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Remove bordas do sistema
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com gradiente
        JPanel painelPrincipal = createMainPanel();
        add(painelPrincipal);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(ModernTheme.PRIMARY_BG);

        // Painel de login centralizado
        ModernComponents.ModernPanel loginPanel = new ModernComponents.ModernPanel(true);
        loginPanel.setBounds(50, 80, 300, 340);
        loginPanel.setLayout(null);

        // Título
        ModernComponents.ModernLabel tituloLabel = new ModernComponents.ModernLabel("Bem-vindo", true);
        tituloLabel.setBounds(20, 20, 260, 30);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setFont(ModernTheme.FONT_TITLE);
        loginPanel.add(tituloLabel);

        ModernComponents.ModernLabel subtituloLabel = new ModernComponents.ModernLabel("Entre com suas credenciais", false);
        subtituloLabel.setBounds(20, 55, 260, 20);
        subtituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(subtituloLabel);

        // Campo ID
        ModernComponents.ModernLabel idLabel = new ModernComponents.ModernLabel("ID do Administrador");
        idLabel.setBounds(20, 95, 260, 20);
        loginPanel.add(idLabel);

        campoId = ModernComponents.createTextField(20);
        campoId.setBounds(20, 120, 260, ModernTheme.INPUT_HEIGHT);
        setupPlaceholder(campoId, "Digite seu ID");
        loginPanel.add(campoId);

        // Campo Senha
        ModernComponents.ModernLabel senhaLabel = new ModernComponents.ModernLabel("Senha");
        senhaLabel.setBounds(20, 165, 260, 20);
        loginPanel.add(senhaLabel);

        campoSenha = ModernComponents.createPasswordField(20);
        campoSenha.setBounds(20, 190, 260, ModernTheme.INPUT_HEIGHT);
        loginPanel.add(campoSenha);

        // Botão Login
        btnLogin = ModernComponents.createPrimaryButton("Entrar");
        btnLogin.setBounds(20, 250, 260, ModernTheme.BUTTON_HEIGHT);
        btnLogin.addActionListener(e -> onLogin());
        loginPanel.add(btnLogin);

        // Botão fechar customizado
        JButton btnFechar = createCloseButton();
        btnFechar.setBounds(360, 10, 30, 30);
        panel.add(btnFechar);

        panel.add(loginPanel);

        // Enter no campo senha também faz login
        campoSenha.addActionListener(e -> onLogin());

        return panel;
    }

    private void setupPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(ModernTheme.TEXT_HINT);

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(ModernTheme.TEXT_PRIMARY);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(ModernTheme.TEXT_HINT);
                }
            }
        });
    }

    private JButton createCloseButton() {
        JButton button = new JButton("×");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(ModernTheme.TEXT_SECONDARY);
        button.setBackground(Color.decode("#F8F9FA"));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(ModernTheme.ERROR_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(ModernTheme.TEXT_SECONDARY);
            }
        });

        button.addActionListener(e -> System.exit(0));

        return button;
    }

    private void setupFrame() {
        // Adicionar sombra à janela
        getRootPane().setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 1),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));

        // Permitir arrastar a janela (já que removemos as bordas)
        final Point[] mouseDownCompCoords = {null};

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                mouseDownCompCoords[0] = e.getPoint();
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                mouseDownCompCoords[0] = null;
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (mouseDownCompCoords[0] != null) {
                    Point currCoords = e.getLocationOnScreen();
                    setLocation(currCoords.x - mouseDownCompCoords[0].x, currCoords.y - mouseDownCompCoords[0].y);
                }
            }
        });
    }
    
    private void onLogin() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            String senha = new String(campoSenha.getPassword());

            // Inicializar adminDAO apenas quando necessário
            if (adminDAO == null) {
                adminDAO = new AdminDAO();
            }

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





