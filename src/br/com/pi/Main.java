package br.com.pi;

import br.com.pi.ui.MainFrame;
import br.com.pi.ui.TabbedPaneComponent;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

