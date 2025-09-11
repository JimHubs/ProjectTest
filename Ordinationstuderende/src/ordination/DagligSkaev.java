package ordination;

import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    // TODO

    private ArrayList<Dosis> doser = new ArrayList<>();


    public void opretDosis(LocalTime tid, double antal) {
        // TODO
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
