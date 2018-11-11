package lesson5;

public class Knight extends BattleUnit{

    public Knight(int health, int attackScore) { // 100 20
        super(health, attackScore); // 100 20
    }

    private void addHealth(){
        if (this.health > 1) {
            this.health +=5;
        }
    }

    @Override
    public void attack(BattleUnit enemy){
        enemy.health -= this.attackScore;
        addHealth();
        System.out.println("Knight attack");
    }

}