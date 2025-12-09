package br.com.pi.ui;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.model.Cliente;
import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private ModernComponents.ModernButton btnEditar;
    private ModernComponents.ModernButton btnExcluir;
    private ModernComponents.ModernButton btnAtualizar;
    private ModernComponents.ModernButton btnAdicionar;
    private JTextField campoFiltro;
    private ClienteDAO clienteDAO;
    private ModernComponents.ModernLabel labelContador;

    public ClienteTablePanel() {
        clienteDAO = new ClienteDAO();
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(ModernTheme.PRIMARY_BG);
        setBorder(BorderFactory.createEmptyBorder(ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM,
                                                ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM));

        // Barra superior com título e filtro
        add(criarBarraSuperior(), BorderLayout.NORTH);

        // Tabela central
        add(criarPainelTabela(), BorderLayout.CENTER);

        // Barra inferior com botões
        add(criarBarraInferior(), BorderLayout.SOUTH);
    }

    private JPanel criarBarraSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.SECONDARY_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            ModernTheme.createRoundedBorder(ModernTheme.BORDER_RADIUS_MEDIUM),
            BorderFactory.createEmptyBorder(ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM,
                                          ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM)
        ));

        // Título e contador
        ModernComponents.ModernLabel tituloLabel = new ModernComponents.ModernLabel("Gerenciamento de Clientes", true);
        panel.add(tituloLabel, BorderLayout.WEST);

        // Painel de filtro
        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, ModernTheme.PADDING_SMALL, 0));
        filtroPanel.setOpaque(false);

        ModernComponents.ModernLabel filtroLabel = new ModernComponents.ModernLabel("Filtrar:");
        campoFiltro = ModernComponents.createTextField(15);
        campoFiltro.setText("Digite para filtrar...");
        campoFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        filtroPanel.add(filtroLabel);
        filtroPanel.add(campoFiltro);
        panel.add(filtroPanel, BorderLayout.EAST);

        return panel;
    }

    private JScrollPane criarPainelTabela() {
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
        table.setRowHeight(35);

        // Configurar sorter para filtros
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Ajustar largura das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(60);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(180);  // Nome
        table.getColumnModel().getColumn(2).setPreferredWidth(220);  // Email
        table.getColumnModel().getColumn(3).setPreferredWidth(130);  // CPF
        table.getColumnModel().getColumn(4).setPreferredWidth(90);   // Sexo
        table.getColumnModel().getColumn(5).setPreferredWidth(60);   // UF
        table.getColumnModel().getColumn(6).setPreferredWidth(100);  // Newsletter

        // Aplicar estilo moderno à tabela
        ModernTheme.styleTable(table);

        // Scroll pane moderno
        ModernComponents.ModernScrollPane scrollPane = new ModernComponents.ModernScrollPane(table);
        scrollPane.setBorder(ModernTheme.createRoundedBorder(ModernTheme.BORDER_RADIUS_MEDIUM));

        return scrollPane;
    }

    private JPanel criarBarraInferior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ModernTheme.PRIMARY_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(ModernTheme.PADDING_MEDIUM, 0, 0, 0));

        // Painel esquerdo com contador
        JPanel contadorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contadorPanel.setOpaque(true);
        contadorPanel.add(criarLabelContador());
        panel.add(contadorPanel, BorderLayout.WEST);

        // Painel direito com botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, ModernTheme.PADDING_MEDIUM, 0));
        botoesPanel.setOpaque(true);

        btnAdicionar = ModernComponents.createPrimaryButton("➕ Adicionar Cliente");
        btnAtualizar = ModernComponents.createSecondaryButton("🔄 Atualizar");
        btnEditar = ModernComponents.createSecondaryButton("✏️ Editar");
        btnExcluir = ModernComponents.createSecondaryButton("🗑️ Excluir");

        // Estado inicial dos botões
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);

        // Tooltips
        btnAdicionar.setToolTipText("Adicionar novo cliente");
        btnAtualizar.setToolTipText("Atualizar lista de clientes");
        btnEditar.setToolTipText("Editar cliente selecionado");
        btnExcluir.setToolTipText("Excluir cliente selecionado");

        // Adicionar listeners
        btnAdicionar.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Use a aba 'Cadastro Clientes' para adicionar novos clientes.",
            "Informação", JOptionPane.INFORMATION_MESSAGE));

        btnAtualizar.addActionListener(e -> carregarDados());

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    int id = (Integer) tableModel.getValueAt(modelRow, 0);
                    editarCliente(id);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    int id = (Integer) tableModel.getValueAt(modelRow, 0);
                    String nome = (String) tableModel.getValueAt(modelRow, 1);
                    excluirCliente(id, nome);
                }
            }
        });

        botoesPanel.add(btnAdicionar);
        botoesPanel.add(btnAtualizar);
        botoesPanel.add(btnEditar);
        botoesPanel.add(btnExcluir);

        panel.add(botoesPanel, BorderLayout.EAST);

        // Listener para seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean selecionado = table.getSelectedRow() >= 0;
            btnEditar.setEnabled(selecionado);
            btnExcluir.setEnabled(selecionado);
        });

        return panel;
    }

    private ModernComponents.ModernLabel criarLabelContador() {
        labelContador = new ModernComponents.ModernLabel("Total: " + tableModel.getRowCount() + " clientes", false);
        labelContador.setForeground(ModernTheme.TEXT_SECONDARY);
        return labelContador;
    }

    private void filtrar() {
        String texto = campoFiltro.getText().trim();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
        atualizarContador();
    }

    private void atualizarContador() {
        if (labelContador != null) {
            int total = tableModel.getRowCount();
            String texto = total == 1 ? "Total: " + total + " cliente" : "Total: " + total + " clientes";
            labelContador.setText(texto);
        }
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
                cliente.isNewsletter() ? "Sim" : "Nao"
            };
            tableModel.addRow(row);
        }

        // Atualizar contador
        SwingUtilities.invokeLater(() -> atualizarContador());
    }

    private void editarCliente(final int id) {
        // Executa a busca em uma thread separada para não travar a interface
        SwingUtilities.invokeLater(() -> {
            try {
        // Buscar cliente por ID
        List<Cliente> clientes = clienteDAO.listar();
                final Cliente clienteParaEditar;

                Cliente temp = null;
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                        temp = c;
                break;
            }
        }
                clienteParaEditar = temp;

        if (clienteParaEditar != null) {
                    SwingUtilities.invokeLater(() -> {
            ClienteEditDialog dialog = new ClienteEditDialog(
                SwingUtilities.getWindowAncestor(this),
                clienteParaEditar,
                clienteDAO
            );
            dialog.setVisible(true);

            // Recarregar dados após edição
            carregarDados();
                    });
                }
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(
                        this,
                        "Erro ao buscar cliente: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                });
            }
        });
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
            // Executa a operação em uma thread separada para não travar a interface
            SwingUtilities.invokeLater(() -> {
                try {
            if (clienteDAO.excluir(id)) {
                        SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                    this,
                    "Cliente excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
                carregarDados();
                        });
            } else {
                        SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                    this,
                    "Erro ao excluir cliente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                        });
                    }
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(
                            this,
                            "Erro inesperado: " + e.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    });
                }
            });
        }
    }
}
