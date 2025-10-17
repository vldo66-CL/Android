package com.dev.pruebaandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GPSActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUbicacion = findViewById(R.id.btnUbicacion);

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "geo:33.4489,-70.6693?q=-33.4489,-70.6693(Santiago de Chile)33.4489,-70.6693?q=-33.4489,-70.6693(Santiago de Chile)";
                Uri gmmIntentUri = Uri.parse(geoUri);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                //hacemos un if para ver si tenemos la aplicacion google maps instalada

                if (mapIntent.resolveActivity(getPackageManager())!= null){
                    startActivity(mapIntent);
                }else{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/maps?q=-33.4489,-70.6693"));
                    startActivity(browserIntent);
                }
            }
        });
    }
}
