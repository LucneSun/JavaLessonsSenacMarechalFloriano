package projectBasicCrud.app;

import javax.swing.*;

public class ControleFluxo {

    public static  void main(String[] args){

        float total = 0.0f;
        float numAtual = Float.parseFloat(JOptionPane.showInputDialog(null, "Informe um numero diferente de 0 que sera somado: ", "Soma numeros", JOptionPane.QUESTION_MESSAGE));
        total += numAtual;

        while (numAtual != 0){
            numAtual = Float.parseFloat(JOptionPane.showInputDialog(null, "Informe um numero diferente de 0 que sera somado: ", "Soma numeros", JOptionPane.QUESTION_MESSAGE));
            total += numAtual;
        }

        System.out.println("O total dos numeros somados foi de: " + total);
    }
}
