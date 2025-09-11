package ordination;

import java.lang.reflect.Array;

public class DagligFast extends Ordination{
    // TODO

    private Dosis doser[] = new Dosis[4];

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
