// Kayan
package br.com.pi.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ProdutoPanel extends JPanel {
    private JTextField campoNomeProduto;
    private JTextField campoCodigo;
    private JFormattedTextField campoPreco;
    private JComboBox<String> comboCategoria;
    private JCheckBox checkDisponivel;
    private JRadioButton radioConsumivel;
    private JRadioButton radioDuravel;

    public ProdutoPanel() {
        setLayout(new BorderLayout(8, 8));

        JPanel painelFormulario = criarPainelFormulario();

        add(painelFormulario, BorderLayout.CENTER);
    }

    private JPanel criarPainelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Dados do Produto"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome do Produto
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(new JLabel("Nome:"), gbc);
        campoNomeProduto = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(campoNomeProduto, gbc);

        // Código
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Código:"), gbc);
        campoCodigo = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panel.add(campoCodigo, gbc);

        // Preço
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Preço (R$):"), gbc);
        campoPreco = new JFormattedTextField(java.text.NumberFormat.getNumberInstance());
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

    // Getters para acessar os campos do formulário
    public JTextField getCampoNomeProduto() {
        return campoNomeProduto;
    }

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public JFormattedTextField getCampoPreco() {
        return campoPreco;
    }

    public JComboBox<String> getComboCategoria() {
        return comboCategoria;
    }

    public JCheckBox getCheckDisponivel() {
        return checkDisponivel;
    }

    public JRadioButton getRadioConsumivel() {
        return radioConsumivel;
    }

    public JRadioButton getRadioDuravel() {
        return radioDuravel;
    }
}


