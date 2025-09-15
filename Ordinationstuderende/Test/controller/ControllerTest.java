package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void dagligFast_opret() {
        //Arrange

        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligFast dagligFast = new DagligFast(LocalDate.of(2025,03,14), LocalDate.of(2025,03,17),laegemiddel, 2,1,3,1);

        //Act

        //Assert
        assertEquals(7.0, dagligFast.doegnDosis(), 0.001);   // 2+1+3+1
        assertEquals(28.0, dagligFast.samletDosis(), 0.001); // 7 * 4 dage

    }

    @Test
    void PN_Opret() {
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        PN pn = new PN(LocalDate.of(2025,03,14), LocalDate.of(2025,03,28),laegemiddel, 3);

        PN pn1 = new PN(LocalDate.of(2025,03,14), LocalDate.of(2025,03,28),laegemiddel, 3);

        assertEquals(3, pn.getAntalEnheder());
    }

    @org.junit.jupiter.api.Test
    void doegnDosis() {
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        PN pn = new PN(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28),laegemiddel, 3);

        pn.givDosis(LocalDate.of(2025,3,16));
        pn.givDosis(LocalDate.of(2025,3,17));
        pn.givDosis(LocalDate.of(2025,3,19));
        pn.givDosis(LocalDate.of(2025,3,18));

        assertEquals(3, pn.doegnDosis());
    }

    @org.junit.jupiter.api.Test
    void samletDosis() {
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        PN pn = new PN(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28),laegemiddel, 3);

        pn.givDosis(LocalDate.of(2025,3,16));
        pn.givDosis(LocalDate.of(2025,3,17));
        pn.givDosis(LocalDate.of(2025,3,19));
        pn.givDosis(LocalDate.of(2025,3,18));

        assertEquals(12, pn.samletDosis());
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt() {
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn() {
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
    void opretPatient() {
        Patient faktiskpatient = controller.opretPatient("1", "John", 10.0);
        Patient forventetPatient = new Patient("1", "John", 10.0);
        assertEquals(forventetPatient.getCprnr(), faktiskpatient.getCprnr());
        assertEquals(forventetPatient.getNavn(), faktiskpatient.getNavn());
        assertEquals(forventetPatient.getVaegt(), faktiskpatient.getVaegt());
    }

    @org.junit.jupiter.api.Test
    void opretLaegemiddel() {
        Laegemiddel faktiskLaegemiddel = controller.opretLaegemiddel("Fent", 0.1,
                0.2,0.4, "Test");
        Laegemiddel forventetLaegemiddel = new Laegemiddel("Fent", 0.1,
                0.2, 0.4, "Test");
        assertEquals(forventetLaegemiddel.getNavn(), faktiskLaegemiddel.getNavn());
        assertEquals(forventetLaegemiddel.getEnhedPrKgPrDoegnLet(),faktiskLaegemiddel.getEnhedPrKgPrDoegnLet());
        assertEquals(forventetLaegemiddel.getEnhedPrKgPrDoegnNormal(), faktiskLaegemiddel.getEnhedPrKgPrDoegnNormal());
        assertEquals(forventetLaegemiddel.getEnhedPrKgPrDoegnTung(),faktiskLaegemiddel.getEnhedPrKgPrDoegnTung());
        assertEquals(forventetLaegemiddel.getEnhed(),faktiskLaegemiddel.getEnhed());

    }
}