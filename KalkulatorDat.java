import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class KalkulatorDat {

    public static int obliczRozniceDni(String poczatek, String koniec) {
//        int rok_p = Integer.parseInt(poczatek.substring(6));
//        int miesiac_p = Integer.parseInt(poczatek.substring(3,5));
//        int dzien_p = Integer.parseInt(poczatek.substring(0,2));
//        System.out.println(rok_p);
//        System.out.println(miesiac_p);
//        System.out.println(miesiac_p);
//
//        int rok_k = Integer.parseInt(koniec.substring(6));
//        int miesiac_k = Integer.parseInt(koniec.substring(3,5));
//        int dzien_k = Integer.parseInt(koniec.substring(0,2));
//
//        Date pocz = new Date(dzien_p, miesiac_p, rok_p);
//        Date kon = new Date(dzien_k, miesiac_k, rok_k);
//        System.out.println(pocz);
//        long roznica = (kon.getTime()-pocz.getTime()) / ( (long) (86400000));
//
//        return (int)roznica;
        long roznica = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
        try {
            Date pocz = format.parse(poczatek);
            Date kon = format.parse(koniec);

            long milisekundy = Math.abs(kon.getTime() - pocz.getTime());
            roznica = TimeUnit.DAYS.convert(milisekundy, TimeUnit.MILLISECONDS);
            return (int)roznica;
        }catch (ParseException pe) {
            System.out.printf("\n%s\n", pe);
        }
        return (int)roznica;
    }
}
