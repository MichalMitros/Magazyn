import java.util.ArrayList;
import java.util.List;

public class Pomieszczenie {

    private static int licznikID = 1;
    private final int id;
    private final double powierzchnia;
    private boolean czyWylaczone = false;
    private ArrayList<Przedmiot> przedmioty;
    private String dataNajmu = "";
    private int iloscDni = 0;

    public Pomieszczenie(double powierzchnia) {
        id = licznikID++;

        this.powierzchnia = powierzchnia;
    }

    public Pomieszczenie(double dlugosc, double szerokosc, double wysokosc) {
        this(dlugosc*szerokosc*wysokosc);
    }

    public boolean getCzyWylaczone() {
        return czyWylaczone;
    }

    public int getID() {
        return id;
    }

    public double getPowierzchnia() {
        return powierzchnia;
    }

    public void wynajmij(String dataNajmu, int iloscDni) {
        this.dataNajmu = dataNajmu;
        this.iloscDni = iloscDni;
    }

    public void setCzyWylaczone(boolean czyWylaczone) {
        this.czyWylaczone = czyWylaczone;
    }

    public String getDataNajmu() {

        return dataNajmu;
    }

    public int getIloscDni() {
        return this.iloscDni;
    }

    public void wlozPrzedmiot(Przedmiot p) throws TooManyThingsException {
        if (przedmioty == null) {
            przedmioty = new ArrayList<Przedmiot>();
        }
        if(getWolnaPowierzchnia() >= p.getPowierzchnia()) {
            przedmioty.add(p);
        } else {
            throw new TooManyThingsException();
        }
    }

    public void zmienPowierzchniePrzedmiotu(int index, double nowaPowierzchnia) throws TooManyThingsException {
        if (przedmioty == null) {
            przedmioty = new ArrayList<Przedmiot>();
        }
        if(getWolnaPowierzchnia() >= nowaPowierzchnia-przedmioty.get(index).getPowierzchnia()) {
            przedmioty.get(index).setPowierzchnia(nowaPowierzchnia);
        } else {
            throw new TooManyThingsException();
        }
    }

    public ArrayList<Przedmiot> getPrzedmioty() {
        return przedmioty;
    }

    public double getWolnaPowierzchnia() {
        if(przedmioty == null) {
            return powierzchnia;
        }
        double zajetaPowierzchnia= 0;
        for(Przedmiot p : przedmioty) {
            zajetaPowierzchnia += p.getPowierzchnia();
        }
        return powierzchnia - zajetaPowierzchnia;
    }

    @Override
    public String toString() {
        return id + " (" + powierzchnia + " m^3)";
    }

    @Override
    public int hashCode() {
        return 23 * id + 71;
    }
}
