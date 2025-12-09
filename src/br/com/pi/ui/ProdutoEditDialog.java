package br.com.pi.ui;

import br.com.pi.dao.ProdutoDAO;
import br.com.pi.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

public class ProdutoEditDialog extends JDialog {
    private Produto produto;
    private ProdutoDAO produtoDAO;

    private JTextField campoNome;
    private JTextField campoCodigo;
    private JFormattedTextField campoPreco;
    private JComboBox<String> comboCategoria;
    private JRadioButton radioConsumivel;
    private JRadioButton radioDuravel;
    private JCheckBox checkDisponivel;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public ProdutoEditDialog(Window parent, Produto produto, ProdutoDAO produtoDAO) {
        super(parent, "Editar Produto", ModalityType.APPLICATION_MODAL);
        this.produto = produto;
        this.produtoDAO = produtoDAO;

        initComponents();
        preencherCampos();
        setLocationRelativeTo(parent);
        pack();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel painelFormulario = criarPainelFormulario();
        JPanel painelBotoes = criarPainelBotoes();

        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Configurar tamanho mínimo
        setMinimumSize(new Dimension(400, 300));
    }

    private JPanel criarPainelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome do Produto
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(new JLabel("Nome:"), gbc);
        campoNome = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(campoNome, gbc);

        // Código
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Código:"), gbc);
        campoCodigo = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panel.add(campoCodigo, gbc);

        // Preço
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Preço (R$):"), gbc);
        campoPreco = new JFormattedTextField(NumberFormat.getNumberInstance());
        campoPreco.setColumns(10);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        panel.add(campoPreco, gbc);

        // Categoria (combo)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        panel.add(new JLabel("Categoria:"), gbc);
        comboCategoria = new JComboBox<>(new String[]{
                "Alimentos", "Bebidas", "Higiene", "Limpeza", "Eletrônicos", "Outros"
        });
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        panel.add(comboCategoria, gbc);

        // Tipo (radio)
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.0;
        panel.add(new JLabel("Tipo:"), gbc);
        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioConsumivel = new JRadioButton("Consumível");
        radioDuravel = new JRadioButton("Durável");
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(radioConsumivel);
        grupoTipo.add(radioDuravel);
        tipoPanel.add(radioConsumivel);
        tipoPanel.add(radioDuravel);
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0;
        panel.add(tipoPanel, gbc);

        // Disponível (checkbox)
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.0;
        panel.add(new JLabel("Disponível em estoque:"), gbc);
        checkDisponivel = new JCheckBox("Sim");
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0;
        panel.add(checkDisponivel, gbc);

        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(btnSalvar);
        panel.add(btnCancelar);

        return panel;
    }

    private void preencherCampos() {
        campoNome.setText(produto.getNome());
        campoCodigo.setText(produto.getCodigo());
        campoPreco.setValue(produto.getPreco());
        comboCategoria.setSelectedItem(produto.getCategoria());

        // Selecionar tipo
        if ("Consumível".equals(produto.getTipo())) {
            radioConsumivel.setSelected(true);
        } else if ("Durável".equals(produto.getTipo())) {
            radioDuravel.setSelected(true);
        }

        checkDisponivel.setSelected(produto.isDisponivel());
    }

    private void salvarProduto() {
        String nome = campoNome.getText().trim();
        String codigo = campoCodigo.getText().trim();
        String precoTexto = campoPreco.getText().trim();

        // Validação básica
        if (nome.isEmpty() || codigo.isEmpty() || precoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha Nome, Código e Preço.",
                "Validação",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double preco;
        try {
            Number number = NumberFormat.getNumberInstance().parse(precoTexto);
            preco = number.doubleValue();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(
                this,
                "Preço inválido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String categoria = (String) comboCategoria.getSelectedItem();

        // Obter tipo
        String tipo = "Não informado";
        if (radioConsumivel.isSelected()) {
            tipo = "Consumível";
        } else if (radioDuravel.isSelected()) {
            tipo = "Durável";
        }

        boolean disponivel = checkDisponivel.isSelected();

        // Atualizar produto
        produto.setNome(nome);
        produto.setCodigo(codigo);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        produto.setTipo(tipo);
        produto.setDisponivel(disponivel);

        if (produtoDAO.editar(produto)) {
            JOptionPane.showMessageDialog(
                this,
                "Produto atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao atualizar produto.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
