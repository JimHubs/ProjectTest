package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;

public class DagligFast extends Ordination{
    // TODO Færdig

    private Dosis doser[] = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel,
                      double morgen, Dosis middag, Dosis aften, Dosis nat) {
        super(startDen, slutDen, laegemiddel);
        doser[0] = new Dosis();
        this.doser[1] = middag;
        this.doser[2] = aften;
        this.doser[3] = nat;
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
