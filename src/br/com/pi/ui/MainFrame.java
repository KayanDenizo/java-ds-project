// Thayna
package br.com.pi.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MenuBar menuBar;
    private TabbedPaneComponent tabbedPaneComponent;
    
    public MainFrame() {
        initComponents();
        setupFrame();
    }
    
    private void initComponents() {
        // Cria e adiciona a barra de menus
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
        
        // Cria e adiciona o componente de abas
        tabbedPaneComponent = new TabbedPaneComponent();
        add(tabbedPaneComponent.getTabbedPane(), BorderLayout.CENTER);
    }
    
    private void setupFrame() {
        setTitle("Sistema de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
    }
    
    public MenuBar getCustomMenuBar() {
        return menuBar;
    }
    
    public TabbedPaneComponent getTabbedPaneComponent() {
        return tabbedPaneComponent;
    }
}
