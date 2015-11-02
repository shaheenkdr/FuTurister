package obx.com.futurister;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;


public class FeedActivity extends ActionBarActivity {
    private String locReceive;
    private String value;
    private EditText E1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                locReceive= null;
            } else {
                locReceive= extras.getString("LOCATION");
            }
        } else {
            locReceive= (String) savedInstanceState.getSerializable("LOCATION");
        }

        E1 = (EditText)findViewById(R.id.FeedText);



       // Toast.makeText(getApplicationContext(), ""+locReceive,
        //        Toast.LENGTH_SHORT).show();



    }

    public void feedPush(View view)
    {
        value=E1.getText().toString().trim();
        if(value.isEmpty() || value.length() == 0 || value.equals("") || value == null)
        {
            Toast.makeText(getApplicationContext(), "Enter your feed before posting",
                    Toast.LENGTH_SHORT).show();


        }
        else
        {
            ParseObject pusher = new ParseObject("BigBoard");
            pusher.put("Location", locReceive);
            pusher.put("Feed", value);
            pusher.saveInBackground();
            finishAffinity();

        }

    }

    public void backOptFeed(View view)
    {
        finish();
        Intent abc = new Intent(FeedActivity.this,OptionChooser.class);
        startActivity(abc);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
