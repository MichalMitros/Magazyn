public class AlreadyRentedException extends Exception{
    public AlreadyRentedException() {
        super("Pomieszczenie jest juz wynajete");
    }
}
