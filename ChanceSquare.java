public class ChanceSquare extends Square{
    public String name = "";
    
    ChanceSquare(String name){
        super(name);
        this.name = name;
    }

    public void effectTo(Player player){} //determine add amount or lose money
    
}
