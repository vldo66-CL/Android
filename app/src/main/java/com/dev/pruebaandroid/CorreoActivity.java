package com.dev.pruebaandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CorreoActivity extends AppCompatActivity {

    Button btnenviar;
    EditText edtcuerpomail, emailreceptor, edtasunto;
    ImageView imagen1;
    private static final int PICK_FILE_REQUEST = 1;
    private Uri archivoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);


        btnenviar = findViewById(R.id.btnenviar);
        edtasunto = findViewById(R.id.edtasunto);
        edtcuerpomail = findViewById(R.id.edtcuerpomail);
        emailreceptor = findViewById(R.id.emailreceptor);
        imagen1 = findViewById(R.id.imagen1);

        //cuando se hace clic en la imagen, abrir el selector de archivos
        imagen1.setOnClickListener(v -> abrirSelectorDeArchivos());
    }

    private void abrirSelectorDeArchivos() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // permite cualquier tipo de archivo
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    // Cuando el usuario elige un archivo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            archivoUri = data.getData(); // guardamos la ruta del archivo seleccionado
        }
    }


    public void FormularioMail(View view) {
        String receptor = emailreceptor.getText().toString().trim();
        String asunto = edtasunto.getText().toString().trim();
        String cuerpoMail = edtcuerpomail.getText().toString().trim();

        if (receptor.isEmpty() || asunto.isEmpty() || cuerpoMail.isEmpty()) {
            if (receptor.isEmpty()) emailreceptor.setError("Ingrese el email del destinatario");
            if (asunto.isEmpty()) edtasunto.setError("Ingrese el asunto");
            if (cuerpoMail.isEmpty()) edtcuerpomail.setError("Ingrese el mensaje");
            return;
        }

        // Crear el Intent para enviar el correo
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{receptor});
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, cuerpoMail);


        if (archivoUri != null) {
            intent.putExtra(Intent.EXTRA_STREAM, archivoUri);
        }

        startActivity(Intent.createChooser(intent, "Elige un cliente de correo"));
    }
}
