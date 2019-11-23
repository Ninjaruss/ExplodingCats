package Cards;

public class CardFactory {
    public static CardObject makeCard(String cardName){
        switch (cardName){
            case "bomb":
                return new Bomb();
            case "defuse":
                return new Defuse();
            case "nope":
                return new Nope();
            case "attack":
                return new Attack();
            case "skip":
                return new Skip();
            case "favor":
                return new Favor();
            case "shuffle":
                return new Shuffle();
            case "seethefuture":
                return new SeeTheFuture();
            default:
                return null;
        }
    }
}
