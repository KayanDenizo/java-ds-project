// Julia
package br.com.pi.ui;

import javax.swing.*;

public class DialogHandler {
    
    // Mensagem de sucesso para cadastro de cliente
    public static void showClienteCadastrado() {
        JOptionPane.showMessageDialog(
            null,
            "Cadastro de cliente realizado com sucesso.",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // Mensagem de sucesso para cadastro de produto
    public static void showProdutoCadastrado() {
        JOptionPane.showMessageDialog(
            null,
            "Cadastro de produto realizado com sucesso.",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // Diálogo de confirmação para limpar formulário
    public static int showConfirmarLimpar() {
        return JOptionPane.showConfirmDialog(
            null,
            "Deseja mesmo limpar tudo?",
            "Limpar tudo",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
    }
    
    // Mensagem de informação genérica
    public static void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            null,
            message,
            title,
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // Mensagem de aviso genérica
    public static void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            null,
            message,
            title,
            JOptionPane.WARNING_MESSAGE
        );
    }
    
    // Mensagem de erro genérica
    public static void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            null,
            message,
            title,
            JOptionPane.ERROR_MESSAGE
        );
    }
}
