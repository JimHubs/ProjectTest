package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    @Test
    void PN_Opret() {
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        PN pn = new PN(LocalDate.of(2025,03,14), LocalDate.of(2025,03,28),laegemiddel, 3);

        PN pn1 = new PN(LocalDate.of(2025,03,14), LocalDate.of(2025,03,28),laegemiddel, 3);

        assertEquals(3, pn.getAntalEnheder());
    }

    @Test
    void givDosis() {
    }

    @Test
    void doegnDosis() {
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        PN pn = new PN(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28),laegemiddel, 3);

        pn.givDosis(LocalDate.of(2025,3,16));
        pn.givDosis(LocalDate.of(2025,3,17));
        pn.givDosis(LocalDate.of(2025,3,19));
        pn.givDosis(LocalDate.of(2025,3,18));

        assertEquals(3, pn.doegnDosis());
    }

    @Test
    void samletDosis() {
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        PN pn = new PN(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28),laegemiddel, 3);

        pn.givDosis(LocalDate.of(2025,3,16));
        pn.givDosis(LocalDate.of(2025,3,17));
        pn.givDosis(LocalDate.of(2025,3,19));
        pn.givDosis(LocalDate.of(2025,3,18));

        assertEquals(12, pn.samletDosis());
    }
}