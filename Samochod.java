public class Samochod extends Pojazd {

    private TypSilnika typSilnika;
    private double pojemnoscSilnika;

    public Samochod(double powierzchnia, TypSilnika typSilnika, double pojemnoscSilnika) {
        super(TypPojazdu.SAMOCHOD, powierzchnia);
        this.typSilnika = typSilnika;
        this.pojemnoscSilnika = pojemnoscSilnika;
    }


    @Override
    public String getSzczegoly() {
        return typPojazdu + " (" + powierzchnia + " m^3), silnik: " + typSilnika +", pojemnosc: " + pojemnoscSilnika;
    }
}

enum TypSilnika {
    GAZ, BENZYNA, DIESEL, HYBRYDA
}
