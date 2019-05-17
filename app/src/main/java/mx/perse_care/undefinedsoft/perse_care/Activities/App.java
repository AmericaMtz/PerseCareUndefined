package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID= "chanhel1";
    public static final String CHANNEL_2_ID= "chanhel2";
    @Override
    public void onCreate() {
        super.onCreate();
        createnotificationsChannel();

    }

    private void createnotificationsChannel() {
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel1= new NotificationChannel(
                    CHANNEL_1_ID,
                    "Hora de la comida",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Alimentación");

            NotificationChannel channel2= new NotificationChannel(
                    CHANNEL_2_ID,
                    "Momento de limpiar la pecera",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("Limpieza");

            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }
}
