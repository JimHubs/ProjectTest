package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

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
    void opretDosis() {
    }

    @Test
    void getDoser() {
    }

    @Test
    void samletDosis() {
    }

    @Test
    void doegnDosis() {
        ArrayList<Dosis> doser = new ArrayList<>();

        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,14), LocalDate.of(2025,3,28), laegemiddel, doser);


        assertEquals(0, dagligSkaev.doegnDosis());
    }
}