 public class test {

 public static void main(String[] args) {
        Manager<Mybook> books = new Manager<>("resources/book.txt");
        Manager<Mycd> cds = new Manager<>("resources/cd.txt");
        Manager<Myloan> loans = new Manager<>("resources/cd.txt");

        System.out.println(DisplayHelper.displayText(cds));
    }
}