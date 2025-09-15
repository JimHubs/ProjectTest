package controller;

import ordination.DagligFast;
import ordination.Laegemiddel;
import ordination.Ordination;
import ordination.PN;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {

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