public class BattleUnit {
    //    private int health;
    //    private int attackScore;
    protected int health;
    protected int attackScore;


    public BattleUnit(int health, int attackScore) {
        this.health = health;
        this.attackScore = attackScore;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void attack(BattleUnit enemy){
        enemy.health -= this.attackScore;
        System.out.println("BattleUnit attack");
    }

}
