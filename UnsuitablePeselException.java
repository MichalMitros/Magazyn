public class UnsuitablePeselException extends Exception {
    public UnsuitablePeselException() {
        super("PESEL jest nieprawidłowy, lub ma niedpowiednia dlugosc");
    }
}
