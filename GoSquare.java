public class GoSquare extends Square implements EffectSquareAPI {

    GoSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player) {
        if(player.getUserCurentRound() > 0){
            player.addBalance(1500);
            player.addUserCurrentRound(1);
        }
        else{
            player.addUserCurrentRound(1);
        }
    } // when user pass through the go square 

}