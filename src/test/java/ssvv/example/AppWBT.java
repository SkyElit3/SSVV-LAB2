package ssvv.example;

import org.junit.BeforeClass;
import org.junit.Test;
import ssvv.example.domain.*;
import ssvv.example.repository.*;
import ssvv.example.service.*;
import ssvv.example.validation.*;

public class AppWBT {
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
        return new Tema("1","VVSSlab3",6,4);
    }

    private Tema getInvalidAssignmentInput_nullNrTema() {
        return new Tema(null,"VVSSlab3",6,4);
    }


    @Test
    public void addAssignmentValidInput()
    {
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
        Tema testAssignment = getInvalidAssignmentInput_nullNrTema();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    private Tema getInvalidAssignmentInput_EmptyDescriere() {
        return new Tema("1","",6,4);
    }

    @Test
    public void addAssignmentInvalidInput_EmptyDescriere()
    {
        Tema testAssignment = getInvalidAssignmentInput_EmptyDescriere();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    private Tema getInvalidAssignmentInput_HigherDeadline() {
        return new Tema("1","VVSSlab3",20,4);
    }

    @Test
    public void addAssignmentInvalidInput_HigherDeadline()
    {
        Tema testAssignment = getInvalidAssignmentInput_HigherDeadline();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    private Tema getInvalidAssignmentInput_LowerDeadline() {
        return new Tema("1","VVSSlab3",0,4);
    }


    @Test
    public void addAssignmentInvalidInput_LowerDeadline()
    {
        Tema testAssignment = getInvalidAssignmentInput_LowerDeadline();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    private Tema getInvalidAssignmentInput_HigherPrimire() {
        return new Tema("1","VVSSlab3",6,20);
    }

    @Test
    public void addAssignmentInvalidInput_HigherPrimire()
    {
        Tema testAssignment = getInvalidAssignmentInput_HigherPrimire();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    private Tema getInvalidAssignmentInput_LowerPrimire() {
        return new Tema("1","VVSSlab3",6,0);
    }

    @Test
    public void addAssignmentInvalidInput_LowerPrimire()
    {
        Tema testAssignment = getInvalidAssignmentInput_LowerPrimire();
        try{
            testService.addTema(testAssignment);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

}