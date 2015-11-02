package obx.com.futurister;

import android.content.Intent;
import android.os.Build;
import android.view.View;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.LinearLayout;

import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class YouPlayer extends YouTubeFailureRecoveryActivity implements
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        YouTubePlayer.OnFullscreenListener {

    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

    private LinearLayout baseLayout;
    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    private Button fullscreenButton;
    private CompoundButton checkbox;
    private View otherViews;
    private int valueX;
    private String play_token;


    private TextView placeDesc;
    private TextView titleSetter;

    private boolean fullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_player);

        Intent receive = getIntent();
        valueX = receive.getIntExtra("place_id",0);

        titleSetter = (TextView)findViewById(R.id.titleTextYou);
        placeDesc = (TextView)findViewById(R.id.textDescYou);
        baseLayout = (LinearLayout) findViewById(R.id.layout);
        playerView = (YouTubePlayerView) findViewById(R.id.player);
        fullscreenButton = (Button) findViewById(R.id.fullscreen_button);
        checkbox = (CompoundButton) findViewById(R.id.landscape_fullscreen_checkbox);
        otherViews = findViewById(R.id.other_views);

        checkbox.setOnCheckedChangeListener(this);
        // You can use your own button to switch to fullscreen too
        fullscreenButton.setOnClickListener(this);

        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

        if(valueX==1)
        {
            titleSetter.setText("Kashmir, India");
            play_token="9l7wiqEGrfc";

            placeDesc.setText(getResources().getString(R.string.kashmir));
        }
        if(valueX==2)
        {
            titleSetter.setText("London, England");
            play_token="PtWeqZsuzpE";
            placeDesc.setText(getResources().getString(R.string.london));
        }
        if(valueX==3)
        {
            titleSetter.setText("Paris, France");
            play_token="x0Pa8aIqmNI";
            placeDesc.setText(getResources().getString(R.string.paris));
        }
        if(valueX==4)
        {
            titleSetter.setText("Himalayas, India");
            play_token="ZQnDpCjtSfE";
            placeDesc.setText(getResources().getString(R.string.himalaya));
        }
        if(valueX==5)
        {
            titleSetter.setText("St Petersburg");
            play_token="LAxf-05NTRY";
            placeDesc.setText(getResources().getString(R.string.russia));
        }
        if(valueX==6)
        {
            titleSetter.setText("Rome, Italy");
            play_token="h9fHP9IvbiI";
            placeDesc.setText(getResources().getString(R.string.rome));
        }
        if(valueX==7)
        {
            titleSetter.setText("Thailand");
            play_token="HL69WXRQrO0";
            placeDesc.setText(getResources().getString(R.string.thailand));
        }
        if(valueX==8)
        {
            titleSetter.setText("Rajasthan, India");
            play_token="CES7WqrYuSE";
            placeDesc.setText(getResources().getString(R.string.Rajasthan));
        }
        if(valueX==9)
        {
            titleSetter.setText("Barcelona");
            play_token="L_bgTJkFk3k";
            placeDesc.setText(getResources().getString(R.string.Barcelona));
        }
        if(valueX==10)
        {
            titleSetter.setText("Budapest, Hungary");
            play_token="B_Hfmp-z7AE";
            placeDesc.setText(getResources().getString(R.string.Budapest));
        }


        doLayout();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        this.player = player;
        setControlsEnabled();
        // Specify that we want to handle fullscreen behavior ourselves.
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener(this);
        if (!wasRestored) {
            player.cueVideo(play_token);
        }
    }

    public void goBackYou(View view)
    {
        finish();
        Intent abc = new Intent(YouPlayer.this,VideoActivity.class);
        startActivity(abc);
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    @Override
    public void onClick(View v) {
        player.setFullscreen(!fullscreen);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int controlFlags = player.getFullscreenControlFlags();
        if (isChecked) {
            // If you use the FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE, your activity's normal UI
            // should never be laid out in landscape mode (since the video will be fullscreen whenever the
            // activity is in landscape orientation). Therefore you should set the activity's requested
            // orientation to portrait. Typically you would do this in your AndroidManifest.xml, we do it
            // programmatically here since this activity demos fullscreen behavior both with and without
            // this flag).
            setRequestedOrientation(PORTRAIT_ORIENTATION);
            controlFlags |= YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            controlFlags &= ~YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
        }
        player.setFullscreenControlFlags(controlFlags);
    }

    private void doLayout() {
        LinearLayout.LayoutParams playerParams =
                (LinearLayout.LayoutParams) playerView.getLayoutParams();
        if (fullscreen) {
            // When in fullscreen, the visibility of all other views than the player should be set to
            // GONE and the player should be laid out across the whole screen.
            playerParams.width = LayoutParams.MATCH_PARENT;
            playerParams.height = LayoutParams.MATCH_PARENT;

            otherViews.setVisibility(View.GONE);
        } else {
            // This layout is up to you - this is just a simple example (vertically stacked boxes in
            // portrait, horizontally stacked in landscape).
            otherViews.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams otherViewsParams = otherViews.getLayoutParams();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                playerParams.width = otherViewsParams.width = 0;
                playerParams.height = WRAP_CONTENT;
                otherViewsParams.height = MATCH_PARENT;
                playerParams.weight = 1;
                baseLayout.setOrientation(LinearLayout.HORIZONTAL);
            } else {
                playerParams.width = otherViewsParams.width = MATCH_PARENT;
                playerParams.height = WRAP_CONTENT;
                playerParams.weight = 0;
                otherViewsParams.height = 0;
                baseLayout.setOrientation(LinearLayout.VERTICAL);
            }
            setControlsEnabled();
        }
    }

    private void setControlsEnabled() {
        checkbox.setEnabled(player != null
                && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        fullscreenButton.setEnabled(player != null);
    }

    @Override
    public void onFullscreen(boolean isFullscreen) {
        fullscreen = isFullscreen;
        doLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        doLayout();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_you_player, menu);
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
