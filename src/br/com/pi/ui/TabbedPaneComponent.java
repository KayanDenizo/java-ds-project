// Julia
package br.com.pi.ui;

import javax.swing.*;

public class TabbedPaneComponent {
    private JTabbedPane tabbedPane;
    private ClientePanel painelClientes;
    private ProdutoPanel painelProdutos;

    public TabbedPaneComponent() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.TOP);

        // Usa os painéis de formulário reais (ClientePanel e ProdutoPanel)
        painelClientes = new ClientePanel();
        painelProdutos = new ProdutoPanel();

        // Adiciona as abas com os formulários
        tabbedPane.addTab("Clientes", painelClientes);
        tabbedPane.addTab("Produtos", painelProdutos);
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
}

