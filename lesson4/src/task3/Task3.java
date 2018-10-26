package task3;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите размер библиотеки: ");
        int count = in.nextInt();

        Book books[] = new Book[count];
        Library lib = new Library(count);

        System.out.println("Заполните книги и их количество: ");
        in.nextLine();
        for (int i = 0; i < count; i++){
            System.out.println("название книги №" + (i + 1) + ": ");
            Book book = new Book(in.nextLine());
            books[i] = book;
            System.out.print("количество: ");
            lib.put(book, in.nextInt());
            in.nextLine();
        }

        Book tempBook;
        outer: while (true){
            lib.listLibrary();
            System.out.println("----------------------------\n1. Взять книги\n2. Вернуть книги\n3. Наличие списком\n4. Выход\nвыбор: ");
            switch (in.nextInt()){
                case 1:{
                    System.out.print("Введите номер книги : ");
                    tempBook = books[in.nextInt()-1];
                    System.out.print("Количество: ");
                    if (lib.get(tempBook, in.nextInt())<0)
                        System.out.println("Недостаточное количество.");
                    break;
                }
                case 2:{
                    System.out.print("Введите номер книги : ");
                    tempBook = books[in.nextInt()-1];
                    System.out.print("Количество: ");
                    lib.put(tempBook, in.nextInt());
                    break;
                }
                case 3:{
                    lib.listLibrary();
                    break;
                }
                case 4: break outer;
            }

        }


    }
}
