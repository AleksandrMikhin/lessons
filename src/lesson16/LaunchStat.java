package lesson16;

import java.io.Serializable; //для записи объектов в файл
import java.util.Date;

public class LaunchStat implements Serializable {
    private int launchCount;
    private Date lastLaunch;

    private String lastUser;

    public void updete(){
        launchCount++;
        lastLaunch = new Date();
        lastUser = System.getProperty("user.name");
    }

    public boolean isFirstLaunch(){
        return launchCount == 0 && lastLaunch == null;
    }

    @Override
    public String toString() {
        return "LaunchStat{" +
                "launchCount=" + launchCount +
                ", lastLaunch=" + lastLaunch +
                ", lastUser='" + lastUser + '\'' +
                '}';
    }
}