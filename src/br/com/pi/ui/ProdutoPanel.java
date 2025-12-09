// Kayan
package br.com.pi.ui;

import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class ProdutoPanel extends JPanel {
    private ModernComponents.ModernTextField campoNomeProduto;
    private ModernComponents.ModernTextField campoCodigo;
    private JFormattedTextField campoPreco;
    private JComboBox<String> comboCategoria;
    private JCheckBox checkDisponivel;
    private JRadioButton radioConsumivel;
    private JRadioButton radioDuravel;

    public ProdutoPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM));
        setBackground(ModernTheme.PRIMARY_BG);
        setBorder(BorderFactory.createEmptyBorder(ModernTheme.PADDING_LARGE, ModernTheme.PADDING_LARGE,
                                                ModernTheme.PADDING_LARGE, ModernTheme.PADDING_LARGE));

        // Painel principal com formulário
        ModernComponents.ModernPanel painelFormulario = criarPainelFormulario();
        ButtonPanel painelBotoes = new ButtonPanel(this);

        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private ModernComponents.ModernPanel criarPainelFormulario() {
        ModernComponents.ModernPanel panel = new ModernComponents.ModernPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM,
                               ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_MEDIUM);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título da seção
        ModernComponents.ModernLabel tituloLabel = new ModernComponents.ModernLabel("Dados do Produto", true);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, ModernTheme.PADDING_LARGE, 0));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panel.add(tituloLabel, gbc);

        // Reset para próximos componentes
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        // Campo Nome do Produto
        ModernComponents.ModernLabel nomeLabel = new ModernComponents.ModernLabel("Nome do Produto");
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(nomeLabel, gbc);

        campoNomeProduto = ModernComponents.createTextField(30);
        campoNomeProduto.setPlaceholder("Digite o nome do produto");
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panel.add(campoNomeProduto, gbc);

        // Campo Código
        ModernComponents.ModernLabel codigoLabel = new ModernComponents.ModernLabel("Código/SKU");
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(codigoLabel, gbc);

        campoCodigo = ModernComponents.createTextField(15);
        campoCodigo.setPlaceholder("PROD-001");
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        panel.add(campoCodigo, gbc);

        // Campo Preço
        ModernComponents.ModernLabel precoLabel = new ModernComponents.ModernLabel("Preço (R$)");
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        panel.add(precoLabel, gbc);

        campoPreco = criarCampoPreco();
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        panel.add(campoPreco, gbc);

        // Categoria - ComboBox
        ModernComponents.ModernLabel categoriaLabel = new ModernComponents.ModernLabel("Categoria");
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.0;
        panel.add(categoriaLabel, gbc);

        comboCategoria = criarComboCategoria();
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0;
        panel.add(comboCategoria, gbc);

        // Tipo - Radio Buttons
        ModernComponents.ModernLabel tipoLabel = new ModernComponents.ModernLabel("Tipo de Produto");
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.0;
        panel.add(tipoLabel, gbc);

        JPanel tipoPanel = criarPainelTipo();
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0;
        panel.add(tipoPanel, gbc);

        // Disponibilidade - Checkbox
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.0;
        panel.add(new JPanel(), gbc); // Espaçador

        checkDisponivel = criarCheckDisponivel();
        gbc.gridx = 1; gbc.gridy = 6; gbc.weightx = 1.0;
        panel.add(checkDisponivel, gbc);

        return panel;
    }

    private JFormattedTextField criarCampoPreco() {
        JFormattedTextField campo = new JFormattedTextField(java.text.NumberFormat.getNumberInstance());
        campo.setFont(ModernTheme.FONT_REGULAR);
        campo.setPreferredSize(new Dimension(150, ModernTheme.INPUT_HEIGHT));
        campo.setBorder(BorderFactory.createCompoundBorder(
            ModernTheme.createRoundedBorder(ModernTheme.BORDER_RADIUS_SMALL),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        campo.setBackground(Color.WHITE);
        campo.setForeground(ModernTheme.TEXT_PRIMARY);
        return campo;
    }

    private JComboBox<String> criarComboCategoria() {
        JComboBox<String> combo = new JComboBox<>(new String[]{
            "Selecione...", "Alimentos", "Bebidas", "Higiene", "Limpeza", "Eletrônicos", "Outros"
        });

        combo.setFont(ModernTheme.FONT_REGULAR);
        combo.setBackground(Color.WHITE);
        combo.setBorder(ModernTheme.createRoundedBorder(ModernTheme.BORDER_RADIUS_SMALL));
        combo.setPreferredSize(new Dimension(200, ModernTheme.INPUT_HEIGHT));

        return combo;
    }

    private JPanel criarPainelTipo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, ModernTheme.PADDING_MEDIUM, 0));
        panel.setOpaque(false);

        radioConsumivel = new JRadioButton("Consumível");
        radioDuravel = new JRadioButton("Durável");

        // Estilizar radio buttons
        radioConsumivel.setFont(ModernTheme.FONT_REGULAR);
        radioDuravel.setFont(ModernTheme.FONT_REGULAR);

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(radioConsumivel);
        grupoTipo.add(radioDuravel);

        panel.add(radioConsumivel);
        panel.add(radioDuravel);

        return panel;
    }

    private JCheckBox criarCheckDisponivel() {
        JCheckBox check = new JCheckBox("Produto disponível em estoque");
        check.setFont(ModernTheme.FONT_REGULAR);
        check.setForeground(ModernTheme.TEXT_SECONDARY);
        check.setBackground(ModernTheme.PANEL_BG);
        check.setBorder(BorderFactory.createEmptyBorder(ModernTheme.PADDING_SMALL, 0, 0, 0));

        return check;
    }

    // Getters para acessar os campos do formulário
    public ModernComponents.ModernTextField getCampoNomeProduto() {
        return campoNomeProduto;
    }

    public ModernComponents.ModernTextField getCampoCodigo() {
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
    
    // Método para limpar todos os campos do formulário
    public void limparCampos() {
        campoNomeProduto.setText("");
        campoCodigo.setText("");
        campoPreco.setValue(null);
        comboCategoria.setSelectedIndex(0);
        checkDisponivel.setSelected(false);
        radioConsumivel.setSelected(false);
        radioDuravel.setSelected(false);
    }
}


