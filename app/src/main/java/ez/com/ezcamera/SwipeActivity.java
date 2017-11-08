package ez.com.ezcamera;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class SwipeActivity extends AppCompatActivity
{
    private static int MIN_DISTANCE_SWIPE = 300;
    private float x_down; // Used to manage left/right swipe

    private static String MODES[] = {"Accelere", "Ralenti", "Video" , "Photo", "Carre", "Panorama" };
    private int indexModes = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        TextView textView = (TextView) findViewById(R.id.text_photo);
        textView.setTextColor(Color.parseColor("#1976D2"));
        textView.setTypeface(null, Typeface.BOLD);
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
            case R.id.action_to_circle:
                Intent circleActivity = new Intent(this, CircleActivity.class);
                startActivity(circleActivity);

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
