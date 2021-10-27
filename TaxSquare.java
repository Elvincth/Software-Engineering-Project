public class TaxSquare extends Square implements EffectSquareAPI {

    TaxSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Dice dice) {
        player.deductBalance(player.getBalance() / 10);
    } // determine add amount or lose money
}
