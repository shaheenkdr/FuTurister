package obx.com.futurister;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class VideoActivity extends ActionBarActivity {

    private int passX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ImageView vid1 = (ImageView)findViewById(R.id.kashmir);
        vid1.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid2 = (ImageView)findViewById(R.id.london);
        vid2.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid3 = (ImageView)findViewById(R.id.paris);
        vid3.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid4 = (ImageView)findViewById(R.id.himalaya);
        vid4.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid5 = (ImageView)findViewById(R.id.russia);
        vid5.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid6 = (ImageView)findViewById(R.id.rome);
        vid6.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid7 = (ImageView)findViewById(R.id.thailand);
        vid7.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid8 = (ImageView)findViewById(R.id.rajasthan);
        vid8.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid9 = (ImageView)findViewById(R.id.Barcelona);
        vid9.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView vid10 = (ImageView)findViewById(R.id.budapest);
        vid10.setScaleType(ImageView.ScaleType.FIT_XY);


        TextView kashmir = (TextView)findViewById(R.id.textKashmir);
        TextView london = (TextView)findViewById(R.id.textLondon);
        TextView paris = (TextView)findViewById(R.id.textParis);
        TextView himalaya = (TextView)findViewById(R.id.textHimalaya);
        TextView russia = (TextView)findViewById(R.id.textRussia);
        TextView rome = (TextView)findViewById(R.id.textRome);
        TextView thailand = (TextView)findViewById(R.id.textThailand);
        TextView rajasthan = (TextView)findViewById(R.id.textRajasthan);
        TextView barcelona = (TextView)findViewById(R.id.textBarcelona);
        TextView budapest = (TextView)findViewById(R.id.textBudapest);


        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/trench100free.otf");

        kashmir.setTypeface(custom_font);
        london.setTypeface(custom_font);
        paris.setTypeface(custom_font);
        himalaya.setTypeface(custom_font);
        russia.setTypeface(custom_font);
        rome.setTypeface(custom_font);
        thailand.setTypeface(custom_font);
        rajasthan.setTypeface(custom_font);
        barcelona.setTypeface(custom_font);
        budapest.setTypeface(custom_font);

    }

    public void goBackVideo(View view)
    {
        finish();
        Intent send = new Intent(VideoActivity.this,OptionChooser.class);
        startActivity(send);
    }

    public void helpDialVideo(View view)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(VideoActivity.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Places");
        builder.setMessage("Choose a favorite place of yours to know more about it and the best tourist attractions in there!");
        builder.setPositiveButton("Got it", null);//second parameter used for onclicklistener

        builder.show();
    }

    public void vid1x(View view)
    {
        passX=1;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid2x(View view)
    {
        passX=2;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid3x(View view)
    {
        passX=3;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid4x(View view)
    {
        passX=4;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid5x(View view)
    {
        passX=5;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid6x(View view)
    {
        passX=6;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid7x(View view)
    {
        passX=7;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid8x(View view)
    {
        passX=8;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vid9x(View view)
    {
        passX=9;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }

    public void vidx(View view)
    {
        passX=10;
        Intent intent = new Intent(VideoActivity.this, YouPlayer.class);
        intent.putExtra("place_id", passX);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
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
