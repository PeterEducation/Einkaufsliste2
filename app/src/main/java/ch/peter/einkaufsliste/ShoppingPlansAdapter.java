package ch.peter.einkaufsliste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.google.firebase.database.core.Context;
import android.content.Context;

import java.util.ArrayList;

public class ShoppingPlansAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<ShoppingPlan> arrayList;
    private TextView shopName, category;
    public ShoppingPlansAdapter(Context context, ArrayList<ShoppingPlan> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.shopping_plan, parent, false);
        shopName = convertView.findViewById(R.id.shopName);
        category = convertView.findViewById(R.id.category);
        shopName.setText(" " + arrayList.get(position).getShopName());
        category.setText(arrayList.get(position).getCategory());
        return convertView;
    }
}
