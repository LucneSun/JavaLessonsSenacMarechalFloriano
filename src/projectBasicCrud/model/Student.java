package projectBasicCrud.model;

public class Student {

    public String name;
    private String email;
    protected int registration;

    String sex;


    public void setName(String name){this.name = name;}

    public void setEmail(String email){
        this.email = email;
    }

    public void setRegistration(int registration){
        this.registration = registration;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getName(){return  this.name;}

    public String getEmail(){return  this.email;}

    public String getSex(){return  this.sex;}

    public int getRegistration(){return this.registration;}

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
