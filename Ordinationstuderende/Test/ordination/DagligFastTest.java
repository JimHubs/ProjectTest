package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void dagligFast_doegnDosis() {
        //Arrange

        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligFast dagligFast = new DagligFast(LocalDate.of(2025,03,14), LocalDate.of(2025,03,17),laegemiddel, 2,1,3,1);

        //Act

        //Assert
        assertEquals(7.0, dagligFast.doegnDosis(), 0.001);   // 2+1+3+1
    }

    @Test
    void dagligFast_samletDosis() {
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        DagligFast dagligFast = new DagligFast(LocalDate.of(2025,03,14), LocalDate.of(2025,03,17),laegemiddel, 2,1,3,1);

        //Act

        //Assert
        assertEquals(28.0, dagligFast.samletDosis(), 0.001); // 7 * 4 dage

    }
}