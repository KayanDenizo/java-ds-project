package br.com.pi;

import br.com.pi.ui.LoginFrame;
import br.com.pi.ui.theme.ModernTheme;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Inicializar tema moderno
        ModernTheme.setGlobalLookAndFeel();

        SwingUtilities.invokeLater(() -> {
            // Mostra a tela de login primeiro
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

