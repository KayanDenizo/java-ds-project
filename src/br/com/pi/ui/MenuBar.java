// Vinicius
package br.com.pi.ui;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private JMenu menuArquivo;
    private JMenu menuAjuda;
    private JMenuItem itemNovo;
    private JMenuItem itemTodos;
    private JMenuItem itemTermos;
    private JMenuItem itemDuvidas;

    public MenuBar() {
        initComponents();
    }

    private void initComponents() {
        // Cria os menus
        menuArquivo = new JMenu("Arquivo");
        menuAjuda = new JMenu("Ajuda");

        // Cria os itens do menu Arquivo
        itemNovo = new JMenuItem("Novo");
        itemTodos = new JMenuItem("Todos");

        // Cria os itens do menu Ajuda
        itemTermos = new JMenuItem("Termos de Uso");
        itemDuvidas = new JMenuItem("Dúvidas Frequentes");

        // Cria os atalhos (mnemônicos e aceleradores)
        menuArquivo.setMnemonic(KeyEvent.VK_A);
        itemNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        itemTodos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        // Adiciona eventos aos itens do menu Arquivo
        itemNovo.addActionListener(e -> JOptionPane.showMessageDialog(null, "Novo Arquivo Criado."));
        itemTodos.addActionListener(e -> JOptionPane.showMessageDialog(null, "Lista de Todos os Arquivos: \n"));

        // Adiciona itens ao menu Arquivo
        menuArquivo.add(itemNovo);
        menuArquivo.addSeparator();
        menuArquivo.add(itemTodos);

        // Adiciona eventos aos itens do menu Ajuda
        itemTermos.addActionListener(e -> JOptionPane.showMessageDialog(null, "Termos de Uso Do Sistema(...)"));
        itemDuvidas.addActionListener(e -> JOptionPane.showMessageDialog(null, "Dúvidas Frequentes: \n 1º Duvida \n 2º Duvida \n (...)"));

        // Adiciona itens ao menu Ajuda
        menuAjuda.add(itemTermos);
        menuAjuda.add(itemDuvidas);

        // Adiciona os menus na barra de menus (ordem: Arquivo primeiro, depois Ajuda)
        add(menuArquivo);
        add(menuAjuda);
    }

    // Getters para acessar os itens de menu (caso precise adicionar mais funcionalidades)
    public JMenuItem getItemNovo() {
        return itemNovo;
    }

    public JMenuItem getItemTodos() {
        return itemTodos;
    }

    public JMenuItem getItemTermos() {
        return itemTermos;
    }

    public JMenuItem getItemDuvidas() {
        return itemDuvidas;
    }
}


