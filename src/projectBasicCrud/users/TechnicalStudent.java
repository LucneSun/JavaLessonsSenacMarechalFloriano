package projectBasicCrud.users;

import projectBasicCrud.model.Student;

public class TechnicalStudent extends Student {
    private String specialization;

    @Override
    public String getName() {
        return "Aluno curso técnico " + name;
    }

    public String getSpecialization(){
        return specialization;
    }

    public void setEspecialization( String specialization){
        this.specialization = specialization;
    }
}
