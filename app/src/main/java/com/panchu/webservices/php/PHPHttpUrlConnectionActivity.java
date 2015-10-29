package com.panchu.webservices.php;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.panchu.services.php.HttpUrlConnectionParser;
import com.panchu.webservices.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class PHPHttpUrlConnectionActivity extends AppCompatActivity {

    private TextView result;
    private EditText name;
    private Button connect;
    private ProgressDialog pDialog;
    private String url="http://server path/filename.php";  //server url link
    private JSONArray jsonArray=null;
    private HashMap<String,String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phphttp_url_connection);

        name= (EditText) findViewById(R.id.name);
        result= (TextView) findViewById(R.id.result);
        connect= (Button) findViewById(R.id.connect);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute();
            }
        });
    }

    public class getData extends AsyncTask<String,String,String>{
        String value=name.getText().toString();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog=new ProgressDialog(PHPHttpUrlConnectionActivity.this);
            pDialog.setIndeterminate(false);
            pDialog.setMessage("Connecting...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {


            data=new HashMap<String,String>();
            data.put("name",value);

            try{
                JSONObject json= HttpUrlConnectionParser.makeHttpUrlConnection(url, data);

                int succ=json.getInt("success");
                if(succ==0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    jsonArray=json.getJSONArray("result");
                    JSONObject child=jsonArray.getJSONObject(0);

                    final String ans=child.optString("reply");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(ans.toString());
                        }
                    });

                }

            }catch (Exception e){}


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.cancel();
        }
    }
}
