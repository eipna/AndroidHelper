package com.serbi.sampleprofilepicture;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class EditActivity extends AppCompatActivity {

    private ImageView user_profile;
    private EditText edit_userFirstName, edit_userLastName;
    private Button btn_save, btn_back;

    private Uri uri;
    private Bitmap bitmap;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        user_profile = findViewById(R.id.edit_user_profile);
        edit_userFirstName = findViewById(R.id.edit_firstName);
        edit_userLastName = findViewById(R.id.edit_lastName);
        btn_back = findViewById(R.id.btn_back);
        btn_save = findViewById(R.id.btn_save);
        databaseHelper = new DatabaseHelper(EditActivity.this);

        Cursor cursor = databaseHelper.getUser();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No user details", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                edit_userFirstName.setText(cursor.getString(1));
                edit_userLastName.setText(cursor.getString(2));
                byte[] byteImage = cursor.getBlob(3);

                Bitmap image = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                user_profile.setImageBitmap(image);
            }
        }

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == EditActivity.RESULT_OK) {
                            Intent data = result.getData();
                            assert data != null;
                            uri = data.getData();

                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            } catch (IOException e) {
                                Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            user_profile.setImageBitmap(bitmap);
                        } else {
                            Toast.makeText(EditActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    activityResultLauncher.launch(intent);
                } catch (Exception e) {
                    Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeImage();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void storeImage() {
        if (!edit_userFirstName.getText().toString().isEmpty() && !edit_userLastName.getText().toString().isEmpty()
                && user_profile.getDrawable() != null && bitmap != null) {
            databaseHelper.storeData(new UserModel(edit_userFirstName.getText().toString(), edit_userLastName.getText().toString(), bitmap));
            startActivity(new Intent(EditActivity.this, MainActivity.class));
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}