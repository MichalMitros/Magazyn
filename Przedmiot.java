public abstract class Przedmiot {

    protected double powierzchnia;
    protected TypPrzedmiotu typ;

    protected Przedmiot(TypPrzedmiotu typ, double powierzchnia) {
        this.powierzchnia = powierzchnia;
        this.typ = typ;
    }

    public void setPowierzchnia(double powierzchnia) {
        this.powierzchnia = powierzchnia;
    }

    public double getPowierzchnia() {
        return powierzchnia;
    }

    public abstract String getSzczegoly();
}
