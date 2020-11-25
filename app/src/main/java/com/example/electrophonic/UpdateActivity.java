package com.example.electrophonic;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    EditText name, qty, price, description;
    private Product u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.name);
        qty = findViewById(R.id.qty);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);

        u = getIntent().getParcelableExtra("product");
        name.setText(u.getName());
        qty.setText(u.getQty());
        price.setText(u.getPrice());
        description.setText(u.getDescription());
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> product = new HashMap<>();
                product.put("name", name.getText().toString());
                product.put("qty", qty.getText().toString());
                product.put("price", price.getText().toString());
                product.put("description", description.getText().toString());
                db.collection("products").document(u.getId()).update(product)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
            }
        });
    }
}
