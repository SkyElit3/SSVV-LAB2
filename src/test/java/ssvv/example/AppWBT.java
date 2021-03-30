package ssvv.example;

import org.junit.Test;
import ssvv.example.domain.*;
import ssvv.example.repository.*;
import ssvv.example.service.*;
import ssvv.example.validation.*;

public class AppWBT {
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

    private Tema getValidAssignmentInput() {
        return new Tema("1","VVSSlab3",6,4);
    }

    private Tema getInvalidAssignmentInput_nullNrTema() {
        return new Tema(null,"VVSSlab3",6,4);
    }


    @Test
    public void addAssignmentValidInput()
    {
        initializeData();
        Tema testAssignment = getValidAssignmentInput();
        try{
            testService.addTema(testAssignment);
            assert(true);
        }catch (Exception ex){
            assert(false);
        }
    }

    @Test
    public void addAssignmentInvalidInput_NullNrTema()
    {
        initializeData();
        Tema testAssignment = getInvalidAssignmentInput_nullNrTema();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }



}