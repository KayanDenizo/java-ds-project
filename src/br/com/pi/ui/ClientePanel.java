// Kayan
package br.com.pi.ui;

import br.com.pi.ui.theme.ModernComponents;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;
import java.awt.*;

public class ClientePanel extends JPanel {
    private ModernComponents.ModernTextField campoNome;
    private ModernComponents.ModernTextField campoEmail;
    private ModernComponents.ModernTextField campoCpf;
    private JCheckBox checkNewsletter;
    private JRadioButton radioMasc;
    private JRadioButton radioFem;
    private JRadioButton radioOutro;
    private JComboBox<String> comboUf;

    public ClientePanel() {
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
        gbc.anchor = GridBagConstraints.WEST; // Alinhar à esquerda

        // Título da seção
        ModernComponents.ModernLabel tituloLabel = new ModernComponents.ModernLabel("Dados do Cliente", true);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, ModernTheme.PADDING_LARGE, 0));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panel.add(tituloLabel, gbc);

        // Reset para próximos componentes
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        // Campo Nome
        ModernComponents.ModernLabel nomeLabel = new ModernComponents.ModernLabel("Nome Completo");
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(nomeLabel, gbc);

        campoNome = ModernComponents.createTextField(30);
        campoNome.setPlaceholder("Digite o nome completo");
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panel.add(campoNome, gbc);

        // Campo Email
        ModernComponents.ModernLabel emailLabel = new ModernComponents.ModernLabel("E-mail");
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(emailLabel, gbc);

        campoEmail = ModernComponents.createTextField(30);
        campoEmail.setPlaceholder("exemplo@email.com");
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        panel.add(campoEmail, gbc);

        // Campo CPF
        ModernComponents.ModernLabel cpfLabel = new ModernComponents.ModernLabel("CPF");
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        panel.add(cpfLabel, gbc);

        campoCpf = ModernComponents.createTextField(15);
        campoCpf.setPlaceholder("000.000.000-00");
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        panel.add(campoCpf, gbc);

        // Sexo - Radio Buttons
        ModernComponents.ModernLabel sexoLabel = new ModernComponents.ModernLabel("Gênero");
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.0;
        panel.add(sexoLabel, gbc);

        JPanel sexoPanel = criarPainelSexo();
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0;
        panel.add(sexoPanel, gbc);

        // UF - ComboBox
        ModernComponents.ModernLabel ufLabel = new ModernComponents.ModernLabel("Estado (UF)");
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.0;
        panel.add(ufLabel, gbc);

        comboUf = criarComboUf();
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0;
        panel.add(comboUf, gbc);

        // Newsletter - Checkbox
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0.0;
        panel.add(new JPanel(), gbc); // Espaçador

        checkNewsletter = criarCheckNewsletter();
        gbc.gridx = 1; gbc.gridy = 6; gbc.weightx = 1.0;
        panel.add(checkNewsletter, gbc);

        return panel;
    }

    private JPanel criarPainelSexo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, ModernTheme.PADDING_MEDIUM, 0));
        panel.setOpaque(false);

        radioMasc = new JRadioButton("Masculino");
        radioFem = new JRadioButton("Feminino");
        radioOutro = new JRadioButton("Outro");

        // Estilizar radio buttons
        radioMasc.setFont(ModernTheme.FONT_REGULAR);
        radioFem.setFont(ModernTheme.FONT_REGULAR);
        radioOutro.setFont(ModernTheme.FONT_REGULAR);

        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(radioMasc);
        grupoSexo.add(radioFem);
        grupoSexo.add(radioOutro);

        panel.add(radioMasc);
        panel.add(radioFem);
        panel.add(radioOutro);

        return panel;
    }

    private JComboBox<String> criarComboUf() {
        JComboBox<String> combo = new JComboBox<>(new String[]{
            "Selecione...", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA",
            "MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN",
            "RS","RO","RR","SC","SP","SE","TO"
        });

        combo.setFont(ModernTheme.FONT_REGULAR);
        combo.setBackground(Color.WHITE);
        combo.setBorder(ModernTheme.createRoundedBorder(ModernTheme.BORDER_RADIUS_SMALL));
        combo.setPreferredSize(new Dimension(200, ModernTheme.INPUT_HEIGHT));

        return combo;
    }

    private JCheckBox criarCheckNewsletter() {
        JCheckBox check = new JCheckBox("Desejo receber newsletter com ofertas e novidades");
        check.setFont(ModernTheme.FONT_REGULAR);
        check.setForeground(ModernTheme.TEXT_SECONDARY);
        check.setBackground(ModernTheme.PANEL_BG);
        check.setBorder(BorderFactory.createEmptyBorder(ModernTheme.PADDING_SMALL, 0, 0, 0));

        return check;
    }

    // Getters para acessar os campos do formulário
    public ModernComponents.ModernTextField getCampoNome() {
        return campoNome;
    }

    public ModernComponents.ModernTextField getCampoEmail() {
        return campoEmail;
    }

    public ModernComponents.ModernTextField getCampoCpf() {
        return campoCpf;
    }

    public JCheckBox getCheckNewsletter() {
        return checkNewsletter;
    }

    public JRadioButton getRadioMasc() {
        return radioMasc;
    }

    public JRadioButton getRadioFem() {
        return radioFem;
    }

    public JRadioButton getRadioOutro() {
        return radioOutro;
    }

    public JComboBox<String> getComboUf() {
        return comboUf;
    }
    
    // Método para limpar todos os campos do formulário
    public void limparCampos() {
        campoNome.setText("");
        campoEmail.setText("");
        campoCpf.setText("");
        checkNewsletter.setSelected(false);
        radioMasc.setSelected(false);
        radioFem.setSelected(false);
        radioOutro.setSelected(false);
        comboUf.setSelectedIndex(0);
    }
}


