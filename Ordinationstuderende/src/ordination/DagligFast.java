package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination{
    // TODO Færdig

    private Dosis doser[] = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel,
                      double morgen, double middag, double aften, double nat) {
        super(startDen, slutDen, laegemiddel);
        doser[0] = new Dosis(LocalTime.of(8,0), morgen);
        doser[1] = new Dosis(LocalTime.of(12,0), middag);
        doser[2] = new Dosis(LocalTime.of(18,0), aften);
        doser[3] = new Dosis(LocalTime.of(22,0), nat);
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * antalDage();
    }

    @Override
    public double doegnDosis() {
        double sum = 0;
        for (Dosis dosis : doser){
            if(dosis != null){
                sum += dosis.getAntal();
            }
        }
        return sum;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
}
