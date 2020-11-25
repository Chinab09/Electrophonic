package com.example.electrophonic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    EditText name,qty,price,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = FirebaseFirestore.getInstance();
        name= findViewById(R.id.name);
        qty= findViewById(R.id.qty);
        price= findViewById(R.id.price);
        description= findViewById(R.id.description);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> product = new HashMap<>();
                product.put("name", name.getText().toString());
                product.put("qty", qty.getText().toString());
                product.put("price", price.getText().toString());
                product.put("description",description.getText().toString());
                db.collection("products")
                        .add(product)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(Constants.TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(Constants.TAG, "Error adding document", e);
                            }
                        });
            }
        });


    }
}
