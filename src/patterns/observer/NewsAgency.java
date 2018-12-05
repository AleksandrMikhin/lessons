package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency{
    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void deleteListener(Listener listener) {
        listeners.remove(listener);
    }

    public void newMessage(String mess) {
        System.out.println("Message " + mess);
        notifyListeners(mess);
    }

    private void notifyListeners(String mess) {
        for (Listener listener: listeners) {
            listener.publish(mess);
        }
    }

    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        newsAgency.addListener(new Listener() {
            @Override
            public void publish(String str) {
                System.out.println("listener 1" + str);
            }
        });

        newsAgency.addListener(new Listener() {
            @Override
            public void publish(String str) {
                System.out.println("listener 2" + str);
            }
        });

        newsAgency.newMessage("some changes");
    }

}
