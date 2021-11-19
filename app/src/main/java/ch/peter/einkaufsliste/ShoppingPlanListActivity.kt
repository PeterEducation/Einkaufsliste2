package ch.peter.einkaufsliste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ch.peter.einkaufsliste.databinding.ActivityShoppingPlanListBinding

class ShoppingPlanListActivity : AppCompatActivity() {

    private var _binding: ActivityShoppingPlanListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_plan_list)

        _binding = ActivityShoppingPlanListBinding.inflate()
    }


}