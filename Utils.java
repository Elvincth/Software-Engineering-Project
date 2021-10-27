public class Utils {
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
