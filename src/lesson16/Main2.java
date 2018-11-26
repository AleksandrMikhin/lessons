package lesson16;

import java.io.*;

public class Main2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("stat2.bin");

        LaunchStatExternalizable stat;

        if (!file.exists()) {
            stat = new LaunchStatExternalizable();
        } else {
            try (ObjectInputStream objIn =
                         new ObjectInputStream(             //ObjectInputStream - преобразует данные в объект
                                 new FileInputStream(file)  //FileInputStream - читает данные из файла
                         ))
            {
                stat = (LaunchStatExternalizable) objIn.readObject();
            }
          }

          if (stat.isFirstLaunch()) {
              System.out.println("first launch");
          } else {
              System.out.println(stat);
          }

          stat.updete();

          try (ObjectOutputStream objOut =
                  new ObjectOutputStream(
                          new FileOutputStream(file)
                  ))
          {
              objOut.writeObject(stat);
          }
    }
}