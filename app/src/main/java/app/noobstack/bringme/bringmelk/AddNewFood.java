package app.noobstack.bringme.bringmelk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import app.noobstack.bringme.bringmelk.model.Food;

public class AddNewFood extends AppCompatActivity {

    private ImageButton selectImgbtn;
    private EditText itemName;
    private EditText itemPrice;
    private EditText itemDiscount;
    private EditText itemDescription;
    private Button submitBtn;
    Uri imageUri = null;
    private StorageReference storage;
    private DatabaseReference dbRef;
    String uniqueId;
    ProgressDialog progress;
    Food newFood;

    private static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);

        newFood = new Food();
        storage = FirebaseStorage.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference().child("foods");

       selectImgbtn = findViewById(R.id.addImgBtn);
       itemName = findViewById(R.id.etitemName);
       itemPrice = findViewById(R.id.etPrice);
       itemDiscount = findViewById(R.id.et_Discount);
       itemDescription = findViewById(R.id.etitemdescription);
       submitBtn = findViewById(R.id.btnSubmitNewItem);
       progress = new ProgressDialog(this);
       selectImgbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });
       submitBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addItem();
           }
       });
    }

    private void addItem() {

        newFood.setTitle(itemName.getText().toString().trim());
        newFood.setDescription(itemDescription.getText().toString().trim());
        newFood.setPrice(itemPrice.getText().toString().trim());
        newFood.setDiscount(itemDiscount.getText().toString().trim());
        newFood.setId(generateId());

        if (TextUtils.isEmpty(newFood.getTitle())) {
            Toast.makeText(AddNewFood.this, "Please Enter Food Name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(newFood.getDescription())) {
            Toast.makeText(AddNewFood.this, "Please Enter Description", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(newFood.getPrice())) {
            Toast.makeText(AddNewFood.this, "Please Enter a Price", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(newFood.getDiscount())) {
            Toast.makeText(AddNewFood.this, "Please Enter Discount", Toast.LENGTH_SHORT).show();
        }
        else {

            if (Float.parseFloat(newFood.getDiscount()) > 100 || Float.parseFloat(newFood.getDiscount()) < 0) {
                Toast.makeText(AddNewFood.this, "Please Enter a Discount between 0 and 100", Toast.LENGTH_SHORT).show();
            }

        else{
            progress.setMessage("Adding new Item");
            progress.show();

            final StorageReference filePath = storage.child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filePath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {


                        newFood.setImage(task.getResult().toString());
                        //dbRef.push().setValue(newFood);
                        dbRef.child(newFood.getId()).setValue(newFood);
                        progress.dismiss();
                        Intent i = new Intent(AddNewFood.this, ManageFoodActivity.class);
                        startActivity(i);


                    } else {
                        Toast.makeText(AddNewFood.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    }

    private String generateId() {
         uniqueId = UUID.randomUUID().toString();
         return uniqueId;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode== RESULT_OK){

            imageUri = data.getData();
            selectImgbtn.setImageURI(imageUri);

        }
    }
}