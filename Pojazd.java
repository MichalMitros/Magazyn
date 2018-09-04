public abstract class Pojazd extends Przedmiot {

    protected TypPojazdu typPojazdu;

    public Pojazd(TypPojazdu typPojazdu, double powierzchnia) {
        super(TypPrzedmiotu.POJAZD, powierzchnia);
        this.typPojazdu = typPojazdu;
    }
}
