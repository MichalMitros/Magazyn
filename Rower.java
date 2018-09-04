public class Rower extends Pojazd {

    private final int liczbaPrzerzutek;
    private final TypHamulcow typHamulcow;
    private final boolean czyAmortyzowany;
    private final int liczbaAmortyzatorow;

    public Rower(double powierzchnia, int liczbaPrzerzutek, TypHamulcow typHamulcow, boolean czyAmortyzowany, int liczbaAmortyzatorow) {
        super(TypPojazdu.ROWER, powierzchnia);
        this.liczbaPrzerzutek = liczbaPrzerzutek;
        this.typHamulcow = typHamulcow;
        this.czyAmortyzowany = czyAmortyzowany;
        if(czyAmortyzowany) {
            this.liczbaAmortyzatorow = liczbaAmortyzatorow;
        } else {
            this.liczbaAmortyzatorow = 0;
        }
    }

    @Override
    public String getSzczegoly() {
        if(!czyAmortyzowany) {
            return typPojazdu + " (" + powierzchnia + " m^3), przerzutek: " + liczbaPrzerzutek +", hamulce " + typHamulcow + ", nieamortyzowany";
        }
        return typPojazdu + " (" + powierzchnia + " m^3), przerzutek: " + liczbaPrzerzutek +", hamulce " + typHamulcow + ", amortyzatorow: " + liczbaAmortyzatorow;
    }
}

enum TypHamulcow {
    TARCZOWE, ZACISKOWE
}
