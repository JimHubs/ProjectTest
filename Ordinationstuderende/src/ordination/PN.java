package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination{

    private double antalEnheder;

    private ArrayList<LocalDate> givetDoser = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDen, slutDen, laegemiddel);
        this.antalEnheder=antalEnheder;
    }


    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */

    public boolean givDosis(LocalDate givesDen) {
        // TODO Færdig

        if(givesDen == null) return false;

        if(!givesDen.isBefore(getStartDen()) && !givesDen.isAfter(getSlutDen())){
            givetDoser.add(givesDen);
            return true;
        }
        return false;
    }

    public double doegnDosis() {
        // TODO Færdig
        if(givetDoser.isEmpty()) return 0.0;
        return samletDosis()/givetDoser.size();
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        // TODO Færdig
        return givetDoser.size() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        // TODO Færdig
        return givetDoser.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public Laegemiddel getLaegemiddel() {
        return super.getLaegemiddel();
    }
}
