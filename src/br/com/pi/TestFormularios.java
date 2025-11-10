// Arquivo de teste para verificar se os formulários estão funcionando
package br.com.pi;

import br.com.pi.ui.ClientePanel;
import br.com.pi.ui.ProdutoPanel;

import javax.swing.*;

public class TestFormularios {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Teste do formulário de Cliente
            JFrame frameCliente = new JFrame("Teste - Formulário Cliente");
            frameCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameCliente.add(new ClientePanel());
            frameCliente.pack();
            frameCliente.setLocationRelativeTo(null);
            frameCliente.setVisible(true);
            
            // Teste do formulário de Produto
            JFrame frameProduto = new JFrame("Teste - Formulário Produto");
            frameProduto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameProduto.add(new ProdutoPanel());
            frameProduto.pack();
            frameProduto.setLocation(200, 200);
            frameProduto.setVisible(true);
        });
    }
}

