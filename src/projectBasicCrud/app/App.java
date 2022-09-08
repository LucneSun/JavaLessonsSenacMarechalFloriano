package projectBasicCrud.app;

import projectBasicCrud.model.Student;
import projectBasicCrud.services.StudentService;
import projectBasicCrud.users.TechnicalStudent;

import javax.swing.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        JOptionPane.showMessageDialog(null, "Bem vindo ao sistema da escola", "Boas vindas", JOptionPane.INFORMATION_MESSAGE);
        var choice = "Empty";

        var service = new StudentService();
        Student student;
        student = new Student();

        while (choice != "QUIT") {

            choice = JOptionPane.showInputDialog(null, "Escolha \n C: cadastrar um novo aluno \n E: editar um aluno \n D: deletar um aluno \n M: mostrar todos os alunos \n DA: deletar todos os alunos \n QUIT: para sair", "Input Name", JOptionPane.QUESTION_MESSAGE);
            switch (choice){
                case "C":
                    var name = JOptionPane.showInputDialog(null, "Informe o nome: ", "Input Name", JOptionPane.QUESTION_MESSAGE);
                    student.setName(name);
                    var email = JOptionPane.showInputDialog(null, "Informe o email: ", "Input Email", JOptionPane.QUESTION_MESSAGE);
                    student.setEmail(email);
                    var sex = JOptionPane.showInputDialog(null, "Informe o sexo: ", "Input Sex", JOptionPane.QUESTION_MESSAGE);
                    student.setSex(sex);
                    var registration = JOptionPane.showInputDialog(null, "Informe a mátricula: ", "Input Registration", JOptionPane.QUESTION_MESSAGE);
                    student.setRegistration(Integer.parseInt(registration));

                    var response = service.save(student);
                    var situation = "Ocorreu uma falha na gravação. Verifique o log";
                    var iconStatus = JOptionPane.ERROR;

                    if (response){
                        situation = "Gravado com sucesso";
                        iconStatus = JOptionPane.INFORMATION_MESSAGE;
                    }

                    var msg ="Situação: " + situation + "\n" + student.Apresentation();
                    JOptionPane.showMessageDialog(null, msg, "Resposta", iconStatus);
                    break;
                case "M":
                    var listResponse = service.findAll().stream().map(student1 -> "Student.name: " + student1.getName() + "\n" + "Student.email: " + student1.getEmail() + "\n" + "Student.registration" + student1.getRegistration() + "\n" + "Student.sex: " + student1.getSex() + "\n" ).toList();
                    JOptionPane.showMessageDialog(null, listResponse, "Listagem de alunos", JOptionPane.INFORMATION_MESSAGE);
                        break;

                case "E":
                    var id = JOptionPane.showInputDialog(null, "Informe o id: ", "Editar aluno", JOptionPane.QUESTION_MESSAGE);

                    var newName = JOptionPane.showInputDialog(null, "Informe novo nome: ", "Novo nome", JOptionPane.QUESTION_MESSAGE);
                    var newSex = JOptionPane.showInputDialog(null, "Informe novo sexo: M ou F ", "Novo sexo", JOptionPane.QUESTION_MESSAGE);
                    var newEmail = JOptionPane.showInputDialog(null, "Informe novo e-mail: ", "Novo email", JOptionPane.QUESTION_MESSAGE);
                    var newRegistration = JOptionPane.showInputDialog(null, "Informe nova matricula: ", "Nova matricula", JOptionPane.QUESTION_MESSAGE);

                    student = new Student();
                    student.setEmail(newEmail);
                    student.setName(newName);
                    student.setSex(newSex);
                    student.setRegistration(Integer.parseInt(newRegistration));



                    response = service.update(student, Integer.parseInt(id)) != null;
                    situation = "Ocorreu uma falha na edição. Verifique o log";
                    iconStatus = JOptionPane.ERROR;

                    if (response){
                        situation = "Edição realizada com sucesso";
                        iconStatus = JOptionPane.INFORMATION_MESSAGE;
                    }

                    JOptionPane.showMessageDialog(null, situation, "Resposta", iconStatus);
                    break;

                case "D":

                     id = JOptionPane.showInputDialog(null, "Informe o id: ", "Excluir aluno", JOptionPane.QUESTION_MESSAGE);


                     response = service.deleteById(Integer.parseInt(id));
                     situation = "Ocorreu uma falha na exclusão. Verifique o log";
                     iconStatus = JOptionPane.ERROR;

                    if (response){
                        situation = "Exclusão realizada com sucesso";
                        iconStatus = JOptionPane.INFORMATION_MESSAGE;
                    }

                    JOptionPane.showMessageDialog(null, situation, "Resposta", iconStatus);
                    break;

                case "DA":

                         response = service.deleteAll();
                         situation = "Ocorreu uma falha na exclusão. Verifique o log";
                         iconStatus = JOptionPane.ERROR;

                        if (response) {
                            situation = "Exclusão realizada com sucesso";
                            iconStatus = JOptionPane.INFORMATION_MESSAGE;
                        }

                        JOptionPane.showMessageDialog(null, situation, "Resposta", iconStatus);

                    break;

                case "QUIT":
                        choice = "QUIT";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Comando inválido", "Tente novamente", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }



    }


}
