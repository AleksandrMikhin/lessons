package tasksPatterns.task2;

//    Реализовать сигнализации, реагирующие на повышение температуры (паттерн observer).
//    Класс Sensor инкрементально повышает температуру, и на каждое изменение температуры
//    оповещает слушателей (сигнализации). Их должно быть три, соответствующие уровням тревоги:
//    Green (100), Yellow (300), Red (600).
//
//    Описываете интерфейс Alarm, имеющий метод void tempChanged(int temp), и подписываете
//    три реализации к датчику на оповещения.
//
//    Сигнализации сработают по преодолению определенного порога температуры:
//    100 градусов - Green
//    300 градусов - Green, Yellow
//    600 градусов - Green, Yellow, Red
//
//    Срабатывание - вывод в консоль сообщения. Если сигнализация сработала, то сообщение не повторяется на
//    дальнейшее повышение температуры, но если опустится ниже порога, а потом опять преодолеет, то выведется снова.

import java.util.ArrayList;
import java.util.List;

public class Sensor {

    private List<Alarm> alarmListeners = new ArrayList<>();

    public void addAlarmListener(Alarm alarm) {
        alarmListeners.add(alarm);
    }

    public void changeTemp(int temp) {
        System.out.println("\n--------------------\nCurrent temp: " + temp);
        notifyListeners(temp);
    }

    private void notifyListeners(int temp) {
        for (Alarm alarm: alarmListeners) {
            alarm.tempChanged(temp);
        }
    }



    public static void main(String[] args) {

        Sensor sensor = new Sensor();

        sensor.addAlarmListener(new Alarm() {
            private int lastTemp = 0;

            @Override
            public void tempChanged(int temp) {
                if (temp >= 100 && lastTemp < 100) System.out.print("Green ");
                lastTemp = temp;
            }
        });

        sensor.addAlarmListener(new Alarm() {
            private int lastTemp = 0;

            @Override
            public void tempChanged(int temp) {
                if (temp >= 300 && lastTemp < 300) System.out.print("Yellow ");
                lastTemp = temp;
            }
        });

        sensor.addAlarmListener(new Alarm() {
            private int lastTemp = 0;

            @Override
            public void tempChanged(int temp) {
                if (temp >= 600 && lastTemp < 600) System.out.print("Red ");
                lastTemp = temp;
            }
        });

        sensor.changeTemp(150);
        sensor.changeTemp(250);
        sensor.changeTemp(350);
        sensor.changeTemp(600);
        sensor.changeTemp(250);
        sensor.changeTemp(650);

    }

}
