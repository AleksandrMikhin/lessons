package lesson4.task1;

public class Triangle {
    double x1, x2, x3, y1, y2, y3;

    private double length(double x1, double x2, double y1, double y2){
        return Math.pow((Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)), 0.5);
    };

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double square() {
        return Math.abs(0.5 * ((x1-x3) * (y2 - y3) - (x2 - x3) * (y1 - y3)));
    }

    public double perimetr() {
        return length(x1, x2, y1, y2) + length(x1, x3, y1, y3) + length(x2, x3, y2, y3);
    }

}
