package ch.peter.einkaufsliste;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView;

import ch.peter.einkaufsliste.databinding.RecyclerViewShoppingPlanBinding;

var shoppingPlans = mutableListOf<ShoppingPlan>()


class ShoppingPlanAdapter: RecyclerView.Adapter<ShoppingPlanAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding: RecyclerViewShoppingPlanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewShoppingPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return shoppingPlans.size
    }
}