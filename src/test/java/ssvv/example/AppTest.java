package ssvv.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ssvv.example.domain.*;
import ssvv.example.repository.*;
import ssvv.example.service.*;
import ssvv.example.validation.*;

public class AppTest {
    Service testService;

    private void initializeData() {
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

    private Student getValidStudentInput() {
        return new Student("idTest1", "Dan", 935, "dan@ubbcluj.ro");
    }

    private Student getInvalidStudentInput_missingEmail() {
        return new Student("idTest2", "Dan", 935, "");
    }

    private Student getInvalidStudentInput_negativeGroup(){
        return new Student("idTest2", "Dan", -999, "ejfpjep@eif.com");
    }

    private Student getInvalidStudentInput_invalidName(){
        return new Student("idTest2", "", 935, "ejfpjep@eif.com");
    }

    @Test
    public void addStudentValidInput_ShouldReturnTrue() {
        initializeData();
        Student testStudent = getValidStudentInput();
        try {
            Student addedTestStudent = testService.addStudent(testStudent);
            assert (addedTestStudent.getID().equals(testStudent.getID()));
        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    public void addStudentInvalidInput_ShouldReturnFalse() {
        initializeData();
        Student testStudent = getInvalidStudentInput_missingEmail();
        try {
            testService.addStudent(testStudent);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentInvalidEmail_ShouldReturnFalse() {
        initializeData();
        Student testStudent = getInvalidStudentInput_negativeGroup();
        try {
            testService.addStudent(testStudent);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentInvalidName_ShouldReturnFalse() {
        initializeData();
        Student testStudent = getInvalidStudentInput_invalidName();
        try {
            testService.addStudent(testStudent);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    private Student getInvalidStudentInput_emptyId(){
        return new Student("", "Dan", 935, "dan@ubbcluj.ro");
    }

    private Student getInvalidStudentInput_nullId(){
        return new Student(null, "Dan", 935, "dan@ubbcluj.ro");
    }
    @Test
    public void addStudentInvalidInput_EmptyId_ShouldReturnFalse()
    {
        initializeData();
        Student testStudent = getInvalidStudentInput_emptyId();
        try{
            testService.addStudent(testStudent);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    @Test
    public void addStudentInvalidInput_NullId_ShouldReturnFalse()
    {
        initializeData();
        Student testStudent = getInvalidStudentInput_nullId();
        try{
            testService.addStudent(testStudent);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

}