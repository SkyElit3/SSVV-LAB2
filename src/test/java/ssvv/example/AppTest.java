package ssvv.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ssvv.example.domain.Student;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
public class AppTest
{
    Service testService;

    private void initializeData(){
        if (testService == null) {
            StudentValidator studentValidator = new StudentValidator();
            TemaValidator temaValidator = new TemaValidator();

            String filenameStudent = "src/test/java/ssvv/example/fisiere/Studenti.xml";
            String filenameTema = "src/test/java/ssvv/example/fisiere/Teme.xml";
            String filenameNota = "src/test/java/ssvv/example/fisiere/Note.xml";

            StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
            TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
            NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
            NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
            testService = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        }
    }

    private Student getValidStudentInput(){
        return new Student("idTest1", "Dan", 935, "dan@ubbcluj.ro");
    }

    private Student getInvalidStudentInput_missingEmail(){
        return new Student("idTest2", "Dan",935,"");
    }

    @Test
    public void addStudentValidInput_ShouldReturnTrue()
    {
        initializeData();
        Student testStudent = getValidStudentInput();
        try{
            Student addedTestStudent = testService.addStudent(testStudent);
            assert(addedTestStudent.getID().equals(testStudent.getID()));
        }catch (Exception ex){
            assert(false);
        }
    }

    @Test
    public void addStudentInvalidInput_ShouldReturnFalse()
    {
        initializeData();
        Student testStudent = getInvalidStudentInput_missingEmail();
        try{
            testService.addStudent(testStudent);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

}