package ez.com.ezcamera;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class StartActivity extends AppCompatActivity implements SensorEventListener
{
    private String modes[] = {"Accelere", "Ralenti", "Video" , "Photo", "Carre", "Panorama", };
    private int indexModes = 0;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.ic_button_menu, R.drawable.ic_button_camera)
                .addSubMenu(Color.parseColor("#FF0000"),R.drawable.ic_button_camera)
                .addSubMenu(Color.parseColor("#00FF00"),R.drawable.ic_button_camera)
                .addSubMenu(Color.parseColor("#0000FF"),R.drawable.ic_button_camera)
                .addSubMenu(Color.parseColor("#FF00FF"),R.drawable.ic_button_camera)
                .addSubMenu(Color.parseColor("#FFFF00"),R.drawable.ic_button_camera)
                .addSubMenu(Color.parseColor("#00FFFF"),R.drawable.ic_button_camera)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Snackbar.make(findViewById(R.id.start_constraint),
                                        "Mode " + modes[i] + ".",
                                        Snackbar.LENGTH_SHORT).show();
                    }
                });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        if(accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


    }

    /**
     * A sensor has detected a change.
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        // No real need of the if here. But if we listen to several sensors, then we would need it.
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = sensorEvent.values[0];

            if(x > 10.0)
            {
                if(indexModes > 0)
                    indexModes--;
                Snackbar.make(findViewById(R.id.start_constraint),
                                "Mode " + modes[indexModes],
                                Snackbar.LENGTH_SHORT).show();
            }


            if(x < -7.0)
            {
                if(indexModes < modes.length - 1)
                    indexModes++;
                Snackbar.make(findViewById(R.id.start_constraint),
                                "Mode " + modes[indexModes],
                                Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // No specific code here.
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }
}
