package ch.peter.einkaufsliste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShoppingPlanListActivity extends AppCompatActivity implements View.OnClickListener {


    private FloatingActionButton add;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    ListView listview;
    ArrayList<ShoppingPlan> list = new ArrayList<ShoppingPlan>();
    ArrayList<String> keyList = new ArrayList<String>();
    ShoppingPlansAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_plan_list);
        this.listview = findViewById(R.id.shopping_plan_list_view);


        reference = FirebaseDatabase.getInstance().getReference("ShoppingPlans");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        Context context = this;

        add = findViewById(R.id.add);
        add.setOnClickListener(this);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                keyList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren())
                {
                    ShoppingPlan shoppingPlan = snapshot1.getValue(ShoppingPlan.class);
                    keyList.add(snapshot1.getKey());
                    list.add(shoppingPlan);
                }
                arrayAdapter = new ShoppingPlansAdapter(context, list);
                listview.setAdapter(arrayAdapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String key = keyList.get(position);
                        Intent intent = new Intent(ShoppingPlanListActivity.this, ShoppingPlanActivity.class);
                        String intentExtra = "showExisting";
                        intent.putExtra("extra", intentExtra);
                        intent.putExtra("key", key);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add:
                Intent intent = new Intent(this, ShoppingPlanActivity.class);
                String intentExtra = "create";
                intent.putExtra("intent", intentExtra);
                startActivity(intent);
                break;
        }
    }
}