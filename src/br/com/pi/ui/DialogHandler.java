// Julia
package br.com.pi.ui;

import javax.swing.JOptionPane;

public class DialogHandler {
    
    // TODO: Implementar caixas de diálogo (mensagens e confirmações)
    public static void main (String[] args) {
        JOptionPane.showMessageDialog(null, "Cadastro de cliente realizado com sucesso.");
        JOptionPane.showMessageDialog(null, "Cadastro de produto realizado com sucesso.");

        //parte que eu não coloquei no protótipo
        JOptionPane.showConfirmDialog(null,"Deseja mesmo limpar tudo?", "Limpar tudo", JOptionPane.OK_CANCEL_OPTION);
    }


    
}
