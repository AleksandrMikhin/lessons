package lesson1;

public class Task1 {

    public static void main(String[] args) {
        int height = 15, length = 25, width = 10, square;
        square = 2 * (height * length + height * width + length * width);
        System.out.println( "Площадь поверхности равна: " + square);
        System.out.println( (height == width)?"Высота и ширина равны" :
                                (height > width)?"Высота больше ширины" : "Ширина больше высоты");

    }

}
