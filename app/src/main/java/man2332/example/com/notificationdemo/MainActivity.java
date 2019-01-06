package man2332.example.com.notificationdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static man2332.example.com.notificationdemo.App.CHANNEL_1_ID;
import static man2332.example.com.notificationdemo.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;
    private EditText editTitle;
    private EditText editmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.edit_text_title);
        editmessage = findViewById(R.id.edit_text_message);
        notificationManagerCompat = NotificationManagerCompat.from(this);

        /**
         * To use this class, call the static function from(Context) to get
         * a NotificationManagerCompat object, and then call one of its
         * methods to post or cancel notifications.
         */

    }

    //sends a notification to the channel
    public void sendOnChannel1(View view) {
        String title = editTitle.getText().toString();
        String message = editmessage.getText().toString();
        //when user clicks on the selfnotification it will open up the application(MainActivity)
        //  this teaches you how to response to user clicking the notification itself
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);
        //-when user clicks the notification button it will display a toast messageTitle
        //  this teaches you how to add a clickable button to a notification
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("message", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0,
                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //This bitmap is used to set a large icon in notification
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_one);

        //Flag indicating that if the described PendingIntent already exists, then keep
        // it but replace its extra data with what is in this new Intent.
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(R.drawable.ic_one)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "ToastMsg", actionIntent)
                .setLargeIcon(bitmap)//this line & below sets big text style dropdown box
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.long_dummy_text))
                        .setBigContentTitle("Big Content TITLE HERE for bigtext style")
                        .setSummaryText("Summary Text here"))
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public void sendOnChannel2(View view) {
        String title = editTitle.getText().toString();
        String message = editmessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(R.drawable.ic_two)
                .setStyle(new NotificationCompat.InboxStyle()
                        .setBigContentTitle("Big Content title for inbox")
                        .setSummaryText("Summary for inbox")
                        .addLine("Line1")
                        .addLine("Line2")
                        .addLine("Line3")
                        .addLine("Line4")
                        .addLine("Line5")
                        .addLine("Line6")
                        .addLine("Line7")
                )
                .build();

        notificationManagerCompat.notify(2, notification);
    }
}
