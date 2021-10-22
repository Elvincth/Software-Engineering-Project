public class TaxSquare extends Square implements EffectSquareAPI {
    
    TaxSquare(String name, int position){
        super(name, position);
    }

    public void effectTo(Player player){} //determine add amount or lose money
    
}
