package br.com.pi.ui.theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Componentes modernos customizados para a aplicação
 */
public class ModernComponents {

    // ==========================================
    // BOTÃO MODERNO
    // ==========================================

    public static class ModernButton extends JButton {
        private Color normalColor;
        private Color hoverColor;
        private Color pressedColor;
        private boolean isPrimary;

        public ModernButton(String text, boolean isPrimary) {
            super(text);
            this.isPrimary = isPrimary;
            initButton();
        }

        private void initButton() {
            setFont(ModernTheme.FONT_BUTTON);
            setPreferredSize(new Dimension(120, ModernTheme.BUTTON_HEIGHT));
            setBorder(ModernTheme.createGlassBorder(ModernTheme.BORDER_RADIUS_MEDIUM));
            setFocusPainted(true); // Mostrar foco para melhor UX
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // FlatLaf + Glassmorphism: manter aparência customizada
            setOpaque(true); // Opaco para evitar problemas visuais de sobreposição

            if (isPrimary) {
                normalColor = ModernTheme.getPrimaryColor();
                hoverColor = ModernTheme.getPrimaryHover();
                pressedColor = ModernTheme.getPrimaryLight();
                setForeground(ModernTheme.TEXT_WHITE);
            } else {
                normalColor = ModernTheme.getSecondaryBg();
                hoverColor = ModernTheme.getAccentBg();
                pressedColor = ModernTheme.getPrimaryBg();
                setForeground(ModernTheme.getTextPrimary());
            }

            setBackground(normalColor);

            // Hover effects
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(hoverColor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(normalColor);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    setBackground(pressedColor);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setBackground(hoverColor);
                }
            });
        }

        public void setPrimary(boolean primary) {
            this.isPrimary = primary;
            if (primary) {
                normalColor = ModernTheme.PRIMARY_COLOR;
                hoverColor = ModernTheme.PRIMARY_HOVER;
                pressedColor = ModernTheme.PRIMARY_HOVER;
                setForeground(ModernTheme.TEXT_WHITE);
            } else {
                normalColor = ModernTheme.SECONDARY_BG;
                hoverColor = Color.decode("#E0E0E0");
                pressedColor = Color.decode("#D0D0D0");
                setForeground(ModernTheme.getTextPrimary());
            }
            setBackground(normalColor);
        }
    }

    // ==========================================
    // CAMPO DE TEXTO MODERNO
    // ==========================================

    public static class ModernTextField extends JTextField {
        private String placeholder = "";

        public ModernTextField(int columns) {
            super(columns);
            initField();
        }

        public ModernTextField() {
            super();
            initField();
        }

        private void initField() {
            setFont(ModernTheme.FONT_REGULAR);
            setPreferredSize(new Dimension(250, ModernTheme.INPUT_HEIGHT));
            setBorder(BorderFactory.createCompoundBorder(
                ModernTheme.createGlassBorder(ModernTheme.BORDER_RADIUS_SMALL),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));

            // Manter aparência customizada para glassmorphism

            setBackground(ModernTheme.getPrimaryBg()); // Fundo opaco para evitar problemas visuais
            setForeground(ModernTheme.TEXT_PRIMARY);
            setOpaque(true);
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
            repaint(); // Redesenhar para mostrar o placeholder
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty() && !placeholder.isEmpty() && !hasFocus()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(ModernTheme.TEXT_HINT);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = getInsets().left;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(placeholder, x, y);
                g2.dispose();
            }
        }

        public void focusGained(java.awt.event.FocusEvent e) {
            // Placeholder visual - apenas muda a cor do texto
            setForeground(ModernTheme.getTextPrimary());
            revalidate();
            repaint();
        }

        public void focusLost(java.awt.event.FocusEvent e) {
            // Placeholder visual - apenas muda a cor do texto
            setForeground(ModernTheme.getTextPrimary());
            revalidate();
            repaint();
        }
    }

    // ==========================================
    // CAMPO DE SENHA MODERNO
    // ==========================================

    public static class ModernPasswordField extends JPasswordField {
        public ModernPasswordField(int columns) {
            super(columns);
            initField();
        }

        public ModernPasswordField() {
            super();
            initField();
        }

        private void initField() {
            setFont(ModernTheme.FONT_REGULAR);
            setPreferredSize(new Dimension(250, ModernTheme.INPUT_HEIGHT));
            setBorder(BorderFactory.createCompoundBorder(
                ModernTheme.createGlassBorder(ModernTheme.BORDER_RADIUS_SMALL),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));
            setBackground(ModernTheme.getPrimaryBg()); // Fundo opaco para evitar problemas visuais
            setForeground(ModernTheme.TEXT_PRIMARY);
            setOpaque(true);
        }
    }

    // ==========================================
    // PAINEL MODERNO
    // ==========================================

    public static class ModernPanel extends JPanel {

        public ModernPanel() {
            initPanel();
        }

        public ModernPanel(LayoutManager layout) {
            super(layout);
            initPanel();
        }

        private void initPanel() {
            // Usar apenas configurações básicas para evitar problemas visuais
            setOpaque(true);
        }

        // Removido paintComponent customizado para evitar problemas visuais
    }

    // ==========================================
    // LABEL MODERNO
    // ==========================================

    public static class ModernLabel extends JLabel {
        public ModernLabel(String text, boolean isTitle) {
            super(text);
            ModernTheme.styleLabel(this, isTitle);
        }

        public ModernLabel(String text) {
            super(text);
            ModernTheme.styleLabel(this, false);
        }
    }

    // ==========================================
    // SCROLL PANE MODERNO
    // ==========================================

    public static class ModernScrollPane extends JScrollPane {
        public ModernScrollPane(Component view) {
            super(view);
            initScrollPane();
        }

        private void initScrollPane() {
            setBorder(ModernTheme.createRoundedBorder(ModernTheme.BORDER_RADIUS_SMALL));
            setBackground(ModernTheme.SECONDARY_BG);

            // Customizar scrollbar
            getVerticalScrollBar().setUnitIncrement(16);
            getHorizontalScrollBar().setUnitIncrement(16);

            // Tema para scrollbar
            getVerticalScrollBar().setBackground(ModernTheme.SECONDARY_BG);
            getHorizontalScrollBar().setBackground(ModernTheme.SECONDARY_BG);
        }
    }

    // ==========================================
    // MÉTODOS DE FÁBRICA
    // ==========================================

    public static ModernButton createPrimaryButton(String text) {
        return new ModernButton(text, true);
    }

    public static ModernButton createSecondaryButton(String text) {
        return new ModernButton(text, false);
    }

    public static ModernTextField createTextField(int columns) {
        return new ModernTextField(columns);
    }

    public static ModernPasswordField createPasswordField(int columns) {
        return new ModernPasswordField(columns);
    }

    public static ModernPanel createPanel() {
        return new ModernPanel();
    }

    public static ModernPanel createPanelWithShadow() {
        return new ModernPanel();
    }

    public static ModernLabel createTitleLabel(String text) {
        return new ModernLabel(text, true);
    }

    public static ModernLabel createLabel(String text) {
        return new ModernLabel(text, false);
    }

    public static ModernScrollPane createScrollPane(Component view) {
        return new ModernScrollPane(view);
    }
}
