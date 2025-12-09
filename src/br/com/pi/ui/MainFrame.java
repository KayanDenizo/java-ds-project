// Thayna
package br.com.pi.ui;

import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MenuBar menuBar;
    private TabbedPaneComponent tabbedPaneComponent;
    private JPanel topBar;
    private ModernComponents.ModernButton btnMinimize;
    private ModernComponents.ModernButton btnClose;

    public MainFrame() {
        initComponents();
        setupFrame();
    }

    private void initComponents() {
        setUndecorated(true); // Remove bordas do sistema para design customizado
        setLayout(new BorderLayout());

        // Barra superior moderna
        topBar = createTopBar();
        add(topBar, BorderLayout.NORTH);

        // Barra de menu
        menuBar = new MenuBar();
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(ModernTheme.SECONDARY_BG);
        menuPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ModernTheme.BORDER_LIGHT));
        menuPanel.add(menuBar, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.NORTH);

        // Conteúdo principal
        tabbedPaneComponent = new TabbedPaneComponent();
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(ModernTheme.PRIMARY_BG);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(tabbedPaneComponent.getTabbedPane(), BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createTopBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.PRIMARY_COLOR);
        panel.setPreferredSize(new Dimension(getWidth(), 40));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 10));

        // Título da aplicação
        ModernComponents.ModernLabel titleLabel = new ModernComponents.ModernLabel("Sistema de Cadastro", false);
        titleLabel.setForeground(ModernTheme.TEXT_WHITE);
        titleLabel.setFont(ModernTheme.FONT_BOLD);
        panel.add(titleLabel, BorderLayout.WEST);

        // Painel de botões da janela
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 8));
        buttonPanel.setOpaque(false);

        // Botão minimizar
        btnMinimize = new ModernComponents.ModernButton("—", false);
        btnMinimize.setPreferredSize(new Dimension(30, 24));
        btnMinimize.setBackground(new Color(255, 255, 255, 100));
        btnMinimize.setForeground(ModernTheme.TEXT_WHITE);
        btnMinimize.addActionListener(e -> setState(JFrame.ICONIFIED));
        buttonPanel.add(btnMinimize);

        // Botão fechar
        btnClose = new ModernComponents.ModernButton("×", false);
        btnClose.setPreferredSize(new Dimension(30, 24));
        btnClose.setBackground(new Color(255, 255, 255, 100));
        btnClose.setForeground(ModernTheme.TEXT_WHITE);
        btnClose.addActionListener(e -> {
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
        });
        buttonPanel.add(btnClose);

        panel.add(buttonPanel, BorderLayout.EAST);

        // Permitir arrastar a janela
        final Point[] mouseDownCompCoords = {null};

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                mouseDownCompCoords[0] = e.getPoint();
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                mouseDownCompCoords[0] = null;
            }
        });

        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (mouseDownCompCoords[0] != null) {
                    Point currCoords = e.getLocationOnScreen();
                    setLocation(currCoords.x - mouseDownCompCoords[0].x, currCoords.y - mouseDownCompCoords[0].y);
                }
            }
        });

        return panel;
    }

    private void setupFrame() {
        setTitle("Sistema de Cadastro");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Controlamos o fechamento
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Adicionar sombra à janela
        getRootPane().setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 30), 1),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));

        // Ícone da aplicação
        try {
            setIconImage(Toolkit.getDefaultToolkit().createImage(""));
        } catch (Exception e) {
            // Ícone padrão
        }
    }

    public MenuBar getCustomMenuBar() {
        return menuBar;
    }

    public TabbedPaneComponent getTabbedPaneComponent() {
        return tabbedPaneComponent;
    }
}
