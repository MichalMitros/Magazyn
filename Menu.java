import java.util.*;
import java.util.regex.Pattern;

public class Menu {

    private static Scanner input;
    private static Osoba osoba;
    private static Pomieszczenie pomieszczenie;

    public static void showMenu(Magazyn magazyn) {

        if(osoba == null) {
            osobaMenu(magazyn);
        } else if(pomieszczenie == null) {
            pomieszczenieMenu(magazyn);
        } else {
            przedmiotMenu(magazyn);
        }
    }

    private static void przedmiotMenu(Magazyn magazyn) {
        int opcja;
        input = new Scanner(System.in);
        System.out.printf("\nOsoba: %s\n", osoba);
        System.out.printf("Pomieszczenie: %s\n", pomieszczenie + ", dotepne " + pomieszczenie.getWolnaPowierzchnia() + " m^3");
        System.out.printf("---------------------\n");
        System.out.printf("Dostepne opcje:\n");
        System.out.printf("1. Wyswietl przedmioty\n");
        System.out.printf("2. Wloz przedmiot\n");
        System.out.printf("3. Wyjmij przedmiot\n");
        System.out.printf("4. Zmien powierzchnie przedmiotu\n");
        System.out.printf("5. Sprawdz termin najmu\n");
        System.out.printf("6. Zmien pomieszczenie\n");
        System.out.printf("0. Wyjdz\n");
        System.out.printf("---------------------\n");
        do {
            System.out.printf("Wybrana opcja: ");
            try {
                opcja = input.nextInt();
            } catch(InputMismatchException e) {
                opcja = -1;
                input = new Scanner(System.in);
            }
            if(opcja < 0 || opcja > 6) {
                System.out.printf("(podaj numer opcji z przedzialu 0-6)\n");
            }
        } while(opcja < 0 || opcja > 6);
        input = null;

        switch(opcja) {
            case 1:
                wypiszPrzedmioty();
                break;
            case 2:
                try {
                    pomieszczenie.wlozPrzedmiot(PrzedmiotFactory.stworzPrzedmiot());
                    System.out.printf("\nWlozono nowy przedmiot\n");
                }catch(TooManyThingsException tmte) {
                    System.out.printf("\n%s\n", tmte);
                }
                break;
            case 3:
                wyjmijPrzedmiot();
                break;
            case 4:
                zmienPowierzchniePrzedmiotu();
                break;
            case 5:
                sprawdzTerminNajmu(magazyn);
                break;
            case 6:
                pomieszczenie = null;
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private static void pomieszczenieMenu(Magazyn magazyn) {
        List<Pomieszczenie> lp = magazyn.getPomieszczenia();
        Map<Pomieszczenie, Osoba> w = magazyn.getWynajecia();
        int opcja;
        input = new Scanner(System.in);
        System.out.printf("\nOsoba: %s\n", osoba);
        System.out.printf("Pomieszczenie: %s\n", pomieszczenie == null ? "?" : (pomieszczenie + ", dsotepne " + pomieszczenie.getWolnaPowierzchnia() + " m^3"));
        System.out.printf("---------------------\n");
        System.out.printf("Dostepne opcje:\n");
        System.out.printf("1. Wybierz pomieszczenie\n");
        System.out.printf("2. Wyswietl pomieszczenia\n");
        System.out.printf("3. Wyswietl wolne pomieszczenia\n");
        System.out.printf("4. Wynajmij nowe pomieszczenie\n");
        System.out.printf("5. Wypisz szczegoly osoby\n");
        System.out.printf("6. Zmien osobe\n");
        System.out.printf("0. Wyjdz\n");
        System.out.printf("---------------------\n");
        do {
            System.out.printf("Wybrana opcja: ");
            try {
                opcja = input.nextInt();
            } catch(InputMismatchException e) {
                opcja = -1;
                input = new Scanner(System.in);
            }
            if(opcja < 0 || opcja > 6) {
                System.out.printf("(podaj numer opcji z przedzialu 0-6)\n");
            }
        } while(opcja < 0 || opcja > 6);
        input = null;

        switch(opcja) {
            case 1:
                wybierzPomieszczenie(lp, w);
                break;
            case 2:
                wypiszPomieszczenia(lp, w);
                break;
            case 3:
                wypiszWolnePomieszczenia(lp, w);
                break;
            case 4:
                wynajmijPomieszczenie(magazyn);
                break;
            case 5:
                wypiszSzczegolyOsoby();
                break;
            case 6:
                osoba = null;
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private static void osobaMenu(Magazyn magazyn) {
        List<Pomieszczenie> lp = magazyn.getPomieszczenia();
        List<Osoba> lo = magazyn.getOsoby();
        Map<Pomieszczenie, Osoba> w = magazyn.getWynajecia();
        int opcja;
        input = new Scanner(System.in);
        System.out.printf("\nOsoba: %s\n", osoba == null ? "?" : osoba);
        System.out.printf("---------------------\n");
        System.out.printf("Dostepne opcje:\n");
        System.out.printf("1. Wybierz osobe\n");
        System.out.printf("2. Wyswietl osoby\n");
        System.out.printf("3. Wyswietl wolne pomieszczenia\n");
        System.out.printf("4. Zapisz stan magazynu do pliku\n");
        System.out.printf("0. Wyjdz\n");
        System.out.printf("---------------------\n");
        do {
            System.out.printf("Wybrana opcja: ");
            try {
                opcja = input.nextInt();
            } catch(InputMismatchException e) {
                opcja = -1;
                input = new Scanner(System.in);
            }
            if(opcja < 0 || opcja > 4) {
                System.out.printf("(podaj numer opcji z przedzialu 0-4)\n");
            }
        } while(opcja < 0 || opcja > 4);
        input = null;
        switch(opcja) {
            case 1:
                wybierzOsobe(lo);
                break;
            case 2:
                wypiszOsoby(lo);
                break;
            case 3:
            wypiszWolnePomieszczenia(lp, w);
                break;
            case 4:
                magazyn.zapiszStanMagazynu();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private static void wypiszPrzedmioty() {
        ArrayList<Przedmiot> przedmioty = pomieszczenie.getPrzedmioty();

        if(przedmioty != null && przedmioty.size() != 0) {
            przedmioty.sort((p1, p2) -> {
                if(p1.getPowierzchnia() != p2.getPowierzchnia()) {
                    return p1.getPowierzchnia() < p2.getPowierzchnia() ? 1 : -1;
                }
                return p1.getSzczegoly().toLowerCase().compareTo(p2.getSzczegoly().toLowerCase());
            });
            System.out.printf("\nPrzedmioty w  pomieszczeniu %s:\n", pomieszczenie);
            for (int i = 0; i < przedmioty.size(); i++) {
                System.out.printf("%d. %s\n", i+1, przedmioty.get(i).getSzczegoly());
            }
        }else {
            System.out.printf("\nW %s nie ma przedmiotow.\n", pomieszczenie);
        }

    }

    private static void zmienPowierzchniePrzedmiotu() {
        ArrayList<Przedmiot> przedmioty = pomieszczenie.getPrzedmioty();
        przedmioty.sort((p1, p2) -> {
            if(p1.getPowierzchnia() != p2.getPowierzchnia()) {
                return p1.getPowierzchnia() < p2.getPowierzchnia() ? 1 : -1;
            }
            return p1.getSzczegoly().toLowerCase().compareTo(p2.getSzczegoly().toLowerCase());
        });
        Scanner input = new Scanner(System.in);
        if(przedmioty.size() != 0) {
            System.out.printf("Przedmioty w %s:\n", pomieszczenie);
            for (int i = 0; i < przedmioty.size(); i++) {
                System.out.printf("%d. %s\n", i+1, przedmioty.get(i).getSzczegoly());
            }
            System.out.printf("\n(Aby wyjsc wpisz 0)\n");
            int index = 0;
            do {
                System.out.printf("Podaj id przedmiotu do zmiany powierzchni: ");
                try {
                    index = input.nextInt();
                } catch(InputMismatchException e) {
                    index = -1;
                    input = new Scanner(System.in);
                }
            }while(index < 0 || index > przedmioty.size());
            if(index == 0) {
                System.exit(0);
            }
            double nowaPowierzchnia;
            System.out.printf("\n(Aby wyjsc wpisz 0)\n");
            do {
                System.out.printf("Podaj nowa powierzchnie: ");
                try {
                    nowaPowierzchnia = input.nextDouble();
                } catch(InputMismatchException e) {
                    nowaPowierzchnia = -1;
                    input = new Scanner(System.in);
                }
            }while(nowaPowierzchnia < 0);
            if(nowaPowierzchnia == 0) {
                System.exit(0);
            }
            try {
               pomieszczenie.zmienPowierzchniePrzedmiotu(--index, nowaPowierzchnia);
                System.out.printf("\nZmieniono powierzchnie przedmiotu\n");
            } catch(TooManyThingsException tmte) {
                System.out.printf("\n%s\n", tmte);
            }
        }else {
            System.out.printf("W %s nie ma przedmiotow.\n", pomieszczenie);
        }
    }

    private static void wyjmijPrzedmiot() {
        ArrayList<Przedmiot> przedmioty = pomieszczenie.getPrzedmioty();
        przedmioty.sort((p1, p2) -> {
            if(p1.getPowierzchnia() != p2.getPowierzchnia()) {
                return p1.getPowierzchnia() < p2.getPowierzchnia() ? 1 : -1;
            }
            return p1.getSzczegoly().toLowerCase().compareTo(p2.getSzczegoly().toLowerCase());
        });
        Scanner input = new Scanner(System.in);
        if(przedmioty.size() != 0) {
            System.out.printf("\nPrzedmioty w %s:\n", pomieszczenie);
            for (int i = 0; i < przedmioty.size(); i++) {
                System.out.printf("%d. %s\n", i+1, przedmioty.get(i).getSzczegoly());
            }
            System.out.printf("\n(Aby wyjsc wpisz 0)\n");
            int index = 0;
            do {
                System.out.printf("Podaj id przedmiotu do wyjecia: ");
                try {
                    index = input.nextInt();
                } catch(InputMismatchException e) {
                    index = -1;
                    input = new Scanner(System.in);
                }
            }while(index < 0 || index > przedmioty.size());
            if(index == 0) {
                System.exit(0);
            }
            przedmioty.remove(--index);
            System.out.printf("\nWyjeto przedmiot\n");
        }else {
            System.out.printf("W %s nie ma przedmiotow.\n", pomieszczenie);
        }
    }

    private static void wypiszOsoby(List<Osoba> lo) {
        System.out.printf("\n-----------------------------------------------------------------------------\n");
        System.out.printf("| Dostepne osoby:                                                           |\n");
        System.out.printf("-----------------------------------------------------------------------------\n");
        System.out.printf("| ID |      IMIE      |       NAZWISKO       | DATA URODZENIA |    PESEL    |\n");
        System.out.printf("-----------------------------------------------------------------------------\n");
        for(Osoba o : lo) {
            System.out.printf("|%-4d| %-15s| %-21s|   %-13s| %s |\n", o.getID(), o.getImie(), o.getNazwisko(), o.getDataUrodzenia(), o.getPesel());
        }
        System.out.printf("-----------------------------------------------------------------------------\n");
    }

    private static void wypiszSzczegolyOsoby() {
        try {
            System.out.printf("\nID: %d\nImie: %s\nNazwisko: %s\nData urodzenia: %s\nPESEL: %s\nAdres: %s\nData pierwszego najmu: %s\n",
                    osoba.getID(), osoba.getImie(), osoba.getNazwisko(), osoba.getDataUrodzenia(),
                    osoba.getPesel(), osoba.getAdres(), osoba.getDataPierwszegonajmu());
        } catch(NeverRentException nre) {
            System.out.printf("\nID: %d\nImie: %s\nNazwisko: %s\nData urodzenia: %s\nPESEL: %s\nAdres: %s\n",
                    osoba.getID(), osoba.getImie(), osoba.getNazwisko(), osoba.getDataUrodzenia(), osoba.getPesel(), osoba.getAdres());
        }
    }

    private static void wybierzOsobe(List<Osoba> lo) {
        wypiszOsoby(lo);
        System.out.printf("(Aby wyjsc wpisz 0)\n");
        input = new Scanner(System.in);
        int id;
        int index = 0;
        do {
            System.out.printf("Podaj id wybranej osoby: ");
            try {
                id = input.nextInt();
            } catch(InputMismatchException e) {
                id = -1;
                input = new Scanner(System.in);
            }
            if(id == 0) {
                System.exit(0);
            }
            boolean znalezionoOsobe = false;
            for(Osoba o : lo) {
                if(o.getID() == id) {
                    znalezionoOsobe = true;
                    index = lo.indexOf(o);
                }
            }
            if(!znalezionoOsobe) {
                id = -1;
            }
        }while (id == -1);
        osoba = lo.get(index);
    }

    private static void wypiszPomieszczenia(List<Pomieszczenie> lp, Map<Pomieszczenie, Osoba> w) {
        List<Pomieszczenie> pomieszczeniaOsoby = getPomieszczeniaOsoby(lp, w);
        if(pomieszczeniaOsoby.size() != 0) {
            pomieszczeniaOsoby.sort((p1, p2) -> {
                if (p1.getPowierzchnia() > p2.getPowierzchnia()) {
                    return 1;
                } else if (p1.getPowierzchnia() < p2.getPowierzchnia()) {
                    return -1;
                }
                return 0;
            });
            System.out.printf("\n-------------------------------------------------------------------------------\n");
            System.out.printf("| Pomieszczenia nalezace do: %-48s |\n", osoba);
            System.out.printf("-------------------------------------------------------------------------------\n");
            System.out.printf("| ID |  POWIERZCHNIA (m^3)  | WOLNA POW. (m^3) |    DATA NAJMU    | ILOSC DNI |\n");
            System.out.printf("-------------------------------------------------------------------------------\n");
            for (Pomieszczenie p : pomieszczeniaOsoby) {
                System.out.printf("|%-4d| %-21f| %-16f |    %10s    | %-9d |\n", p.getID(), p.getPowierzchnia(), p.getWolnaPowierzchnia(), p.getDataNajmu(), p.getIloscDni());
            }
            System.out.printf("-------------------------------------------------------------------------------\n");
        } else {
            System.out.printf("\n%s nie posiada wynajetych pomieszczen.\n", osoba);
        }
    }

    private static void wypiszWolnePomieszczenia(List<Pomieszczenie> lp, Map<Pomieszczenie, Osoba> w) {
        ArrayList<Pomieszczenie> wolnePomieszczenia = getWolnePomieszczenia(lp, w);
        System.out.printf("\n---------------------------------------------------\n");
        System.out.printf("| Wolne pomieszczenia:                            |\n");
        System.out.printf("---------------------------------------------------\n");
        System.out.printf("| ID |  POWIERZCHNIA (m^3)  | WYLACZONE Z UZYTKU  |\n");
        System.out.printf("---------------------------------------------------\n");
        for(Pomieszczenie p : wolnePomieszczenia) {
            System.out.printf("|%-4d| %-21f|         %3s         |\n", p.getID(), p.getPowierzchnia(), p.getCzyWylaczone() ? "TAK" : "NIE");
        }
        System.out.printf("---------------------------------------------------\n");
    }

    private static void sprawdzTerminNajmu(Magazyn magazyn) {
        String data;
        input = new Scanner(System.in);
        do {
            System.out.printf("Podaj obecna date (DD.MM.YYYY): ");
            try {
                data = input.nextLine();
            } catch (InputMismatchException e) {
                data = "abc";
                input = new Scanner(System.in);
            }
        } while(!Pattern.matches("^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$", data));

        if(KalkulatorDat.obliczRozniceDni(pomieszczenie.getDataNajmu(), data) < pomieszczenie.getIloscDni()) {
            System.out.printf("\nNajem sie skonczyl, przedmioty zostana przeniesone, lub usuniete.\n");
            magazyn.przeniesZawartosc(pomieszczenie, getPomieszczeniaOsoby(magazyn.getPomieszczenia(), magazyn.getWynajecia()));
            pomieszczenie.getPrzedmioty().clear();
            magazyn.getWynajecia().remove(pomieszczenie);
            pomieszczenie = null;
        }
    }

    private static void wybierzPomieszczenie(List<Pomieszczenie> lp, Map<Pomieszczenie, Osoba> w) {
        wypiszPomieszczenia(lp, w);
        List<Pomieszczenie> pomieszczeniaOsoby = getPomieszczeniaOsoby(lp, w);
        if(pomieszczeniaOsoby.size() != 0) {
            System.out.printf("(Aby wyjsc wpisz 0)\n");
            input = new Scanner(System.in);
            int id;
            int index = 0;
            do {
                System.out.printf("Podaj id wybranego pomieszczenia: ");
                try {
                    id = input.nextInt();
                } catch (InputMismatchException e) {
                    id = -1;
                    input = new Scanner(System.in);
                }
                if (id == 0) {
                    System.exit(0);
                }
                boolean znalezionoPomieszczenie = false;
                for (Pomieszczenie p : pomieszczeniaOsoby) {
                    if (p.getID() == id) {
                        znalezionoPomieszczenie = true;
                        index = lp.indexOf(p);
                    }
                }
                if (!znalezionoPomieszczenie) {
                    id = -1;
                }
            } while (id == -1);
            pomieszczenie = lp.get(index);
        }
    }

    private static void wynajmijPomieszczenie(Magazyn magazyn) {
        List<Pomieszczenie> lp = magazyn.getPomieszczenia();
        Map<Pomieszczenie, Osoba> w = magazyn.getWynajecia();

        wypiszWolnePomieszczenia(lp, w);
        System.out.printf("(Aby wyjsc wpisz 0)\n");

        input = new Scanner(System.in);
        ArrayList<Pomieszczenie> wolnePomieszczenia = getWolnePomieszczenia(lp, w);
        int ilDostepnychPomieszczen = 0;
        int id;
        int index = 0;

        for(Pomieszczenie p : wolnePomieszczenia) {
            if(p.getCzyWylaczone() == false) {
                ilDostepnychPomieszczen++;
            }
        }

        if(ilDostepnychPomieszczen > 0) {
            do {
                System.out.printf("Podaj id wybranego pomieszczenia: ");
                try {
                    id = input.nextInt();
                } catch (InputMismatchException e) {
                    id = -1;
                    input = new Scanner(System.in);
                }
                if (id == 0) {
                    System.exit(0);
                }

                boolean znalezionoPomieszczenie = false;
                for (Pomieszczenie p : wolnePomieszczenia) {
                    if (p.getID() == id) {
                        znalezionoPomieszczenie = true;
                        index = lp.indexOf(p);
                    }
                }
                if (!znalezionoPomieszczenie) {
                    id = -1;
                }
            } while (id == -1);

            String data;
            do {
                System.out.printf("Podaj obecna date (DD.MM.YYYY): ");
                try {
                    data = input.nextLine();
                } catch (InputMismatchException e) {
                    data = "abc";
                    input = new Scanner(System.in);
                }
            } while(!Pattern.matches("^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$", data));

            int iloscDni;
            do {
                System.out.printf("Podaj ilosc dni wynajecia: ");
                try {
                    iloscDni = input.nextInt();
                } catch (InputMismatchException e) {
                    iloscDni = -1;
                    input = new Scanner(System.in);
                }
            } while (iloscDni <= 0);

            try {
                magazyn.wynajmij(magazyn.getPomieszczenia().get(index), osoba, data, iloscDni);
                System.out.printf("\nWynajeto nowe pomieszczenie.\n");
            } catch (NotAvaiableException nae) {
                System.out.printf("\n%s\n", nae);
            } catch (AlreadyRentedException are) {
                System.out.printf("\n%s\n", are);
            }
        } else {
            System.out.printf("\nBrak wolnych pomieszczen do wynajecia.\n");
        }
    }

    private static ArrayList<Pomieszczenie> getPomieszczeniaOsoby(List<Pomieszczenie> lp, Map<Pomieszczenie, Osoba> w) {
        ArrayList<Pomieszczenie> pomieszczeniaOsoby = new ArrayList<Pomieszczenie>();
        for(Pomieszczenie p : lp) {
            Osoba o = w.get(p);
            if(o != null) {
                if(o.equals(osoba)) {
                    pomieszczeniaOsoby.add(p);
                }
            }
        }

        return pomieszczeniaOsoby;
    }

    private static ArrayList<Pomieszczenie> getWolnePomieszczenia(List<Pomieszczenie> lp, Map<Pomieszczenie, Osoba> w) {
        ArrayList<Pomieszczenie> wolnePomieszczenia = new ArrayList<Pomieszczenie>();
        for(Pomieszczenie p : lp) {
            if(!w.containsKey(p)) {
                wolnePomieszczenia.add(p);
            }
        }

        return wolnePomieszczenia;
    }

}