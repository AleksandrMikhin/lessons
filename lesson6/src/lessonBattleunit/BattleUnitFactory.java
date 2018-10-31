package lessonBattleunit;

public class BattleUnitFactory {
    public BattleUnit create(String userChoice){
        if (userChoice.equals("knight")) {
            return new Knight(100, 20);
        }
        if (userChoice.equals("spman")) {
            return new Spearman(70, 40);
        }
        return new Doctor(50, 10);
    }
}