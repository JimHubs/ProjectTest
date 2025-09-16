package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {

    private Controller controller;
    private Laegemiddel testLaegemiddel;

    @BeforeEach
    void setUp(){
        controller = Controller.getController();
        testLaegemiddel = new Laegemiddel(
                "Fent", 0.1,0.15,
                0.3,"NULL");
    }

    @org.junit.jupiter.api.Test
    void TC14_ordinationPNAnvendt_NULL_Parameter() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.ordinationPNAnvendt(null, null);
        });
        assertEquals("Parameter må ikke være null",  exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void TC15_ordinationPNAnvendt_OutOf_Parameter() {
        Patient patient = new Patient("1","John", 100);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PN pn = controller.opretPNOrdination(
                    LocalDate.of(2000,2,2),
                    LocalDate.of(2001,2,2),
                    patient, testLaegemiddel,1);
            controller.ordinationPNAnvendt(pn, LocalDate.of(2025,9,15));
        });
        assertEquals("Datoen er ikke indefor ordinations gyldighedsperiode",  exception.getMessage());
    }

    @Test
    void TC26_anbefaletDosisPrDoegn_NULL_Patient() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.anbefaletDosisPrDoegn(null, testLaegemiddel);
        });
        assertEquals("Parametre må ikke være null", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void TC27_anbefaletDosisPrDoegn_NULL_Laegemiddel() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Patient patient = new Patient("1","John", 10.0);
            controller.anbefaletDosisPrDoegn(patient,null);
        });
        assertEquals("Parametre må ikke være null", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void TC21_anbefaletDosisPrDoegn_Let_24() {
        Patient patient = new Patient("1","John", 24);
        double forventetResultat = patient.getVaegt() *
                testLaegemiddel.getEnhedPrKgPrDoegnLet();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                testLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void TC22_anbefaletDosisPrDoegn_Normal_25() {
        Patient patient = new Patient("1","John", 25);
        double forventetResultat = patient.getVaegt() *
                testLaegemiddel.getEnhedPrKgPrDoegnNormal();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                testLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void TC23_anbefaletDosisPrDoegn_Normal_119() {
        Patient patient = new Patient("1","John", 119);
        double forventetResultat = patient.getVaegt() *
                testLaegemiddel.getEnhedPrKgPrDoegnNormal();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                testLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void TC24_anbefaletDosisPrDoegn_Normal_120() {
        Patient patient = new Patient("1","John", 120);
        double forventetResultat = patient.getVaegt() *
                testLaegemiddel.getEnhedPrKgPrDoegnNormal();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                testLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @org.junit.jupiter.api.Test
    void TC25_anbefaletDosisPrDoegn_Tung_121() {
        Patient patient = new Patient("1","John", 121);
        double forventetResultat = patient.getVaegt() *
                testLaegemiddel.getEnhedPrKgPrDoegnTung();
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient,
                testLaegemiddel);
        assertEquals(forventetResultat, faktiskResultat);
    }

    @Test
    void TC20_antalOrdinationerPrVægtPrLægemiddel() {
        // Arrange
        Laegemiddel standardLaegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

        Patient patient = controller.opretPatient("1", "John", 121);
        PN ordination = controller.opretPNOrdination(
                LocalDate.of(2025,3,14), LocalDate.of(2025,3,20),
                patient, standardLaegemiddel, 2);

        // Act
        int antal = controller.antalOrdinationerPrVægtPrLægemiddel(60, 121, standardLaegemiddel);

        // Assert
        assertEquals(1, antal);
    }

    @org.junit.jupiter.api.Test
    void TC28_opretPatient() {
        Patient faktiskpatient = controller.opretPatient("1", "John", 10.0);
        Patient forventetPatient = new Patient("1", "John", 10.0);
        assertEquals(forventetPatient.getCprnr(), faktiskpatient.getCprnr());
        assertEquals(forventetPatient.getNavn(), faktiskpatient.getNavn());
        assertEquals(forventetPatient.getVaegt(), faktiskpatient.getVaegt());
    }

    @org.junit.jupiter.api.Test
    void TC29_opretLaegemiddel() {
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

    @Test
    void TC12_opretPNOrdination() {
        Patient patient = new Patient("1", "John" , 100);
        PN faktiskPNOrd = controller.opretPNOrdination(LocalDate.of(2025,9,16),
                LocalDate.of(2025,9,25),
                patient, testLaegemiddel, 2);
        PN forventetPN = new PN(LocalDate.of(2025,9,16),
                LocalDate.of(2025,9,25), testLaegemiddel, 2);
        assertEquals(forventetPN.getLaegemiddel(), faktiskPNOrd.getLaegemiddel());
        assertEquals(forventetPN.getStartDen(),faktiskPNOrd.getStartDen());
        assertEquals(forventetPN.getSlutDen(),faktiskPNOrd.getSlutDen());
        assertEquals(forventetPN.getAntalEnheder(), faktiskPNOrd.getAntalEnheder());
    }

    @Test
    void TC16_opretDagligFastOrdination() {
        Patient patient = new Patient("1", "John" , 100);
        DagligFast faktiskFastOrd = controller.opretDagligFastOrdination(LocalDate.of(2025,9,16),
                LocalDate.of(2025,9,25),
                patient, testLaegemiddel, 2,2,2,2);
        DagligFast forventetFast = new DagligFast(LocalDate.of(2025,9,16),
                LocalDate.of(2025,9,25), testLaegemiddel, 2,2,2,2);
        assertEquals(forventetFast.getLaegemiddel(), faktiskFastOrd.getLaegemiddel());
        assertEquals(forventetFast.getStartDen(),faktiskFastOrd.getStartDen());
        assertEquals(forventetFast.getSlutDen(),faktiskFastOrd.getSlutDen());
        assertEquals(forventetFast.samletDosis(), faktiskFastOrd.samletDosis());
    }

    @Test
    void TC18_testOpretDagligSkaevOrdination() {
        Patient patient = new Patient("1", "John" , 100);
        LocalTime[] times = new LocalTime[0];
        double[] doser = new double[0];
        ArrayList<Dosis> dosiser = new ArrayList<>();
        DagligSkaev faktiskSkaevOrd = controller.opretDagligSkaevOrdination(LocalDate.of(2025,9,16),
                LocalDate.of(2025,9,25),
                patient, testLaegemiddel, times, doser);
        DagligSkaev forventetSkaev = new DagligSkaev(LocalDate.of(2025,9,16),
                LocalDate.of(2025,9,25), testLaegemiddel, dosiser);
        assertEquals(forventetSkaev.getLaegemiddel(), faktiskSkaevOrd.getLaegemiddel());
        assertEquals(forventetSkaev.getStartDen(), faktiskSkaevOrd.getStartDen());
        assertEquals(forventetSkaev.getSlutDen(), faktiskSkaevOrd.getSlutDen());
        assertEquals(forventetSkaev.samletDosis(), faktiskSkaevOrd.samletDosis());
    }

    @Test
    void TC13_opretPNOrdination_StartDatoError() {
        Patient patient = new Patient("1","John", 10.0);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PN pn = controller.opretPNOrdination(LocalDate.of(2025,3,29),
                    LocalDate.of(2025,3,24), patient, testLaegemiddel, 2);
        });

        assertEquals("Start dato må ikke være efter slut dato", exception.getMessage());
    }


    @Test
    void TC17_opretDagligFastOrdination_StartDatoError(){
        Patient patient = new Patient("1","John", 10.0);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2025,3,29),
                    LocalDate.of(2025,3,24), patient, testLaegemiddel, 2,1,3,1 );
        });

        assertEquals("Startdato er efter slutden", exception.getMessage());
    }

    @Test
    void TC19_opretDagligSkaevOrdination() {
        Patient patient = new Patient("1","John", 10.0);
        LocalTime[] times = new LocalTime[0];
        double[] doser = new double[0];
        Exception exception = assertThrows(RuntimeException.class, () -> {
            DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2025,3,29),
                    LocalDate.of(2025,3,24), patient, testLaegemiddel, times, doser);
        });

        assertEquals("Start dato må ikke være efter slut dato", exception.getMessage());
    }
}