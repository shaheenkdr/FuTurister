package obx.com.futurister;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import obx.com.futurister.provider.MyFileContentProvider;


public class OptionChooser extends ActionBarActivity  {

    private String locReceive;
    static int TAKE_PIC =1;
    Uri outPutfileUri;


    private final int CAMERA_RESULT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_chooser);
        ImageView futour = (ImageView)findViewById(R.id.futourImage);
        futour.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView board = (ImageView)findViewById(R.id.boardImage);
        board.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView discover = (ImageView)findViewById(R.id.discoverImage);
        discover.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView guidex = (ImageView)findViewById(R.id.guideImage);
        guidex.setScaleType(ImageView.ScaleType.CENTER_CROP);

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

        //TextView t1 = (TextView)findViewById(R.id.info_text);
        //t1.setText(locReceive);
    }

    public void cameraFuture(View view)
    {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "MyPhoto.jpg");
        outPutfileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, TAKE_PIC);
    }

    public void vidPlay(View view)
    {
        Intent intent = new Intent(OptionChooser.this, VideoActivity.class);
        startActivity(intent);

    }

    public void goBack(View view)
    {
        finish();
        Intent intent = new Intent(OptionChooser.this, Futurister_Home_Screen.class);
        startActivity(intent);
    }

    public void bigBoard(View view)
    {
        Intent intent = new Intent(OptionChooser.this, BigBoard.class);
        intent.putExtra("LOCATION", locReceive);
        startActivity(intent);
    }

    public void helpDial(View view)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(OptionChooser.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Features");
        builder.setMessage("Explore the features one by one, that'd make your journey exhilarating. Some are still under development, and will be hitting Futurister Soon. Hope it helps!");
        builder.setPositiveButton("Got it", null);//second parameter used for onclicklistener

        builder.show();
    }

    public void guideLaunch(View view)
    {
        finish();
        Intent abcc = new Intent(OptionChooser.this,Guide.class);
        startActivity(abcc);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PIC && resultCode==RESULT_OK){
            Toast.makeText(this, outPutfileUri.toString(),Toast.LENGTH_LONG).show();
            //Bitmap mBitmap = BitmapFactory.decodeFile(out.getAbsolutePath());

            Intent bitIntent = new Intent(this, CameraTake.class);
            bitIntent.putExtra("imageUri", outPutfileUri);
            startActivity(bitIntent);
            finish();

           // Matrix matrix = new Matrix();
            //matrix.postRotate(90);
            //rotatedBitmap = Bitmap.createBitmap(mBitmap , 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option_chooser, menu);
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
