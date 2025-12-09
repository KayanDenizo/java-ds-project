package br.com.pi.ui;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnAtualizar;
    private ClienteDAO clienteDAO;

    public ClienteTablePanel() {
        clienteDAO = new ClienteDAO();
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Criar tabela
        String[] colunas = {"ID", "Nome", "Email", "CPF", "Sexo", "UF", "Newsletter"};
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
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Email
        table.getColumnModel().getColumn(3).setPreferredWidth(120); // CPF
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // Sexo
        table.getColumnModel().getColumn(5).setPreferredWidth(50);  // UF
        table.getColumnModel().getColumn(6).setPreferredWidth(80);  // Newsletter

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
                    editarCliente(id);
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
                    excluirCliente(id, nome);
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

        List<Cliente> clientes = clienteDAO.listar();

        for (Cliente cliente : clientes) {
            Object[] row = {
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getSexo(),
                cliente.getUf(),
                cliente.isNewsletter() ? "Sim" : "Não"
            };
            tableModel.addRow(row);
        }
    }

    private void editarCliente(int id) {
        // Buscar cliente por ID
        List<Cliente> clientes = clienteDAO.listar();
        Cliente clienteParaEditar = null;

        for (Cliente c : clientes) {
            if (c.getId() == id) {
                clienteParaEditar = c;
                break;
            }
        }

        if (clienteParaEditar != null) {
            ClienteEditDialog dialog = new ClienteEditDialog(
                SwingUtilities.getWindowAncestor(this),
                clienteParaEditar,
                clienteDAO
            );
            dialog.setVisible(true);

            // Recarregar dados após edição
            carregarDados();
        }
    }

    private void excluirCliente(int id, String nome) {
        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir o cliente \"" + nome + "\"?\nEsta ação não pode ser desfeita.",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            if (clienteDAO.excluir(id)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Cliente excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Erro ao excluir cliente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
