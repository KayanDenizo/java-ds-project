package br.com.pi.ui.theme;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Tema moderno para a aplicação Java Swing
 * Design clean, profissional e elegante
 */
public class ModernTheme {

    // ==========================================
    // CORES PRINCIPAIS
    // ==========================================

    // Backgrounds - Glassmorphism
    public static final Color PRIMARY_BG = new Color(255, 255, 255, 240); // Branco semi-transparente
    public static final Color SECONDARY_BG = new Color(248, 249, 250, 220); // Cinza claro semi-transparente
    public static final Color ACCENT_BG = new Color(227, 242, 253, 200); // Azul claro semi-transparente
    public static final Color PANEL_BG = new Color(255, 255, 255, 180); // Branco muito translúcido para painéis
    public static final Color GLASS_BG = new Color(255, 255, 255, 150); // Fundo vidro muito translúcido
    public static final Color OVERLAY_BG = new Color(0, 0, 0, 30); // Overlay escuro sutil

    // Cores principais
    public static final Color PRIMARY_COLOR = Color.decode("#1976D2");
    public static final Color PRIMARY_HOVER = Color.decode("#1565C0");
    public static final Color PRIMARY_LIGHT = Color.decode("#BBDEFB");

    // Cores de sucesso/erro
    public static final Color SUCCESS_COLOR = Color.decode("#4CAF50");
    public static final Color ERROR_COLOR = Color.decode("#F44336");
    public static final Color WARNING_COLOR = Color.decode("#FF9800");

    // Textos
    public static final Color TEXT_PRIMARY = Color.decode("#212121");
    public static final Color TEXT_SECONDARY = Color.decode("#757575");
    public static final Color TEXT_HINT = Color.decode("#BDBDBD");
    public static final Color TEXT_WHITE = Color.decode("#FFFFFF");

    // Bordas e divisores - Glassmorphism
    public static final Color BORDER_LIGHT = new Color(224, 224, 224, 120); // Cinza claro translúcido
    public static final Color BORDER_MEDIUM = new Color(189, 189, 189, 150); // Cinza médio semi-translúcido
    public static final Color DIVIDER = new Color(238, 238, 238, 100); // Divisor muito sutil
    public static final Color GLASS_BORDER = new Color(255, 255, 255, 80); // Borda vidro sutil

    // ==========================================
    // FONTES
    // ==========================================

    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);

    // ==========================================
    // DIMENSÕES E ESPAÇAMENTO
    // ==========================================

    public static final int PADDING_SMALL = 8;
    public static final int PADDING_MEDIUM = 12;
    public static final int PADDING_LARGE = 16;
    public static final int PADDING_XLARGE = 24;

    public static final int BORDER_RADIUS_SMALL = 4;
    public static final int BORDER_RADIUS_MEDIUM = 8;
    public static final int BORDER_RADIUS_LARGE = 12;

    public static final int BUTTON_HEIGHT = 35;
    public static final int INPUT_HEIGHT = 32;

    // ==========================================
    // BORDAS MODERNAS
    // ==========================================

    public static Border createRoundedBorder(int radius) {
        return new LineBorder(BORDER_LIGHT, 1, true);
    }

    public static Border createRoundedBorder(Color color, int radius) {
        return new LineBorder(color, 1, true);
    }

    public static Border createEmptyBorder(int padding) {
        return new EmptyBorder(padding, padding, padding, padding);
    }

    public static Border createCompoundBorder(int outerPadding, int innerPadding) {
        return new CompoundBorder(
            new EmptyBorder(outerPadding, outerPadding, outerPadding, outerPadding),
            new EmptyBorder(innerPadding, innerPadding, innerPadding, innerPadding)
        );
    }

    public static Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_LIGHT, 1, true),
            title,
            0, 0,
            FONT_BOLD,
            TEXT_SECONDARY
        );
    }

    // ==========================================
    // BORDAS GLASSMORPHISM
    // ==========================================

    public static Border createGlassBorder(int radius) {
        return BorderFactory.createCompoundBorder(
            new LineBorder(GLASS_BORDER, 1, true),
            new EmptyBorder(2, 2, 2, 2)
        );
    }

    public static Border createFrostedBorder(int radius) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 60), 1, true),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        );
    }

    public static Border createGlassPanelBorder() {
        return BorderFactory.createCompoundBorder(
            createGlassBorder(BORDER_RADIUS_MEDIUM),
            new EmptyBorder(PADDING_MEDIUM, PADDING_MEDIUM, PADDING_MEDIUM, PADDING_MEDIUM)
        );
    }

    // ==========================================
    // COMPONENTES ESTILIZADOS
    // ==========================================

    public static void styleButton(JButton button, boolean isPrimary) {
        button.setFont(FONT_BUTTON);
        button.setPreferredSize(new Dimension(120, BUTTON_HEIGHT));
        button.setBorder(createGlassBorder(BORDER_RADIUS_MEDIUM));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);

        if (isPrimary) {
            button.setBackground(new Color(25, 118, 210, 200)); // Azul semi-transparente
            button.setForeground(TEXT_WHITE);
        } else {
            button.setBackground(new Color(248, 249, 250, 180)); // Fundo semi-transparente
            button.setForeground(TEXT_PRIMARY);
        }

        // Hover effect com glassmorphism
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (isPrimary) {
                    button.setBackground(new Color(21, 101, 192, 220)); // Azul mais opaco no hover
                } else {
                    button.setBackground(new Color(224, 224, 224, 200)); // Cinza mais opaco no hover
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (isPrimary) {
                    button.setBackground(new Color(25, 118, 210, 200));
                } else {
                    button.setBackground(new Color(248, 249, 250, 180));
                }
            }
        });
    }

    public static void styleTextField(JTextField textField) {
        textField.setFont(FONT_REGULAR);
        textField.setPreferredSize(new Dimension(250, INPUT_HEIGHT));
        textField.setBorder(BorderFactory.createCompoundBorder(
            createGlassBorder(BORDER_RADIUS_SMALL),
            createEmptyBorder(4)
        ));
        textField.setBackground(new Color(255, 255, 255, 220)); // Fundo semi-transparente
        textField.setOpaque(true);
    }

    public static void stylePasswordField(JPasswordField passwordField) {
        passwordField.setFont(FONT_REGULAR);
        passwordField.setPreferredSize(new Dimension(250, INPUT_HEIGHT));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            createGlassBorder(BORDER_RADIUS_SMALL),
            createEmptyBorder(4)
        ));
        passwordField.setBackground(new Color(255, 255, 255, 220)); // Fundo semi-transparente
        passwordField.setOpaque(true);
    }

    public static void styleLabel(JLabel label, boolean isTitle) {
        if (isTitle) {
            label.setFont(FONT_SUBTITLE);
            label.setForeground(TEXT_PRIMARY);
        } else {
            label.setFont(FONT_REGULAR);
            label.setForeground(TEXT_SECONDARY);
        }
    }

    public static void stylePanel(JPanel panel, boolean hasBorder) {
        panel.setBackground(PANEL_BG);
        if (hasBorder) {
            panel.setBorder(createGlassPanelBorder());
            // Adicionar efeito de sombra sutil
            panel.setOpaque(false);
        } else {
            panel.setBorder(createEmptyBorder(PADDING_MEDIUM));
            panel.setOpaque(false);
        }
    }

    public static void styleGlassPanel(JPanel panel) {
        panel.setBackground(GLASS_BG);
        panel.setBorder(createFrostedBorder(BORDER_RADIUS_LARGE));
        panel.setOpaque(false);
    }

    // ==========================================
    // UTILITÁRIOS GLASSMORPHISM
    // ==========================================

    public static void applyGlassEffect(Component component) {
        component.setBackground(new Color(255, 255, 255, 150));
        if (component instanceof JComponent) {
            ((JComponent) component).setOpaque(false);
        }
    }

    public static void applyFrostedGlassEffect(Component component) {
        component.setBackground(new Color(255, 255, 255, 120));
        if (component instanceof JComponent) {
            ((JComponent) component).setOpaque(false);
        }
    }

    public static void styleTable(JTable table) {
        table.setFont(FONT_REGULAR);
        table.setRowHeight(32);
        table.setGridColor(DIVIDER);
        table.setSelectionBackground(new Color(187, 222, 251, 180)); // Azul claro semi-transparente
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setBackground(new Color(255, 255, 255, 200)); // Fundo semi-transparente
        table.setOpaque(true);

        // Header com glassmorphism
        table.getTableHeader().setFont(FONT_BOLD);
        table.getTableHeader().setBackground(new Color(248, 249, 250, 220)); // Cinza semi-transparente
        table.getTableHeader().setForeground(TEXT_PRIMARY);
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(GLASS_BORDER));
        table.getTableHeader().setOpaque(true);
    }

    // ==========================================
    // UTILITÁRIOS
    // ==========================================

    public static void setGlobalLookAndFeel() {
        try {
            // FlatLaf IntelliJ - tema moderno e profissional
            FlatIntelliJLaf.setup();
            // FlatDarculaLaf.setup(); // ← DESCOMENTE esta linha para ver o tema DARK!
            customizeFlatLaf();
            System.out.println("✅ FlatLaf IntelliJ carregado com sucesso!");
            System.out.println("🎨 Tema moderno ativado - veja a diferença na interface!");
            System.out.println("💡 Para ver mudança dramática: descomente FlatDarculaLaf.setup()");
        } catch (Exception e) {
            try {
                // Fallback para FlatLaf Light
                FlatLightLaf.setup();
                customizeFlatLaf();
                System.out.println("✅ FlatLaf Light carregado (fallback)");
            } catch (Exception e2) {
                try {
                    // Fallback para Nimbus
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                    System.out.println("⚠️ Usando Nimbus (FlatLaf não disponível)");
                } catch (Exception e3) {
                    // Último fallback - usar o padrão do sistema
                    System.err.println("❌ Usando Look and Feel padrão do sistema");
                }
            }
        }
    }

    private static void customizeFlatLaf() {
        // Customizações específicas para FlatLaf + Glassmorphism
        try {
            // Bordas arredondadas consistentes
            UIManager.put("Button.arc", BORDER_RADIUS_MEDIUM);
            UIManager.put("Component.arc", BORDER_RADIUS_MEDIUM);
            UIManager.put("TextComponent.arc", BORDER_RADIUS_SMALL);
            UIManager.put("ScrollBar.arc", BORDER_RADIUS_MEDIUM);

            // Cores que combinam com glassmorphism - mais perceptíveis
            UIManager.put("Panel.background", new Color(250, 250, 250, 220)); // Fundo mais claro
            UIManager.put("OptionPane.background", GLASS_BG);

            // Indicador visual de FlatLaf ativo (borda sutil azul)
            UIManager.put("Panel.border", BorderFactory.createLineBorder(new Color(33, 150, 243, 50), 1));

            // Melhorar aparência dos botões - mais vibrantes com FlatLaf
            UIManager.put("Button.focusWidth", 2);
            UIManager.put("Button.focusColor", PRIMARY_COLOR);
            UIManager.put("Button.default.focusColor", new Color(76, 175, 80)); // Verde para confirmação

            // Scrollbar mais moderno (flat)
            UIManager.put("ScrollBar.thumbArc", BORDER_RADIUS_MEDIUM);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));

            // Melhorar tabelas com cores mais vibrantes
            UIManager.put("Table.selectionBackground", new Color(33, 150, 243, 120)); // Azul mais visível
            UIManager.put("Table.selectionForeground", TEXT_PRIMARY);

            // Componentes de foco mais visíveis
            UIManager.put("Component.focusWidth", 2);
            UIManager.put("Component.focusColor", PRIMARY_COLOR);

            // Melhorar aparência geral
            UIManager.put("TabbedPane.selectedBackground", PRIMARY_LIGHT);
            UIManager.put("TabbedPane.selectedForeground", TEXT_WHITE);

        } catch (Exception e) {
            System.err.println("Erro ao customizar FlatLaf: " + e.getMessage());
        }
    }

    public static Color getContrastColor(Color background) {
        // Calcular luminância para determinar cor de texto contrastante
        double luminance = (0.299 * background.getRed() +
                          0.587 * background.getGreen() +
                          0.114 * background.getBlue()) / 255;

        return luminance > 0.5 ? TEXT_PRIMARY : TEXT_WHITE;
    }
}
