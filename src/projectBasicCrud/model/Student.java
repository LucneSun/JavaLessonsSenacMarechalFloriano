package projectBasicCrud.model;

public class Student {

    public String name;
    private String email;
    protected int registration;

    String sex;

    public void SetName(String name){
        this.name = name;
    }

    public void SetEmail(String email){
        this.email = email;
    }

    public void SetRegistration(int registration){
        this.registration = registration;
    }

    public void SetSex(String sex){
        this.sex = sex;
    }

    public String Apresentation(){
        return "Ola! Meu nome eh:" + name + ", meu email eh:" + email + ", meu sexo eh:" + sex + ", minha matricula eh: " + registration + "...";
    }



    public void WatchingTheClass(){
        System.out.println("the student is watching the class");
    }

    public String AskQuestion(){
        return "The student is asking a question.";
    }

}
