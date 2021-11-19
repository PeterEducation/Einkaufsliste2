package ch.peter.einkaufsliste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class ShoppingPlanActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, createShoppingPlan;
    private EditText editTextShopName, editTextCategory;
    private ProgressBar progressBar;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_plan);


        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        createShoppingPlan = (Button) findViewById(R.id.createShoppingPlan);
        createShoppingPlan.setOnClickListener(this);

        editTextShopName = (EditText) findViewById(R.id.shopName);
        editTextCategory = (EditText) findViewById(R.id.category);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("ShoppingPlans");
        userID = user.getUid();

        Intent intent = getIntent();
        String intentExtra = intent.getStringExtra("extra");
        String key = intent.getStringExtra("key");
        if(intentExtra == "showExisting")
        {
            getShoppingPlan(key);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.banner:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.createShoppingPlan:
                createShoppingPlan();
                break;
        }
    }

    private void createShoppingPlan()
    {
        String shopName = editTextShopName.getText().toString().trim();
        String category = editTextCategory.getText().toString().trim();


        if (shopName.isEmpty()) {
            editTextShopName.setError("Name des Ladens wird benötigt!");
            editTextShopName.requestFocus();
            return;
        }

        if (category.isEmpty()) {
            editTextCategory.setError("Name der Kategorie wird benötigt!");
            editTextCategory.requestFocus();
            return;
        }



        ShoppingPlan shoppingPlan = new ShoppingPlan(shopName, category, userID);

        progressBar.setVisibility(View.VISIBLE);

        String uniqueID = UUID.randomUUID().toString();

        FirebaseDatabase.getInstance().getReference("ShoppingPlans")
                .child(uniqueID)
                .setValue(shoppingPlan).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(ShoppingPlanActivity.this, "Einkaufsplan wurde erfolgreich erstellt!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(ShoppingPlanActivity.this, "Einkaufsplan erstellen fehlgeschlagen! Versuchen Sie es erneut!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void getShoppingPlan(String shoppingPlanID)
    {
        reference.child(shoppingPlanID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ShoppingPlan shoppingPlan = snapshot.getValue(ShoppingPlan.class);

                if (shoppingPlan != null)
                {
                    String shopName = shoppingPlan.shopName;
                    String category = shoppingPlan.category;

                    editTextShopName.setText(shopName);
                    editTextCategory.setText(category);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}