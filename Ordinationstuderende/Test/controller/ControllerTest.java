package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_NULL_Parameter() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.ordinationPNAnvendt(null, null);
        });
        assertEquals("Parameter må ikke være null",  exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt_OutOf_Parameter() {
        Patient patient = new Patient("1","John", 100);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PN pn = controller.opretPNOrdination(
                    LocalDate.of(2000,2,3),
                    LocalDate.of(2001,2,2),
                    patient,standardLaegemiddel,1);
            controller.ordinationPNAnvendt(pn, LocalDate.of(2025,9,15));
        });
        assertEquals("Datoen er ikke indefor ordinations gyldighedsperiode",  exception.getMessage());
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

    @Test
    void opretDagligFastOrdination_StartDatoError(){
        Patient patient = new Patient("1","John", 10.0);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2025,3,29),
                    LocalDate.of(2025,3,24), patient, standardLaegemiddel, 2,1,3,1 );
        });

        assertEquals("Startdato er efter slutden", exception.getMessage());
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

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        // Arrange
        Laegemiddel standardLaegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

        Patient patient = controller.opretPatient("1", "John", 121);
        PN ordination = controller.opretPNOrdination(
                LocalDate.of(2025,3,14),
                LocalDate.of(2025,3,20),
                patient,
                standardLaegemiddel,
                2
        );

        // Act
        int antal = controller.antalOrdinationerPrVægtPrLægemiddel(60, 121, standardLaegemiddel);

        // Assert
        assertEquals(1, antal);
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