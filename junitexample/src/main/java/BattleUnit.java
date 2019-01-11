import java.util.Objects;

public class BattleUnit {
    int health;
    int attack;

    public BattleUnit(int health, int attack) throws Exception {
        if (health < 1 || attack < 1) throw new Exception("Здоровье должно быть положительным");
        this.health = health;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BattleUnit)) return false;
        BattleUnit that = (BattleUnit) o;
        return health == that.health &&
                attack == that.attack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, attack);
    }

    public void minusHealth(int health){
        this.health -= health;
    }

    public void attack(BattleUnit unit){
        unit.minusHealth(this.attack);
    }
}
