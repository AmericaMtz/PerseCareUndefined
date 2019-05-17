package mx.perse_care.undefinedsoft.perse_care.Activities;

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

import mx.perse_care.undefinedsoft.perse_care.R;

public class NotificationsCreate extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_create);
        notificationManager = NotificationManagerCompat.from(this);

    }
    public void sendOnChannel1(View view){

        Intent intent= new Intent(this, HorariosDeComidaNotificaciones.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Intent broadcastIntent = new Intent(this, NotificationReceive.class);
        broadcastIntent.putExtra("toastMessage", "listo");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.pez30);

        Notification notification= new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_cocinero)
                .setContentTitle("HORA DE COMER")
                .setContentText("Tus peces tienen hambre, es momento de alimentarlos")
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Tus peces tienen hambre, es momento de alimentarlos")
                .setBigContentTitle("ES LA HORA DE COMER DE TUS PECES")
                .setSummaryText("Alimenta a tus peces"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.rgb(255,20,147))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.drawable.ic_launcher,"Listo", actionIntent )
                .build();
        notificationManager.notify(1, notification);

    }
    public void sendOnChannel2(View view){
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.pecera);
        Intent intent= new Intent(this, HorariosDeLimpiezaNotificaciones.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Intent broadcastIntent = new Intent(this, NotificationReceive.class);
        broadcastIntent.putExtra("toastMessage", "listo");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification= new NotificationCompat.Builder(this, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.spray)
                .setContentTitle("HORA DE LIMPIAR")
                .setContentText("La pecera de tus peces esta sucia, es momento de limpiarla")
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("La pecera de tus peces esta sucia, es momento de limpiarla, recuerda que debes hacerlo almenos dos veces por mes.")
                .setBigContentTitle("HORA DE LIMPIAR")
                .setSummaryText("Limpieza de pecera"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.rgb(0,191,255))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.drawable.ic_launcher,"Listo", actionIntent )
                .build();
        notificationManager.notify(2, notification);
    }
}
