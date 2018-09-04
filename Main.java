import java.util.ArrayList;
import java.util.List;

/*
    Magazyn
    by Michal Mitros s16614

    Dodatkowe funkcjonalnosci i struktury wykorzystane w kodzie:
    - printf do wypisywania
    - regex do sprawdzania poprawnosci numeru PESEL i formatu dat
    - wzorzec projektowy (fabryka abstrakcyjna) do tworzenia przemiotow
 */

public class Main {

    public static void main(String[] args) {
        Osoba o = null;
        Osoba o2 = null;
        Osoba o3 = null;
        Osoba o4 = null;
        Osoba o5 = null;
        try {
            o = new Osoba("Jan", "Kowalski", "79021533648", "Borkowa 5");
            o2 = new Osoba("Adam", "Nowak", "02310515643", "Lesna 12");
            o3 = new Osoba("Karol", "Zalewski", "89031654698", "Mazowiecka 41/38");
            o4 = new Osoba("Anna", "Slowacka", "91022464579", "Nowa 2");
            o5 = new Osoba("Agata", "Lis", "00321156468", "Krotka 1");
        } catch(UnsuitablePeselException upe) {
            System.out.println(upe);
        }

        if(o != null && o2 != null && o3 != null && o4 != null && o5 != null) {
            List<Osoba> osoby = new ArrayList<Osoba>();
            osoby.add(o);
            osoby.add(o2);
            osoby.add(o3);
            osoby.add(o4);
            osoby.add(o5);
            List<Pomieszczenie> pomieszczenia = new ArrayList<Pomieszczenie>();
            pomieszczenia.add(new Pomieszczenie(3.5));
            pomieszczenia.add(new Pomieszczenie(4.2));
            pomieszczenia.add(new Pomieszczenie(5.1));
            pomieszczenia.add(new Pomieszczenie(1.3));
            pomieszczenia.add(new Pomieszczenie(10.5));
            pomieszczenia.add(new Pomieszczenie(3.7));
            pomieszczenia.add(new Pomieszczenie(6.7));
            pomieszczenia.add(new Pomieszczenie(3.5));
            pomieszczenia.add(new Pomieszczenie(12.6));
            pomieszczenia.add(new Pomieszczenie(9.1));

            pomieszczenia.get(1).setCzyWylaczone(true);
            pomieszczenia.get(6).setCzyWylaczone(true);

            Magazyn magazyn = new Magazyn(pomieszczenia, osoby);

            try {
                magazyn.wynajmij(pomieszczenia.get(3), osoby.get(4), "01.04.2018", 30);
                magazyn.wynajmij(pomieszczenia.get(5), osoby.get(4), "01.04.2018", 30);
                magazyn.wynajmij(pomieszczenia.get(0), osoby.get(4), "01.04.2018", 30);
            } catch(Exception e) {
                System.out.printf("%s\n", e);
            }

            while(true) {
                Menu.showMenu(magazyn);
            }

        }



    }
}
