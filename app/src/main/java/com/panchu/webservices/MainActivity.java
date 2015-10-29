package com.panchu.webservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.panchu.webservices.php.PHPHttpClientActivity;
import com.panchu.webservices.php.PHPHttpUrlConnectionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button php,php1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        php= (Button) findViewById(R.id.php);
        php1= (Button) findViewById(R.id.php2);

        php.setOnClickListener(this);
        php1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.php:
                startActivity(new Intent(this, PHPHttpClientActivity.class));
                break;
            case R.id.php2:
                startActivity(new Intent(this, PHPHttpUrlConnectionActivity.class));
                break;
        }

    }
}
