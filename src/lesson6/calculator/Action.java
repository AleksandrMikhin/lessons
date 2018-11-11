package lesson6.calculator;

abstract public class Action
{
    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public int getVar2() {
        return var2;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    private int var1;
    private int var2;

    public Action(int var1, int var2)
    {
        this.var1 = var1;
        this.var2 = var2;
    }

    abstract public void work();
}