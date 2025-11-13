// Julia
package br.com.pi.ui;

import javax.swing.*;

public class TabbedPaneComponent {
    private JTabbedPane tabbedPane;

    public TabbedPaneComponent() {
        // TODO: Implementar JTabbedPane com abas "Clientes" e "Produtos"
        tabbedPane =  new JTabbedPane();

            tabbedPane.setTabPlacement(JTabbedPane.TOP);

            JPanel painelClientes = new JPanel();
            JPanel painelProdutos = new JPanel();

            tabbedPane.addTab("Clientes", painelClientes);
            tabbedPane.addTab("Produtos", painelProdutos);
    }

    public JTabbedPane getTabbedPane() { return tabbedPane; }
}

