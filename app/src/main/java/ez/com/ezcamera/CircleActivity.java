package ez.com.ezcamera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
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
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class CircleActivity extends AppCompatActivity
{
    private static String MODES[] = {"Accelere", "Ralenti", "Video" , "Photo", "Carre", "Panorama" };
    private static int MODES_IMG[] = {R.drawable.ic_fast, R.drawable.ic_slow, R.drawable.ic_movie,
            R.drawable.ic_button_camera,R.drawable.ic_square,R.drawable.ic_panoramic};
    private static int COLOR = Color.parseColor("#ffffff");

    private int indexModes = 3;

    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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
                        indexModes=i;
                        changeMode();
                    }
                });

        TextView textView = (TextView) findViewById(R.id.text_photo);
        textView.setTextColor(Color.parseColor("#1976D2"));
        textView.setTypeface(null, Typeface.BOLD);
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
        circleMenu.setMainMenu(COLOR,MODES_IMG[indexModes],MODES_IMG[indexModes]);
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

            case R.id.action_to_shake:
                Intent shakeActivity = new Intent(this, ShakeActivity.class);
                startActivity(shakeActivity);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
