package projectBasicCrud.services;

import projectBasicCrud.model.Student;
import projectBasicCrud.persist.StudentDAO;

import java.util.List;

public class StudentService {
private StudentDAO dao;

public StudentService(){
    dao = new StudentDAO();
}

public boolean save(Student student){
   return dao.save(student);
}

public List<Student> findAll(){
    return dao.findAll();
}

public boolean deleteById(int id){
    return dao.deleteById(id);
}

public Student findById(int id){
    return dao.findById(id);
}

public Student update(Student student){
    return (dao.update(student));
}

public boolean deleteAll(){
    return dao.deleteAll();
}
}
