package com.dev.pruebaandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        Button correoButton = findViewById(R.id.correo_button);
        correoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CorreoActivity.class);
                startActivity(intent);
            }
        });

        Button btnUbicacion = findViewById(R.id.btnUbicacion);
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "geo:33.4493142,-70.6627046,21z=-33.4493142,-70.6627046(Santiago de Chile)33.4493142,-70.6627046,21z=-33.4493142,-70.6627046(Santiago de Chile)";
                Uri gmmIntentUri = Uri.parse(geoUri);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                //hacemos un if para ver si tenemos la aplicacion google maps instalada

                if (mapIntent.resolveActivity(getPackageManager())!= null){
                    startActivity(mapIntent);
                }else{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/maps?q=-33.4493142,-70.6627046"));
                    startActivity(browserIntent);
                }
            }
        });

        Button btnWeb = findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.santotomas.cl";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));

                startActivity(intent);
            }
        });
    }
}
