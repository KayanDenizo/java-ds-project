package br.com.pi.ui.theme;

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
    // CONTROLE DE TEMA (CLARO/ESCURO)
    // ==========================================

    private static boolean isDarkMode = false;

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
        applyTheme();
    }

    public static void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        applyTheme();
    }

    private static void applyTheme() {
        try {
            if (isDarkMode) {
                FlatDarculaLaf.setup();
                System.out.println("🌙 Modo escuro ativado!");
            } else {
                FlatIntelliJLaf.setup();
                System.out.println("☀️ Modo claro ativado!");
            }

            // Atualizar todas as variáveis de cor estáticas
            PRIMARY_BG = getPrimaryBg();
            SECONDARY_BG = getSecondaryBg();
            ACCENT_BG = getAccentBg();
            PANEL_BG = getPanelBg();
            GLASS_BG = getGlassBg();
            PRIMARY_COLOR = getPrimaryColor();
            PRIMARY_HOVER = getPrimaryHover();
            PRIMARY_LIGHT = getPrimaryLight();
            TEXT_PRIMARY = getTextPrimary();
            TEXT_SECONDARY = getTextSecondary();
            TEXT_HINT = getTextHint();
            BORDER_LIGHT = getBorderLight();
            BORDER_MEDIUM = getBorderMedium();
            DIVIDER = getDivider();
            GLASS_BORDER = getGlassBorder();

            customizeFlatLaf();

            // Atualizar aparência de todas as janelas abertas
            updateAllWindows();

        } catch (Exception e) {
            System.err.println("Erro ao alternar tema: " + e.getMessage());
        }
    }

    private static void updateAllWindows() {
        // Atualizar aparência de todas as janelas abertas
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
            window.repaint();

            // Forçar atualização de todos os componentes filhos
            updateAllComponents(window);
        }
    }

    private static void updateAllComponents(java.awt.Component component) {
        // Atualizar cores dinâmicas em todos os componentes
        component.revalidate();
        component.repaint();

        // Forçar atualização de propriedades visuais
        if (component instanceof javax.swing.JComponent) {
            javax.swing.JComponent jComponent = (javax.swing.JComponent) component;
            jComponent.setOpaque(jComponent.isOpaque()); // Forçar reavaliação
        }

        if (component instanceof java.awt.Container) {
            java.awt.Container container = (java.awt.Container) component;
            for (int i = 0; i < container.getComponentCount(); i++) {
                updateAllComponents(container.getComponent(i));
            }
        }
    }

    // Método público para forçar atualização de cores em componentes específicos
    public static void refreshComponentColors(java.awt.Component component) {
        updateAllComponents(component);
    }

    // ==========================================
    // CORES PRINCIPAIS (DINÂMICAS POR TEMA)
    // ==========================================

    // Backgrounds - adaptáveis ao tema
    public static Color getPrimaryBg() {
        return isDarkMode ? new Color(45, 45, 45, 240) : new Color(255, 255, 255, 240);
    }

    public static Color getSecondaryBg() {
        return isDarkMode ? new Color(60, 60, 60, 220) : new Color(248, 249, 250, 220);
    }

    public static Color getAccentBg() {
        return isDarkMode ? new Color(70, 70, 70, 200) : new Color(227, 242, 253, 200);
    }

    public static Color getPanelBg() {
        return isDarkMode ? new Color(50, 50, 50, 180) : new Color(255, 255, 255, 180);
    }

    public static Color getGlassBg() {
        return isDarkMode ? new Color(60, 60, 60, 150) : new Color(255, 255, 255, 150);
    }

    // Cores principais - adaptáveis ao tema
    public static Color getPrimaryColor() {
        return isDarkMode ? Color.decode("#64B5F6") : Color.decode("#1976D2");
    }

    public static Color getPrimaryHover() {
        return isDarkMode ? Color.decode("#42A5F5") : Color.decode("#1565C0");
    }

    public static Color getPrimaryLight() {
        return isDarkMode ? Color.decode("#1E88E5") : Color.decode("#BBDEFB");
    }

    // Textos - adaptáveis ao tema
    public static Color getTextPrimary() {
        return isDarkMode ? Color.decode("#FFFFFF") : Color.decode("#212121");
    }

    public static Color getTextSecondary() {
        return isDarkMode ? Color.decode("#B0B0B0") : Color.decode("#757575");
    }

    public static Color getTextHint() {
        return isDarkMode ? Color.decode("#808080") : Color.decode("#BDBDBD");
    }

    // Bordas e divisores - adaptáveis ao tema
    public static Color getBorderLight() {
        return isDarkMode ? new Color(100, 100, 100, 120) : new Color(224, 224, 224, 120);
    }

    public static Color getBorderMedium() {
        return isDarkMode ? new Color(120, 120, 120, 150) : new Color(189, 189, 189, 150);
    }

    public static Color getDivider() {
        return isDarkMode ? new Color(80, 80, 80, 100) : new Color(238, 238, 238, 100);
    }

    public static Color getGlassBorder() {
        return isDarkMode ? new Color(100, 100, 100, 80) : new Color(255, 255, 255, 80);
    }

    // Cores fixas (não mudam com o tema)
    public static final Color SUCCESS_COLOR = Color.decode("#4CAF50");
    public static final Color ERROR_COLOR = Color.decode("#F44336");
    public static final Color WARNING_COLOR = Color.decode("#FF9800");
    public static final Color TEXT_WHITE = Color.decode("#FFFFFF");

    // Para compatibilidade com código existente (valores iniciais - serão atualizados)
    public static Color PRIMARY_BG = getPrimaryBg();
    public static Color SECONDARY_BG = getSecondaryBg();
    public static Color ACCENT_BG = getAccentBg();
    public static Color PANEL_BG = getPanelBg();
    public static Color GLASS_BG = getGlassBg();
    public static Color PRIMARY_COLOR = getPrimaryColor();
    public static Color PRIMARY_HOVER = getPrimaryHover();
    public static Color PRIMARY_LIGHT = getPrimaryLight();
    public static Color TEXT_PRIMARY = getTextPrimary();
    public static Color TEXT_SECONDARY = getTextSecondary();
    public static Color TEXT_HINT = getTextHint();
    public static Color BORDER_LIGHT = getBorderLight();
    public static Color BORDER_MEDIUM = getBorderMedium();
    public static Color DIVIDER = getDivider();
    public static Color GLASS_BORDER = getGlassBorder();

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
        // Aplicar tema inicial (claro por padrão)
        applyTheme();
    }

    private static void customizeFlatLaf() {
        // Customizações mínimas para FlatLaf - evitando conflitos visuais
        try {
            // Bordas arredondadas básicas
            UIManager.put("Button.arc", BORDER_RADIUS_MEDIUM);
            UIManager.put("Component.arc", BORDER_RADIUS_MEDIUM);
            UIManager.put("TextComponent.arc", BORDER_RADIUS_SMALL);

            // Cores básicas - mantendo FlatLaf padrão mas com nossas cores
            UIManager.put("Table.selectionBackground", PRIMARY_LIGHT);
            UIManager.put("Table.selectionForeground", TEXT_PRIMARY);

            // Foco sutil
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Component.focusColor", PRIMARY_COLOR);

            // TabbedPane
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
