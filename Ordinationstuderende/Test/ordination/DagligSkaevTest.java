package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    ArrayList<Dosis> testDoser = new ArrayList<>();

    Laegemiddel testMiddel = new Laegemiddel("Fent", 0.1,0.2,0.4,"Test");
    DagligSkaev testSkaev = new DagligSkaev(LocalDate.of(2025,9,15), LocalDate.of(2025,9,16), testMiddel, testDoser);

    @org.junit.jupiter.api.Test
    void DagligSkaev_OpretDosis() {

        ArrayList<Dosis> doser = new ArrayList<>();

        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28), laegemiddel, doser);

        dagligSkaev.opretDosis(LocalTime.of(8,0),5);
        dagligSkaev.opretDosis(LocalTime.of(15,0), 7);

        assertEquals(12, dagligSkaev.samletDosis());
        assertEquals(0.8, dagligSkaev.doegnDosis());

    }

    @Test
    void opretDosis_NULL_tid() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            testSkaev.opretDosis(null, 2);
        });
        assertEquals("Tid må ikke være null.", exception.getMessage());
    }

    @Test
    void opretDosis_0_Antal() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
        testSkaev.opretDosis(LocalTime.of(2,30), 0);
        });
        assertEquals("Antal skal være > 0.", exception.getMessage());
    }

    @Test
    void opretDosis_negativ_Antal() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            testSkaev.opretDosis(LocalTime.of(2,30), -1);
        });
        assertEquals("Antal skal være > 0.", exception.getMessage());
    }

    @Test
    void samletDosis() {
        testSkaev.opretDosis(LocalTime.of(2,30), 2);
        double resultat = testSkaev.samletDosis();
        assertEquals(2, resultat);
    }

    @Test
    void doegnDosis() {
        ArrayList<Dosis> doser = new ArrayList<>();

        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28), laegemiddel, doser);


        assertEquals(0, dagligSkaev.doegnDosis());
    }
}