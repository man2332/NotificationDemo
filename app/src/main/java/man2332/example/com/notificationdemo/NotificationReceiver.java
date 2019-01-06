package man2332.example.com.notificationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    //called when BroadcastReceiver is triggered and can execute some code without having our app open
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
