package ssvv.example;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import ssvv.example.domain.*;
import ssvv.example.repository.*;
import ssvv.example.service.*;
import ssvv.example.validation.*;

import java.time.LocalDate;

public class BigBangTest {
    static Service testService;

    @BeforeClass
    public static void initializeData() {
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

    private Tema getValidAssignmentInput() {
        return new Tema("test1", "VVSSlab3", 6, 4);
    }

    @Test
    public void BBIaddAssignmentValidInput() {
        Tema testAssignment = getValidAssignmentInput();
        try {
            testService.addTema(testAssignment);
            assert (true);
        } catch (Exception ex) {
            assert (false);
        }
    }


    private Student getValidStudentInput() {
        return new Student("idTest1", "Dan", 935, "dan@ubbcluj.ro");
    }

    @Test
    public void BBIaddStudentValidInput() {
        Student testStudent = getValidStudentInput();
        try {
            Student addedTestStudent = testService.addStudent(testStudent);
            assert (addedTestStudent.getID().equals(testStudent.getID()));
        } catch (Exception ex) {
            assert (false);
        }
    }

    private Nota getValidNotaInput() {
        return new Nota("test1", "idTest1", "test1", 10.0,
                LocalDate.of(2018, 11, 12));
    }

    @Test
    public void BBIaddNotaValidInput() {
        Nota testNota = getValidNotaInput();
        try{
            double addedTestNota = testService.addNota(testNota, "OK");
            assert(addedTestNota == 10.0);
        } catch(Exception ex){
            assert(false);
        }
    }

    @Test
    public void BigBangIntegrationValidInputAll(){
        BBIaddStudentValidInput();
        BBIaddAssignmentValidInput();
        BBIaddNotaValidInput();
    }
}