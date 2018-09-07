package c.test.toll;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button getLocationBtn;
    Image image;
    TextView locationText;
    TextView info;
    int flag = 0;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        locationText = (TextView)findViewById(R.id.locationText);
        info = (TextView)findViewById(R.id.info);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }


        getLocationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(flag==0) {
                    info.setText("Your location is being monitored. You will be notified when you be around 2km from the toll plaza.");
                    getLocation();
                }
                else{
                    Intent intent1 = new Intent(MainActivity.this,Ticket.class);
                    startActivity(intent1);
                }
            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        /*
        this is added by me
         */
        Location loc1 = new Location("");
        loc1.setLatitude(28.6498);
        loc1.setLongitude(77.3395);

        Location loc2 = new Location("");
        loc2.setLatitude(location.getLatitude());
        loc2.setLongitude(location.getLongitude());

        float distanceInMeters = loc1.distanceTo(loc2);

        //change this to 2000 later
        locationText.setText("The toll Plaza is " + distanceInMeters + " meters away.");

        if(distanceInMeters<=2000.000 && flag==0){
            flag++;
            showNotification();

            getLocationBtn.setText("Pay Toll Tax!!!");




            /*
            Intent intent1= new Intent(this,NotificationReceiver.class);
            startActivity(intent1);
            */
        }

    }
    public void showNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
/*
        Intent myIntentToButtonOneScreen = new Intent(this,NotificationReceiver.class);
        .addAction(R.drawable.ic_android_black_24dp, "BUTTON 1", myIntentToButtonOneScreen);

*/
        Intent myIntentToButtonOneScreen = new Intent(this,Ticket.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,1,myIntentToButtonOneScreen,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setTicker("Hearty365")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("TOLL PLAZA AHEAD")
                .setContentText("Pay your toll tax here to avoid long queue.")
                .setContentInfo("Info")
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);


        notificationManager.notify(/*notification id*/1, notificationBuilder.build());



    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }


}

