import java.util.regex.Pattern;

public class Osoba implements Comparable<Osoba> {

    private static int licznikID = 1;
    private final int id;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String dataUrodzenia;
    private String dataPierwszegonajmu = "";
    private String adres;

    public Osoba(String imie, String nazwisko, String pesel, String adres) throws UnsuitablePeselException {
        if (Pattern.matches("^[0-9]{2}(([02][0-9])|([13][0-2]))[0-3][0-9]{6}$", pesel)) {
            id = licznikID++;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.adres = adres;
            this.pesel = pesel;
            obliczDateUrodzenia();
        } else {
            throw new UnsuitablePeselException();
        }
    }

    public void wynajmij(String data) {
        if(dataPierwszegonajmu.equals("")) {
            dataPierwszegonajmu = data;
        }
    }

    private void obliczDateUrodzenia() {
        StringBuilder sb = new StringBuilder();
        sb.append(pesel.charAt(4));
        sb.append(pesel.charAt(5));
        sb.append('.');
        if(pesel.charAt(2) > '1') {
            sb.append((char)(pesel.charAt(2)-2));
            sb.append(pesel.charAt(3));
        } else {
            sb.append(pesel.charAt(2));
            sb.append(pesel.charAt(3));
        }
        sb.append('.');
        if(pesel.charAt(2) > '1') {
            sb.append("20");
        } else {
            sb.append("19");
        }
        sb.append(pesel.charAt(0));
        sb.append(pesel.charAt(1));
        this.dataUrodzenia = sb.toString();
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public int getID() {
        return id;
    }

    public String getPesel() {
        return pesel;
    }

    public String getAdres() {
        return adres;
    }

    public String getDataPierwszegonajmu() throws NeverRentException {
        if(dataPierwszegonajmu.equals("")) {
            throw new NeverRentException();
        }

        return dataPierwszegonajmu;
    }

    @Override
    public int compareTo(Osoba o) {
        if(o.equals(this)) {
            return 0;
        }

        if(o.getNazwisko().equals(nazwisko)) {
            return imie.compareTo(o.getImie());
        }

        return nazwisko.compareTo(o.getNazwisko());
    }

    @Override
    public String toString() {
        return id + ". " + imie + " " + nazwisko + " " + dataUrodzenia;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Osoba) {
            return o.hashCode() == hashCode();
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 23 * hash + imie.hashCode();
        hash = 23 * hash + nazwisko.hashCode();
        hash = 23 * hash + pesel.hashCode();
        return hash;
    }
}