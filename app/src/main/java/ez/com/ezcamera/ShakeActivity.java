package ez.com.ezcamera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShakeActivity extends AppCompatActivity  implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private static String MODES[] = {"Accelere", "Ralenti", "Video" , "Photo", "Carre", "Panorama" };
    private int indexModes = 3;


    private Button button_capture;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        TextView textView = (TextView) findViewById(R.id.text_photo);
        textView.setTextColor(Color.parseColor("#1976D2"));
        textView.setTypeface(null, Typeface.BOLD);

        image = (ImageView)findViewById(R.id.imageView);
        button_capture = (Button)findViewById(R.id.button_capture);
        button_capture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                switch(indexModes){
                    case 3:
                        image.setImageResource(R.drawable.stylo);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.usb);
                        break;
                    case 5:
                        image.setImageResource(R.drawable.mouchoir);
                        break;
                    default:
                        image.setImageResource(R.drawable.fail);
                }
            }
        });
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
                previousMode();

            if(x < -7.0)
                nextMode();
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

    private void nextMode()
    {
        if(indexModes < MODES.length - 1)
            indexModes++;

        changeMode();
    }

    private void previousMode()
    {
        if(indexModes > 0)
            indexModes--;

        changeMode();
    }

    private void changeMode()
    {
        TextView text = (TextView) findViewById(R.id.text_accelere);
        text.setTextColor(Color.parseColor("#7C7C7C"));
        text.setTypeface(null);

        text = (TextView) findViewById(R.id.text_ralenti);
        text.setTextColor(Color.parseColor("#7C7C7C"));
        text.setTypeface(null);
        text = (TextView) findViewById(R.id.text_video);
        text.setTextColor(Color.parseColor("#7C7C7C"));
        text.setTypeface(null);
        text = (TextView) findViewById(R.id.text_photo);
        text.setTextColor(Color.parseColor("#7C7C7C"));
        text.setTypeface(null);
        text = (TextView) findViewById(R.id.text_carre);
        text.setTextColor(Color.parseColor("#7C7C7C"));
        text.setTypeface(null);
        text = (TextView) findViewById(R.id.text_panorama);
        text.setTextColor(Color.parseColor("#7C7C7C"));
        text.setTypeface(null);

        switch(MODES[indexModes])
        {
            case "Accelere" :
            {
                TextView textView = (TextView) findViewById(R.id.text_accelere);
                textView.setTextColor(Color.parseColor("#1976D2"));
                textView.setTypeface(null, Typeface.BOLD);
                break;
            }
            case "Ralenti" :
            {
                TextView textView = (TextView) findViewById(R.id.text_ralenti);
                textView.setTextColor(Color.parseColor("#1976D2"));
                textView.setTypeface(null, Typeface.BOLD);
                break;
            }
            case "Video" :
            {
                TextView textView = (TextView) findViewById(R.id.text_video);
                textView.setTextColor(Color.parseColor("#1976D2"));
                textView.setTypeface(null, Typeface.BOLD);
                break;
            }
            case "Photo" :
            {
                TextView textView = (TextView) findViewById(R.id.text_photo);
                textView.setTextColor(Color.parseColor("#1976D2"));
                textView.setTypeface(null, Typeface.BOLD);
                break;
            }
            case "Carre" :
            {
                TextView textView = (TextView) findViewById(R.id.text_carre);
                textView.setTextColor(Color.parseColor("#1976D2"));
                textView.setTypeface(null, Typeface.BOLD);
                break;
            }
            case "Panorama" :
            {
                TextView textView = (TextView) findViewById(R.id.text_panorama);
                textView.setTextColor(Color.parseColor("#1976D2"));
                textView.setTypeface(null, Typeface.BOLD);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_global, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_to_swipe:
                Intent swipeActivity = new Intent(this, SwipeActivity.class);
                startActivity(swipeActivity);

                return true;

            case R.id.action_to_circle:
                Intent circleActivity = new Intent(this, CircleActivity.class);
                startActivity(circleActivity);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
