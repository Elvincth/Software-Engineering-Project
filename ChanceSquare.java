
public class ChanceSquare extends Square{
    public String name = "";
    
    ChanceSquare(String name, int position){
        super(name, position);
        this.name = name;
    }

    public void effectTo(Player player){} //determine add amount or lose money
    
}
