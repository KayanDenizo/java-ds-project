package br.com.pi.ui;

import br.com.pi.dao.ProdutoDAO;
import br.com.pi.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class ProdutoTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnAtualizar;
    private ProdutoDAO produtoDAO;
    private DecimalFormat formatoPreco;

    public ProdutoTablePanel() {
        produtoDAO = new ProdutoDAO();
        formatoPreco = new DecimalFormat("R$ #,##0.00");
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Criar tabela
        String[] colunas = {"ID", "Nome", "Código", "Preço", "Categoria", "Tipo", "Disponível"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permitir edição direta na tabela
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        // Ajustar largura das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Código
        table.getColumnModel().getColumn(3).setPreferredWidth(80);  // Preço
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Categoria
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Tipo
        table.getColumnModel().getColumn(6).setPreferredWidth(80);  // Disponível

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAtualizar = new JButton("Atualizar Lista");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");

        // Estado inicial dos botões
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);

        // Adicionar listeners
        btnAtualizar.addActionListener(e -> carregarDados());

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                    editarProduto(id);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                    String nome = (String) tableModel.getValueAt(selectedRow, 1);
                    excluirProduto(id, nome);
                }
            }
        });

        // Listener para seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean selecionado = table.getSelectedRow() >= 0;
            btnEditar.setEnabled(selecionado);
            btnExcluir.setEnabled(selecionado);
        });

        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    public void carregarDados() {
        tableModel.setRowCount(0); // Limpar tabela

        List<Produto> produtos = produtoDAO.listar();

        for (Produto produto : produtos) {
            Object[] row = {
                produto.getId(),
                produto.getNome(),
                produto.getCodigo(),
                formatoPreco.format(produto.getPreco()),
                produto.getCategoria(),
                produto.getTipo(),
                produto.isDisponivel() ? "Sim" : "Não"
            };
            tableModel.addRow(row);
        }
    }

    private void editarProduto(int id) {
        // Buscar produto por ID
        List<Produto> produtos = produtoDAO.listar();
        Produto produtoParaEditar = null;

        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtoParaEditar = p;
                break;
            }
        }

        if (produtoParaEditar != null) {
            ProdutoEditDialog dialog = new ProdutoEditDialog(
                SwingUtilities.getWindowAncestor(this),
                produtoParaEditar,
                produtoDAO
            );
            dialog.setVisible(true);

            // Recarregar dados após edição
            carregarDados();
        }
    }

    private void excluirProduto(int id, String nome) {
        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir o produto \"" + nome + "\"?\nEsta ação não pode ser desfeita.",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            if (produtoDAO.excluir(id)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Produto excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Erro ao excluir produto.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
