import java.util.InputMismatchException;
import java.util.Scanner;

public class PrzedmiotFactory {

    public static Przedmiot stworzPrzedmiot() {

        Przedmiot produkt;

        int typ;
        double powierzchnia;
        Scanner input = new Scanner(System.in);

        System.out.printf("\nDodawanie przedmiotu\n");
        System.out.printf("---------------------\n");
        System.out.printf("(Aby wyjsc z programu wpisz 0)\n");
        do {
            System.out.printf("Podaj powierzchnie przedmiotu w m^3: ");
            try {
                powierzchnia = input.nextDouble();
            } catch(InputMismatchException e) {
                powierzchnia = -2;
                input = new Scanner(System.in);
            }
        } while(powierzchnia <= 0 && powierzchnia != -1);
        if(powierzchnia == -1) {
            System.exit(0);
        }

        System.out.printf("\nDodawanie przedmiotu\n");
        System.out.printf("---------------------\n");
        System.out.printf("Wybierz typ przedmiotu:\n");
        System.out.printf("1. ZWYKLY\n");
        System.out.printf("2. POJAZD\n");
        System.out.printf("0. wyjscie z programu\n");
        System.out.printf("---------------------\n");
        do {
            System.out.printf("Wybrany typ: ");
            try {
                typ = input.nextInt();
            } catch(InputMismatchException e) {
                typ = -1;
                input = new Scanner(System.in);
            }
            if(typ < 0 || typ > 2) {
                System.out.printf("(podaj numer typu z przedzialu 1-2)\n");
            }
        } while(typ < 0 || typ > 2);

        switch(typ) {
            case 2:
                produkt = stworzPojazd(powierzchnia);
                break;
            case 0:
                System.exit(0);
            default:
                produkt = stworzZwykly(powierzchnia);
                break;
        }

        return produkt;
    }

    private static Przedmiot stworzZwykly(double powierzchnia) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Podaj nazwe przedmiotu: ");
        String nazwa = input.next();
        return new ZwyklyPrzedmiot(nazwa, powierzchnia);
    }

    private static Przedmiot stworzPojazd(double powierzchnia) {
        Pojazd produkt;
        Scanner input = new Scanner(System.in);
        int typ;
        System.out.printf("\nDodawanie pojazdu\n");
        System.out.printf("---------------------\n");
        System.out.printf("Wybierz typ pojazdu:\n");
        System.out.printf("1. SAMOCHOD\n");
        System.out.printf("2. MOTOCYKL\n");
        System.out.printf("3. ROWER\n");
        System.out.printf("---------------------\n");
        System.out.printf("(Aby wyjsc z programu wpisz 0)\n");
        do {
            System.out.printf("Wybrany typ: ");
            try {
                typ = input.nextInt();
            } catch(InputMismatchException e) {
                typ = -1;
                input = new Scanner(System.in);
            }
            if(typ < 0 || typ > 3) {
                System.out.printf("(podaj numer typu z przedzialu 1-3)\n");
            }
        } while(typ < 0 || typ > 3);

        switch(typ) {
            case 1:
                produkt = stworzSamochod(powierzchnia);
                break;
            case 2:
                produkt = stworzMotocykl(powierzchnia);
                break;
            case 3:
                produkt = stworzRower(powierzchnia);
                break;
            case 0:
                System.exit(0);
            default:
                produkt = stworzRower(powierzchnia);
                break;
        }

        return produkt;
    }

    private static Samochod stworzSamochod(double powierzchnia) {
        Scanner input = new Scanner(System.in);
        TypSilnika typSilnika = TypSilnika.BENZYNA;
        double pojemnosc;
        System.out.printf("\nDodawanie samochodu\n");
        System.out.printf("---------------------\n");
        System.out.printf("(Aby wyjsc z programu wpisz -1)\n");

        int typsil;
        System.out.printf("\nWybierz typ silnika: \n");
        System.out.printf("1. benzyna\n");
        System.out.printf("2. diesel\n");
        System.out.printf("3. gaz\n");
        System.out.printf("4. hybryda\n");
        System.out.printf("-1. Wyjscie z programu\n");
        do {
            System.out.printf("Wybrany typ hamulcow: ");
            try {
                typsil = input.nextInt();
            } catch(InputMismatchException e) {
                typsil = -2;
                input = new Scanner(System.in);
            }
        } while(typsil == -2);
        switch(typsil) {
            case 1:
                typSilnika = TypSilnika.BENZYNA;
                break;
            case 2:
                typSilnika = TypSilnika.DIESEL;
                break;
            case 3:
                typSilnika = TypSilnika.GAZ;
                break;
            case 4:
                typSilnika = TypSilnika.HYBRYDA;
                break;
            case -1:
                System.exit(0);
        }

        System.out.printf("\n(Aby wyjsc z programu wpisz -1)\n");
        do {
            System.out.printf("Podaj pojemnosc silnika: ");
            try {
                pojemnosc = input.nextDouble();
            } catch(InputMismatchException e) {
                pojemnosc = -2;
                input = new Scanner(System.in);
            }
        } while(pojemnosc < -1);
        if(pojemnosc == -1) {
            System.exit(0);
        }

        return new Samochod(powierzchnia, typSilnika, pojemnosc);
    }

    private static Motocykl stworzMotocykl(double powierzchnia) {
        Scanner input = new Scanner(System.in);
        TypMotocyklu typMotocyklu = TypMotocyklu.TYP1;
        boolean posiadaHomologacje = false;
        double pojemnosc;
        System.out.printf("\nDodawanie motocyklu\n");
        System.out.printf("---------------------\n");

        int typmot;
        System.out.printf("Wybierz typ motocyklu: (nie wiem jakie sa typy motocykli, nie znam sie...)\n");
        System.out.printf("1. TYP1\n");
        System.out.printf("2. TYP2\n");
        System.out.printf("-1. Wyjscie z programu\n");
        do {
            System.out.printf("Wybrany typ hamulcow: ");
            try {
                typmot = input.nextInt();
            } catch(InputMismatchException e) {
                typmot = -2;
                input = new Scanner(System.in);
            }
        } while(typmot != -1  && typmot != 1 && typmot != 2);
        switch(typmot) {
            case 1:
                typMotocyklu = TypMotocyklu.TYP1;
                break;
            case 2:
                typMotocyklu = TypMotocyklu.TYP2;
                break;
            case -1:
                System.exit(0);
        }

        System.out.printf("\nCzy posiada homologacje?\n");
        System.out.printf("1. TAK\n");
        System.out.printf("2. NIE\n");
        System.out.printf("-1. Wyjscie z programu\n");
        do {
            System.out.printf("Wybrana opcja: ");
            try {
                typmot = input.nextInt();
            } catch(InputMismatchException e) {
                typmot = -2;
                input = new Scanner(System.in);
            }
        } while(typmot != -1  && typmot != 1 && typmot != 2);

        switch(typmot) {
            case 1:
                posiadaHomologacje = true;
                break;
            case 2:
                posiadaHomologacje = false;
                break;
            case -1:
                System.exit(0);
        }
        return new Motocykl(powierzchnia, typMotocyklu, posiadaHomologacje);
    }

    private static Rower stworzRower(double powierzchnia) {
        Scanner input = new Scanner(System.in);
        int liczbaPrzerzutek;
        TypHamulcow typHamulcow = TypHamulcow.ZACISKOWE;
        int liczbaAmortyzaotrow;
        System.out.printf("\nDodawanie roweru\n");
        System.out.printf("---------------------\n");
        System.out.printf("(Aby wyjsc z programu wpisz -1)\n");

        do {
            System.out.printf("Podaj liczbe przerzutek: ");
            try {
                liczbaPrzerzutek = input.nextInt();
            } catch(InputMismatchException e) {
                liczbaPrzerzutek = -2;
                input = new Scanner(System.in);
            }
        } while(!(liczbaPrzerzutek == -1 || liczbaPrzerzutek > 0));
        if(liczbaPrzerzutek == -1) {
            System.exit(0);
        }

        int typham;
        System.out.printf("\nWybierz typ hamulcow: \n");
        System.out.printf("1. tarczowe\n");
        System.out.printf("2. zaciskowe\n");
        System.out.printf("-1. Wyjscie z programu\n");
        do {
            System.out.printf("Wybrany typ hamulcow: ");
            try {
                typham = input.nextInt();
            } catch(InputMismatchException e) {
                typham = -2;
                input = new Scanner(System.in);
            }
        } while(typham != 1 && typham != 2 && typham != -1);
        switch(typham) {
            case 1:
                typHamulcow = TypHamulcow.TARCZOWE;
                break;
            case 2:
                typHamulcow = TypHamulcow.ZACISKOWE;
                break;
            case -1:
                System.exit(0);
        }

        System.out.printf("\n(Aby wyjsc z programu wpisz -1)\n");
        do {
            System.out.printf("Podaj liczbe amortyzatorow: ");
            try {
                liczbaAmortyzaotrow = input.nextInt();
            } catch(InputMismatchException e) {
                liczbaAmortyzaotrow = -2;
                input = new Scanner(System.in);
            }
        } while(liczbaAmortyzaotrow < -1);
        if(liczbaAmortyzaotrow == -1) {
            System.exit(0);
        }

        return new Rower(powierzchnia, liczbaPrzerzutek, typHamulcow, liczbaAmortyzaotrow != 0, liczbaAmortyzaotrow);
    }
}
