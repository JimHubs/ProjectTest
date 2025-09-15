package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;
class ControllerTest {

    private Controller controller;
    private Laegemiddel standardLaegemiddel;

    @BeforeEach
    void setUp(){
        controller = Controller.getController();
        standardLaegemiddel = new Laegemiddel(
                "Fent", 0.1,0.15,
                0.3,"NULL");
    }

    @org.junit.jupiter.api.Test
    void getController() {
    }

    @org.junit.jupiter.api.Test
    void getTestController() {
    }

    @org.junit.jupiter.api.Test
    void opretPNOrdination() {

    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {
    }

    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination() {
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt() {

    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_NULL_Patient() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
        controller.anbefaletDosisPrDoegn(null,standardLaegemiddel);
        });
        assertEquals("Parametre må ikke være null", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_NULL_Laegemiddel() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Patient patient = new Patient("1","John", 10.0);
            controller.anbefaletDosisPrDoegn(patient,null);
        });
        assertEquals("Parametre må ikke være null", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Let_24() {
        Patient patient = new Patient("1","John", 24);
        double forventetResultat = patient.getVaegt() *
                standardLaegemiddel.getEnhedPrKgPrDoegnLet();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                standardLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Normal_25() {
        Patient patient = new Patient("1","John", 25);
        double forventetResultat = patient.getVaegt() *
                standardLaegemiddel.getEnhedPrKgPrDoegnNormal();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                standardLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Normal_119() {
        Patient patient = new Patient("1","John", 119);
        double forventetResultat = patient.getVaegt() *
                standardLaegemiddel.getEnhedPrKgPrDoegnNormal();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                standardLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Normal_120() {
        Patient patient = new Patient("1","John", 120);
        double forventetResultat = patient.getVaegt() *
                standardLaegemiddel.getEnhedPrKgPrDoegnNormal();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                standardLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn_Tung_121() {
        Patient patient = new Patient("1","John", 121);
        double forventetResultat = patient.getVaegt() *
                standardLaegemiddel.getEnhedPrKgPrDoegnTung();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                standardLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

    @org.junit.jupiter.api.Test
    void getAllPatienter() {
    }

    @org.junit.jupiter.api.Test
    void getAllLaegemidler() {
    }

    @org.junit.jupiter.api.Test
    void opretPatient() {
    }

    @org.junit.jupiter.api.Test
    void opretLaegemiddel() {
    }
}