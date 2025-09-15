package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    // TODO Færdig

    private ArrayList<Dosis> doser;

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, ArrayList<Dosis> doser) {
        super(startDen, slutDen, laegemiddel);
        this.doser = doser;
    }


    public void opretDosis(LocalTime tid, double antal) {
        // TODO Færdig
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
        double sum = 0;
        for (Dosis d : doser) {
            sum += d.getAntal();
        }
        return sum;
    }

    @Override
    public double doegnDosis() {
        if (doser.isEmpty()) return 0;
        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return "DagligSkaev";
    }
}
