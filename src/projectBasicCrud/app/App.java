package projectBasicCrud.app;

import projectBasicCrud.model.Student;

import javax.swing.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Student student;
        student = new Student();

        var name = JOptionPane.showInputDialog(null, "Informe o nome: ", "Input Name", JOptionPane.QUESTION_MESSAGE);
        student.SetName(name);
        var email = JOptionPane.showInputDialog(null, "Informe o email: ", "Input Email", JOptionPane.QUESTION_MESSAGE);
        student.SetEmail(email);
        var sex = JOptionPane.showInputDialog(null, "Informe o sexo: ", "Input Sex", JOptionPane.QUESTION_MESSAGE);
        student.SetSex(sex);
        var registration = JOptionPane.showInputDialog(null, "Informe a mátricula: ", "Input Registration", JOptionPane.QUESTION_MESSAGE);
        student.SetRegistration(Integer.parseInt(registration));

        System.out.println(student.Apresentation());
    }
}
