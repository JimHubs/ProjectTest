package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;

public class DagligFast extends Ordination{
    // TODO

    private Dosis doser[] = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, Dosis[] doser) {
        super(startDen, slutDen, laegemiddel);
        this.doser=doser;
    }

    public Dosis[] getDoser() {
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
