// Kayan
package br.com.pi.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ClientePanel extends JPanel {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoCpf;
    private JCheckBox checkNewsletter;
    private JRadioButton radioMasc;
    private JRadioButton radioFem;
    private JRadioButton radioOutro;
    private JComboBox<String> comboUf;

    public ClientePanel() {
        setLayout(new BorderLayout(8, 8));

        JPanel painelFormulario = criarPainelFormulario();

        add(painelFormulario, BorderLayout.CENTER);
    }

    private JPanel criarPainelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Dados do Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        panel.add(new JLabel("Nome:"), gbc);
        campoNome = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(campoNome, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Email:"), gbc);
        campoEmail = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panel.add(campoEmail, gbc);

        // CPF
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("CPF:"), gbc);
        campoCpf = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        panel.add(campoCpf, gbc);

        // Sexo (radio buttons)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        panel.add(new JLabel("Sexo:"), gbc);
        JPanel sexoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioMasc = new JRadioButton("Masculino");
        radioFem = new JRadioButton("Feminino");
        radioOutro = new JRadioButton("Outro");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(radioMasc);
        grupoSexo.add(radioFem);
        grupoSexo.add(radioOutro);
        sexoPanel.add(radioMasc);
        sexoPanel.add(radioFem);
        sexoPanel.add(radioOutro);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        panel.add(sexoPanel, gbc);

        // UF (combo box)
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.0;
        panel.add(new JLabel("UF:"), gbc);
        comboUf = new JComboBox<>(new String[]{
                "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA",
                "MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN",
                "RS","RO","RR","SC","SP","SE","TO"
        });
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0;
        panel.add(comboUf, gbc);

        // Newsletter (checkbox)
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.0;
        panel.add(new JLabel("Receber newsletter:"), gbc);
        checkNewsletter = new JCheckBox("Sim");
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0;
        panel.add(checkNewsletter, gbc);

        return panel;
    }

    // Getters para acessar os campos do formulário
    public JTextField getCampoNome() {
        return campoNome;
    }

    public JTextField getCampoEmail() {
        return campoEmail;
    }

    public JTextField getCampoCpf() {
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
}


