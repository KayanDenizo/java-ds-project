// Julia
package br.com.pi.ui;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class TabbedPaneComponent {
    private JTabbedPane tabbedPane;
    private ClientePanel painelClientes;
    private ProdutoPanel painelProdutos;
    private ClienteTablePanel painelClientesTabela;
    private ProdutoTablePanel painelProdutosTabela;

    public TabbedPaneComponent() {
        initComponents();
    }

    private void initComponents() {
        // Criar tabbed pane com UI customizada
        tabbedPane = new JTabbedPane() {
            @Override
            public void updateUI() {
                super.updateUI();
                setUI(new ModernTabbedPaneUI());
            }
        };

        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setFont(ModernTheme.FONT_REGULAR);
        tabbedPane.setBackground(ModernTheme.PRIMARY_BG);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());

        // Inicializar painéis com configurações robustas
        painelClientes = new ClientePanel();
        painelClientes.setName("clientePanel"); // Identificador único

        painelProdutos = new ProdutoPanel();
        painelProdutos.setName("produtoPanel"); // Identificador único

        painelClientesTabela = new ClienteTablePanel();
        painelClientesTabela.setName("clienteTablePanel"); // Identificador único

        painelProdutosTabela = new ProdutoTablePanel();
        painelProdutosTabela.setName("produtoTablePanel"); // Identificador único

        // Criar sub-abas para registros com design moderno
        JTabbedPane registrosTab = createRegistrosTab();

        // Adicionar ícones e títulos às abas principais
        tabbedPane.addTab("   Cadastro de Clientes   ", painelClientes);
        tabbedPane.addTab("   Cadastro de Produtos   ", painelProdutos);
        tabbedPane.addTab("   Gerenciamento de Registros   ", registrosTab);

        // Configurar tooltips
        tabbedPane.setToolTipTextAt(0, "Cadastrar novos clientes no sistema");
        tabbedPane.setToolTipTextAt(1, "Cadastrar novos produtos no sistema");
        tabbedPane.setToolTipTextAt(2, "Visualizar, editar e excluir registros");

        // Adicionar listener para garantir re-renderização correta ao trocar abas
        tabbedPane.addChangeListener(e -> {
            // Forçar revalidação e repaint do painel atual para evitar problemas visuais
            Component selectedComponent = tabbedPane.getSelectedComponent();
            if (selectedComponent != null) {
                selectedComponent.revalidate();
                selectedComponent.repaint();

                // Forçar atualização de toda a hierarquia para limpar artefatos visuais
                forceRepaintHierarchy(selectedComponent);
            }
        });
    }

    private JTabbedPane createRegistrosTab() {
        JTabbedPane registrosTab = new JTabbedPane() {
            @Override
            public void updateUI() {
                super.updateUI();
                setUI(new ModernTabbedPaneUI());
            }
        };

        registrosTab.setTabPlacement(JTabbedPane.TOP);
        registrosTab.setFont(ModernTheme.FONT_SMALL);
        registrosTab.setBackground(ModernTheme.SECONDARY_BG);

        // Adicionar sub-abas com ícones
        registrosTab.addTab(" Clientes ", painelClientesTabela);
        registrosTab.addTab(" Produtos ", painelProdutosTabela);

        registrosTab.setToolTipTextAt(0, "Gerenciar registros de clientes");
        registrosTab.setToolTipTextAt(1, "Gerenciar registros de produtos");

        // Adicionar listener para sub-abas também
        registrosTab.addChangeListener(e -> {
            Component selectedComponent = registrosTab.getSelectedComponent();
            if (selectedComponent != null) {
                selectedComponent.revalidate();
                selectedComponent.repaint();
            }
        });

        return registrosTab;
    }

    // Método para forçar repaint completo da hierarquia de componentes
    private void forceRepaintHierarchy(Component component) {
        // Limpar qualquer cache visual e forçar re-renderização
        component.revalidate();
        component.repaint();

        // Recursivamente atualizar todos os filhos
        if (component instanceof java.awt.Container) {
            java.awt.Container container = (java.awt.Container) component;
            for (int i = 0; i < container.getComponentCount(); i++) {
                Component child = container.getComponent(i);
                // Forçar atualização do componente filho
                if (child instanceof javax.swing.JComponent) {
                    javax.swing.JComponent jChild = (javax.swing.JComponent) child;
                    jChild.revalidate();
                    jChild.repaint();
                }
                forceRepaintHierarchy(child);
            }
        }
    }

    // UI customizada para as abas
    private static class ModernTabbedPaneUI extends BasicTabbedPaneUI {
        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                        int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (isSelected) {
                // Aba selecionada - azul moderno
                g2.setColor(ModernTheme.PRIMARY_COLOR);
                g2.fillRoundRect(x, y, w, h, ModernTheme.BORDER_RADIUS_MEDIUM, ModernTheme.BORDER_RADIUS_MEDIUM);

                // Borda inferior
                g2.setColor(ModernTheme.PRIMARY_COLOR);
                g2.drawRoundRect(x, y, w, h, ModernTheme.BORDER_RADIUS_MEDIUM, ModernTheme.BORDER_RADIUS_MEDIUM);
            } else {
                // Aba não selecionada
                g2.setColor(ModernTheme.SECONDARY_BG);
                g2.fillRoundRect(x, y, w, h, ModernTheme.BORDER_RADIUS_MEDIUM, ModernTheme.BORDER_RADIUS_MEDIUM);

                // Hover effect - simplificado
                g2.setColor(new Color(ModernTheme.PRIMARY_LIGHT.getRed(),
                                     ModernTheme.PRIMARY_LIGHT.getGreen(),
                                     ModernTheme.PRIMARY_LIGHT.getBlue(), 30));
                g2.fillRoundRect(x, y, w, h, ModernTheme.BORDER_RADIUS_MEDIUM, ModernTheme.BORDER_RADIUS_MEDIUM);
            }

            g2.dispose();
        }


        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
            // Largura um pouco maior para melhor aparência
            return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 20;
        }

        protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
            return fontHeight + 16; // Altura maior para melhor toque
        }
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public ClientePanel getPainelClientes() {
        return painelClientes;
    }

    public ProdutoPanel getPainelProdutos() {
        return painelProdutos;
    }

    public ClienteTablePanel getPainelClientesTabela() {
        return painelClientesTabela;
    }

    public ProdutoTablePanel getPainelProdutosTabela() {
        return painelProdutosTabela;
    }
}

