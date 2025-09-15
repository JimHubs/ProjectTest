package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    // TODO

    private ArrayList<Dosis> doser;

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, ArrayList<Dosis> doser) {
        super(startDen, slutDen, laegemiddel);
        this.doser = doser;
    }


    public void opretDosis(LocalTime tid, double antal) {
        // TODO
        if (tid == null) {
            throw new IllegalArgumentException("Tid må ikke være null.");
        }
        if (antal <= 0) {
            throw new IllegalArgumentException("Antal skal være > 0.");
        }

        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }

    public ArrayList<Dosis> getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return "";
    }
}
