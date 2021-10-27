public class TaxSquare extends Square implements EffectSquareAPI {
    private Utils utils = new Utils();
    private final String TAG = "[TAX]";

    TaxSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        if (player.getBalance() > 0){
            int tax = utils.roundDown(player.getBalance() * 0.1);

            System.out.printf("%s You have to pay %dHKD tax!\n", TAG, tax);
            player.deductBalance(tax);
        }
        else{
            System.out.println("Since your balance is 0, you don't need to pay tax");
        }
    } // determine add amount or lose money
}
