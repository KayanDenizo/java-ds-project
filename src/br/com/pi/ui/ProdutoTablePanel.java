package br.com.pi.ui;

import br.com.pi.dao.ProdutoDAO;
import br.com.pi.model.Produto;
import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class ProdutoTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private ModernComponents.ModernButton btnEditar;
    private ModernComponents.ModernButton btnExcluir;
    private ModernComponents.ModernButton btnAtualizar;
    private ModernComponents.ModernButton btnAdicionar;
    private JTextField campoFiltro;
    private ProdutoDAO produtoDAO;
    private DecimalFormat formatoPreco;
    private ModernComponents.ModernLabel labelContador;

    public ProdutoTablePanel() {
        produtoDAO = new ProdutoDAO();
        formatoPreco = new DecimalFormat("R$ #,##0.00");
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
        panel.setOpaque(false);

        // Título e contador
        ModernComponents.ModernLabel tituloLabel = new ModernComponents.ModernLabel("Produtos Cadastrados", true);
        tituloLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, ModernTheme.PADDING_SMALL, 0));
        panel.add(tituloLabel, BorderLayout.NORTH);

        // Painel de filtro
        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtroPanel.setOpaque(false);

        ModernComponents.ModernLabel filtroLabel = new ModernComponents.ModernLabel("Filtrar:", false);
        campoFiltro = new JTextField(20);
        campoFiltro.setBorder(ModernTheme.createGlassBorder(ModernTheme.BORDER_RADIUS_SMALL));
        campoFiltro.setBackground(new Color(255, 255, 255, 220));
        campoFiltro.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        filtroPanel.add(filtroLabel);
        filtroPanel.add(campoFiltro);
        panel.add(filtroPanel, BorderLayout.CENTER);

        // Painel esquerdo com contador
        JPanel contadorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contadorPanel.setOpaque(false);
        contadorPanel.add(criarLabelContador());
        panel.add(contadorPanel, BorderLayout.WEST);

        return panel;
    }

    private JScrollPane criarPainelTabela() {
        // Criar tabela com estilo Glassmorphism
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

        // Aplicar estilo Glassmorphism à tabela
        ModernTheme.styleTable(table);

        // Configurar sorter para filtragem
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Ajustar largura das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Código
        table.getColumnModel().getColumn(3).setPreferredWidth(80);  // Preço
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Categoria
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Tipo
        table.getColumnModel().getColumn(6).setPreferredWidth(80);  // Disponível

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(ModernTheme.createGlassBorder(ModernTheme.BORDER_RADIUS_MEDIUM));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        return scrollPane;
    }

    private JPanel criarBarraInferior() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        btnAdicionar = ModernComponents.createPrimaryButton("Adicionar");
        btnEditar = ModernComponents.createSecondaryButton("Editar");
        btnExcluir = ModernComponents.createSecondaryButton("Excluir");
        btnAtualizar = ModernComponents.createSecondaryButton("Atualizar");

        // Estado inicial dos botões
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);

        // Adicionar listeners
        btnAtualizar.addActionListener(e -> carregarDados());
        btnAdicionar.addActionListener(e -> adicionarProduto());
        btnEditar.addActionListener(e -> editarProdutoSelecionado());
        btnExcluir.addActionListener(e -> excluirProdutoSelecionado());

        // Listener para seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean selecionado = table.getSelectedRow() >= 0;
            btnEditar.setEnabled(selecionado);
            btnExcluir.setEnabled(selecionado);
        });

        panel.add(btnAdicionar);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        panel.add(btnAtualizar);

        return panel;
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
                produto.isDisponivel() ? "Sim" : "Nao"
            };
            tableModel.addRow(row);
        }

        atualizarContador();
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

    private ModernComponents.ModernLabel criarLabelContador() {
        labelContador = new ModernComponents.ModernLabel("Total: 0 produtos", false);
        labelContador.setForeground(ModernTheme.TEXT_SECONDARY);
        return labelContador;
    }

    private void atualizarContador() {
        if (labelContador != null) {
            int total = tableModel.getRowCount();
            String texto = total == 1 ? "Total: " + total + " produto" : "Total: " + total + " produtos";
            labelContador.setText(texto);
        }
    }

    private void adicionarProduto() {
        // TODO: Implementar adição de produto
        JOptionPane.showMessageDialog(this, "Funcionalidade de adicionar produto será implementada em breve.",
                                    "Em desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarProdutoSelecionado() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            int id = (Integer) tableModel.getValueAt(modelRow, 0);
            editarProduto(id);
        }
    }

    private void excluirProdutoSelecionado() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            int id = (Integer) tableModel.getValueAt(modelRow, 0);
            String nome = (String) tableModel.getValueAt(modelRow, 1);
            excluirProduto(id, nome);
        }
    }

    private void editarProduto(final int id) {
        // Executa a busca em uma thread separada para não travar a interface
        SwingUtilities.invokeLater(() -> {
            try {
                // Buscar produto por ID
                List<Produto> produtos = produtoDAO.listar();
                final Produto produtoParaEditar;

                Produto temp = null;
                for (Produto p : produtos) {
                    if (p.getId() == id) {
                        temp = p;
                        break;
                    }
                }
                produtoParaEditar = temp;

                if (produtoParaEditar != null) {
                    SwingUtilities.invokeLater(() -> {
                        ProdutoEditDialog dialog = new ProdutoEditDialog(
                            SwingUtilities.getWindowAncestor(this),
                            produtoParaEditar,
                            produtoDAO
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
                        "Erro ao buscar produto: " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                });
            }
        });
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
            // Executa a operação em uma thread separada para não travar a interface
            SwingUtilities.invokeLater(() -> {
                try {
                    if (produtoDAO.excluir(id)) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(
                                this,
                                "Produto excluído com sucesso!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                            carregarDados();
                        });
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(
                                this,
                                "Erro ao excluir produto.",
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
