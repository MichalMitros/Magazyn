public class ZwyklyPrzedmiot extends Przedmiot {

    private String nazwa;

    public ZwyklyPrzedmiot(String nazwa, double powierzchnia) {
        super(TypPrzedmiotu.ZWYKLY, powierzchnia);
        this.nazwa = nazwa;
    }

    @Override
    public String getSzczegoly() {
        return nazwa + " (" + powierzchnia + " m^3)";
    }
}
