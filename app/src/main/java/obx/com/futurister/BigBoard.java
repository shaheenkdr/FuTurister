package obx.com.futurister;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;


public class BigBoard extends ActionBarActivity {

    private List<Person> persons;
    private RecyclerView rv;
    private RVAdapter adapter;
    private String a,b,c;
    private ProgressDialog pr;
    private String locReceive;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "app-id", "client-key");
        setContentView(R.layout.activity_big_board);
        initializeData();

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
        adapter = new RVAdapter(persons);
        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeAdapter();

    }

    private void initializeData(){
        persons = new ArrayList<>();

        pr = new ProgressDialog(BigBoard.this);
        pr.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pr.setIndeterminate(true);
        pr.setCancelable(false);
        pr.setMessage("Loading Board");
        pr.show();


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("BigBoard");
        query.findInBackground(new FindCallback<ParseObject>()
        {
            public void done(List<ParseObject> credentialList, ParseException e) {



                if (e == null)
                {
                    for(int i=0;i<credentialList.size();i++)
                    {
                        a=credentialList.get(i).getString("Location");
                        b=credentialList.get(i).getString("Feed");
                        //c=credentialList.get(i).getString("Alias");
                        persons.add(new Person(a,b));



                        Log.d("OUT", "So the Val::------> " +a +b +c);
                        adapter.notifyDataSetChanged();



                    }
                    pr.dismiss();
                }
                else
                {
                    Log.d("score", "Error: " + e.getMessage());
                }





            }
        });



    }

    private void initializeAdapter(){


        rv.setAdapter(adapter);
    }

    public void feed(View view)
    {
        Intent intent = new Intent(BigBoard.this, FeedActivity.class);
        intent.putExtra("LOCATION", locReceive);
        startActivity(intent);
    }



    public void backOpt(View view)
    {
        finish();
        Intent big = new Intent(BigBoard.this,OptionChooser.class);
        startActivity(big);

    }

    public void boardHelp(View view)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(BigBoard.this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Features");
        builder.setMessage("Explore the big board, or click the button at the buttom to write on the big board");
        builder.setPositiveButton("Got it", null);//second parameter used for onclicklistener

        builder.show();

    }




}


