package com.dev.pruebaandroid;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import java.io.File;

public class CorreoActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 100;
    private Uri attachmentUri = null;

    private EditText recipientEditText;
    private EditText subjectEditText;
    private EditText messageEditText;
    private TextView attachedFileTextView;


    private static final int CAMERA_REQUEST_CODE = 200;
    private Uri photoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);

        Toolbar toolbar = findViewById(R.id.toolbar_correo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());


        recipientEditText = findViewById(R.id.recipient_edittext);
        subjectEditText = findViewById(R.id.subject_edittext);
        messageEditText = findViewById(R.id.message_edittext);
        attachedFileTextView = findViewById(R.id.attached_file_textview);
        Button attachButton = findViewById(R.id.attach_button);
        Button sendButton = findViewById(R.id.send_button);
        ImageView camaraIMG = findViewById(R.id.camaraIMG);

        camaraIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = File.createTempFile(
                        "foto_",
                        ".jpg",
                        getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                );
            } catch (Exception ex) {
                Toast.makeText(this, "Error al crear archivo de imagen", Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(
                        this,
                        getPackageName() + ".provider",
                        photoFile
                );
                takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar archivo"), PICK_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            attachmentUri = data.getData();
            String fileName = getFileName(attachmentUri);
            attachedFileTextView.setText("Archivo adjunto: " + fileName);
            attachedFileTextView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Archivo adjunto: " + fileName, Toast.LENGTH_SHORT).show();
        }
        else {
            attachmentUri = null;
            attachedFileTextView.setVisibility(View.GONE);
        }
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            attachmentUri = photoUri;
            attachedFileTextView.setText("Foto adjunta: " + getFileName(photoUri));
            attachedFileTextView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Foto capturada y lista para enviar", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


    private void sendEmail() {
        String recipient = recipientEditText.getText().toString();
        String subject = subjectEditText.getText().toString();
        String message = messageEditText.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822"); // For email apps
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        if (attachmentUri != null) {
            emailIntent.putExtra(Intent.EXTRA_STREAM, attachmentUri);
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar correo usando..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CorreoActivity.this, "No hay clientes de correo instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}
