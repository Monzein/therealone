package ez.com.ezcamera;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class StartActivity extends AppCompatActivity implements SensorEventListener
{
    private static String MODES[] = {"Accelere", "Ralenti", "Video" , "Photo", "Carre", "Panorama" };
    private static int MODES_IMG[] = {R.drawable.ic_fast, R.drawable.ic_slow, R.drawable.ic_movie,
            R.drawable.ic_button_camera,R.drawable.ic_square,R.drawable.ic_panoramic};
    private static int COLOR = Color.parseColor("#ffffff");

    private int indexModes = 3;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float x_down; // Used to manage left/right swipe
    private static int MIN_DISTANCE_SWIPE = 300;

    private TextView textview;
    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        textview = (TextView) findViewById(R.id.textview);
        textview.setText(MODES[indexModes]);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(COLOR,MODES_IMG[indexModes], MODES_IMG[indexModes])
                .addSubMenu(COLOR,MODES_IMG[0])
                .addSubMenu(COLOR,MODES_IMG[1])
                .addSubMenu(COLOR,MODES_IMG[2])
                .addSubMenu(COLOR,MODES_IMG[3])
                .addSubMenu(COLOR,MODES_IMG[4])
                .addSubMenu(COLOR,MODES_IMG[5])
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        /*Snackbar.make(findViewById(R.id.start_constraint),
                                        "Mode " + MODES[i] + ".",
                                        Snackbar.LENGTH_SHORT).show();*/
                        indexModes=i;
                        changeMode();
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

    /**
     * Managing the left/right swipe
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        ImageView rectangle = (ImageView) findViewById(R.id.rectangle_swipe);
        Rect dimensions = new Rect();

        rectangle.getGlobalVisibleRect(dimensions);

        // If the swipe didn't happen in the rectangle, exit the method.
        if(!dimensions.contains((int) event.getX(), (int) event.getY()))
            return super.onTouchEvent(event);

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
            {
                x_down = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP  :
            {
                if(event.getX() - x_down >= MIN_DISTANCE_SWIPE) // Switch to previous mode
                {
                    previousMode();
                }
                else if(x_down - event.getX() >= MIN_DISTANCE_SWIPE) // Switch to next mode
                {
                    nextMode();
                }
                break;
            }
        }

        return super.onTouchEvent(event);
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

    private void changeMode(){

        Snackbar.make(findViewById(R.id.start_constraint),
                "Mode " + MODES[indexModes],
                Snackbar.LENGTH_SHORT).show();
        textview.setText(MODES[indexModes]);
        circleMenu.setMainMenu(COLOR,MODES_IMG[indexModes],MODES_IMG[indexModes]);
    }
}
