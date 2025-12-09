// Julia
package br.com.pi.ui;

import javax.swing.*;

public class TabbedPaneComponent {
    private JTabbedPane tabbedPane;
    private ClientePanel painelClientes;
    private ProdutoPanel painelProdutos;
    private ClienteTablePanel painelClientesTabela;
    private ProdutoTablePanel painelProdutosTabela;

    public TabbedPaneComponent() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.TOP);

        // Usa os painéis de formulário reais (ClientePanel e ProdutoPanel)
        painelClientes = new ClientePanel();
        painelProdutos = new ProdutoPanel();

        // Painéis de tabela para listagem
        painelClientesTabela = new ClienteTablePanel();
        painelProdutosTabela = new ProdutoTablePanel();

        // Cria sub-abas para registros
        JTabbedPane registrosTab = new JTabbedPane();
        registrosTab.addTab("Clientes", painelClientesTabela);
        registrosTab.addTab("Produtos", painelProdutosTabela);

        // Adiciona as abas principais
        tabbedPane.addTab("Cadastro Clientes", painelClientes);
        tabbedPane.addTab("Cadastro Produtos", painelProdutos);
        tabbedPane.addTab("Registros", registrosTab);
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

