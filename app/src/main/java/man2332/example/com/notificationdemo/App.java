package man2332.example.com.notificationdemo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.EditText;

/*
* In manifest Application tag, we put android:name=".App" which tells the system
* that when the application starts, it will call this class's onCreate method BEFORE
* any activity starts
* */
//App.java is for setting up the notification channels
public class App extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();
        //when the application starts, we create notification channels, later we send notification
        //objects to this channel to be displayed to user
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        //1.Check if SDK is 26(Oreo) or higher - Notification channels work only on SDK 26 or higher
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //2.Create the channel object
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "ChannelOne",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel1 Description!");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "ChannelTwo",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("This is Channel2 Description!");

            //3.get reference to Notification Manager & use that to create the notification channel
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
        }
    }
}
