package br.com.pi.ui;

import br.com.pi.dao.ClienteDAO;
import br.com.pi.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteEditDialog extends JDialog {
    private Cliente cliente;
    private ClienteDAO clienteDAO;

    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoCpf;
    private JRadioButton radioMasc;
    private JRadioButton radioFem;
    private JRadioButton radioOutro;
    private JComboBox<String> comboUf;
    private JCheckBox checkNewsletter;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public ClienteEditDialog(Window parent, Cliente cliente, ClienteDAO clienteDAO) {
        super(parent, "Editar Cliente", ModalityType.APPLICATION_MODAL);
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;

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
        panel.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
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

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
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
        campoNome.setText(cliente.getNome());
        campoEmail.setText(cliente.getEmail());
        campoCpf.setText(cliente.getCpf());

        // Selecionar sexo
        switch (cliente.getSexo()) {
            case "Masculino":
                radioMasc.setSelected(true);
                break;
            case "Feminino":
                radioFem.setSelected(true);
                break;
            case "Outro":
                radioOutro.setSelected(true);
                break;
        }

        comboUf.setSelectedItem(cliente.getUf());
        checkNewsletter.setSelected(cliente.isNewsletter());
    }

    private void salvarCliente() {
        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String cpf = campoCpf.getText().trim();

        // Validação básica
        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha Nome, Email e CPF.",
                "Validação",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Obter sexo
        String sexo = "Não informado";
        if (radioMasc.isSelected()) {
            sexo = "Masculino";
        } else if (radioFem.isSelected()) {
            sexo = "Feminino";
        } else if (radioOutro.isSelected()) {
            sexo = "Outro";
        }

        String uf = (String) comboUf.getSelectedItem();
        boolean newsletter = checkNewsletter.isSelected();

        // Atualizar cliente
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setSexo(sexo);
        cliente.setUf(uf);
        cliente.setNewsletter(newsletter);

        if (clienteDAO.editar(cliente)) {
            JOptionPane.showMessageDialog(
                this,
                "Cliente atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Erro ao atualizar cliente.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
