package obx.com.futurister;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.io.File;


public class CameraTake extends ActionBarActivity {
    Bitmap bitmap;
    Intent shareIntent = new Intent();
    Uri uri;
    String receiveImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_camera_take);

        Intent receiveIntent = getIntent();

        uri = receiveIntent.getParcelableExtra("imageUri");


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                receiveImg= null;
            } else {
                receiveImg= extras.getString("PASSER");
            }
        } else {
            receiveImg= (String) savedInstanceState.getSerializable("PASSER");
        }




        File imgFile = new  File(Environment.getExternalStorageDirectory(),"MyPhoto.jpg");

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.camOut);

            myImage.setImageBitmap(myBitmap);

        }





       // bitmap = BitmapFactory.decodeFile(receiveImg);
       // ImageView im1 = (ImageView)findViewById(R.id.camOut);
        //im1.setImageBitmap(bitmap);
        //im1.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    public void goBackOptions(View view)
    {
        finish();
        Intent myx = new Intent(CameraTake.this,OptionChooser.class);
        startActivity(myx);

    }

    public void helpDialShare(View view)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(CameraTake.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("PiC");
        builder.setMessage("Share the Futorfie to your beloved friends with the share button below!");
        builder.setPositiveButton("Got it", null);//second parameter used for onclicklistener

        builder.show();

    }

    public void shareIt(View view)
    {
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_take, menu);
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
