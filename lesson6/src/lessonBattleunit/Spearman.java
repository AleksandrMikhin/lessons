package lessonBattleunit;

public class Spearman
        extends BattleUnit {

    public Spearman(int health, int attackScore) {
        super(health, attackScore);
    }

    @Override
    public void attack(BattleUnit enemy) {
        enemy.health -= this.attackScore;
    }

    @Override
    public void say(String text) {
        System.out.println("Пикейщик сказал " + text);
    }

    @Override
    public void sing(String song) {
        System.out.println("Поет песню " + song);
    }
}