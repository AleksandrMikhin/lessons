package task1;

public class Rectangle {
    double x1, x2, x3, x4, y1, y2, y3, y4;

    private double length(double x1, double x2, double y1, double y2){
        return Math.pow( (Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2)), 0.5);
    };

    private boolean rightAngle(double x1, double x2, double x3, double y1, double y2, double y3) {
        return ((((y1 - y2) * (y2 - y3) + (x2 - x1) * (x3 - x2)) == 0)
                        || ((y2 - y3) * (y3 - y1) + (x3 - x2) * (x1 - x3) == 0)
                        || ((y1 - y2) * (y3 - y1) + (x2 - x1) * (x1 - x3) == 0));
    }

    public Rectangle(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4) {
        if (rightAngle(x1, x2, x3, y1, y2, y3)
                && rightAngle(x2, x3, x4, y2, y3, y4)
                && rightAngle(x3, x4, x1, y3, y4, y1)
                && rightAngle(x4, x1, x2, y4, y1, y2)) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
            this.y1 = y1;
            this.y2 = y2;
            this.y3 = y3;
            this.y4 = y4;
        }
    }

    public double square() {
        return length(x1, x2, y1, y2) * length(x1, x4, y1, y4);
    }

    public double perimetr() {
        return (length(x1, x2, y1, y2) + length(x1, x4, y1, y4)) * 2;
    }

}
