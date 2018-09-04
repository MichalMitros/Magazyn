import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Magazyn {
    private List<Pomieszczenie> pomieszczenia;
    private List<Osoba> osoby;
    private Map<Pomieszczenie, Osoba> wynajecia;

    public Magazyn() {
        this(new ArrayList<Pomieszczenie>(), new ArrayList<Osoba>());

    }

    public Magazyn(List<Pomieszczenie> pomieszczenia, List<Osoba> osoby) {
        this.pomieszczenia = pomieszczenia;
        this.osoby = osoby;
        wynajecia = new HashMap<Pomieszczenie, Osoba>();
        this.pomieszczenia.sort((p1 , p2) -> {
            if(p1.getPowierzchnia() > p2.getPowierzchnia()) {
                return 1;
            } else if(p1.getPowierzchnia() < p2.getPowierzchnia()) {
                return -1;
            }
            return 0;
        });
    }

    public void wynajmij(Pomieszczenie p, Osoba o, String data, int iloscDni) throws AlreadyRentedException, NotAvaiableException {
        if(wynajecia.containsKey(p)) {
            throw new AlreadyRentedException();
        }

        if(p.getCzyWylaczone()) {
            throw new NotAvaiableException();
        }

        p.wynajmij(data, iloscDni);
        o.wynajmij(data);
        wynajecia.put(p, o);
    }

    public List<Pomieszczenie> getPomieszczenia() {
        return pomieszczenia;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public Map<Pomieszczenie, Osoba> getWynajecia() {
        return wynajecia;
    }

    public static void przeniesZawartosc(Pomieszczenie p, ArrayList<Pomieszczenie> lp) {
        lp.remove(p);
        for(Przedmiot prz : p.getPrzedmioty()) {
            for(int i=0; i<lp.size(); i++) {
                try {
                    lp.get(i).wlozPrzedmiot(prz);
                    i = lp.size();
                }catch(TooManyThingsException tmte) {
                    ;
                }
            }
        }
    }

    public void zapiszStanMagazynu() {
        try {
            PrintWriter out = new PrintWriter("stanMagazynu.txt");
            for(Osoba o : osoby) {
                if(wynajecia.containsValue(o)) {
                    try {
                        out.printf("\nOSOBA:\nID: %d\nImie: %s\nNazwisko: %s\nData urodzenia: %s\nPESEL: %s\nAdres: %s\nData pierwszego najmu: %s\n",
                                o.getID(), o.getImie(), o.getNazwisko(), o.getDataUrodzenia(),
                                o.getPesel(), o.getAdres(), o.getDataPierwszegonajmu());
                    } catch (NeverRentException nre) {
                        out.printf("\nOSOBA\nID: %d\nImie: %s\nNazwisko: %s\nData urodzenia: %s\nPESEL: %s\nAdres: %s\n",
                                o.getID(), o.getImie(), o.getNazwisko(), o.getDataUrodzenia(), o.getPesel(), o.getAdres());
                    }

                    for (Pomieszczenie p : pomieszczenia) {
                        if (wynajecia.containsKey(p)) {
                            if (wynajecia.get(p).equals(o)) {
                                out.printf("\tPOMIESZCZENIE:\n");
                                out.printf("\t%s\n", p);
                                if (p.getPrzedmioty() != null && p.getPrzedmioty().size() != 0) {
                                    out.printf("\t\tPRZEDMIOTY:\n");
                                    for (int i = 0; i < p.getPrzedmioty().size(); i++) {
                                        out.printf("\t\t%d. %s\n", i + 1, p.getPrzedmioty().get(i).getSzczegoly());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            out.printf("\n\nWSZYSTKIE OSOBY:\n");
            for(Osoba o : osoby) {
                try {
                    out.printf("\nID: %d\nImie: %s\nNazwisko: %s\nData urodzenia: %s\nPESEL: %s\nAdres: %s\nData pierwszego najmu: %s\n",
                            o.getID(), o.getImie(), o.getNazwisko(), o.getDataUrodzenia(),
                            o.getPesel(), o.getAdres(), o.getDataPierwszegonajmu());
                } catch(NeverRentException nre) {
                    out.printf("\nID: %d\nImie: %s\nNazwisko: %s\nData urodzenia: %s\nPESEL: %s\nAdres: %s\n",
                            o.getID(), o.getImie(), o.getNazwisko(), o.getDataUrodzenia(), o.getPesel(), o.getAdres());
                }
            }
            out.printf("\n\nWSZYSTKIE POMIESZCZENIA:\n");
            for(Pomieszczenie p : pomieszczenia) {
                out.printf("%s%s\n", p, p.getCzyWylaczone() ? " (wylaczone z uzytku)" : "");
            }
            out.close();
            System.out.printf("\nZapisano stan magazynu.\n");
        }catch(FileNotFoundException fnfe) {
            System.out.printf("\n%s\n", fnfe);
        }

    }

}
