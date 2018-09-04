public class Motocykl extends Pojazd {

    private TypMotocyklu typMotocyklu;
    private boolean posiadaHomologacje;

    public Motocykl(double powierzchnia, TypMotocyklu typMotocyklu, boolean posiadaHomologacje) {
        super(TypPojazdu.MOTOCYKL, powierzchnia);
        this.typMotocyklu = typMotocyklu;
        this.posiadaHomologacje = posiadaHomologacje;
    }

    @Override
    public String getSzczegoly() {
        return typPojazdu + " (" + powierzchnia + " m^3), " + typMotocyklu + ", " + (posiadaHomologacje ? "posiada homologacje" : "nie posiada homologacji");
    }
}

enum TypMotocyklu {
    TYP1, TYP2      // Nie znam sie, nie wiem jakie sa typy motocykli, nie bylo w specyfikacji wymagan, to sprawa hardware'owa...
}