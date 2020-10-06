package com.example.burgerapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


private val TextView.setImageResource: Unit
    get() {}

class RecyclerViewAdapter(var foodData: Array<Food>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(
            R.layout.food_view,
            parent,
            false
        )
        return RecyclerViewHolder(viewItem)
    }

    lateinit var clickLambda: (Food) -> Unit

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(foodData[position], clickLambda)
    }

    override fun getItemCount(): Int {
        return foodData.size
    }

    class RecyclerViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(food: Food, clickLambda: (Food) -> Unit) {
            viewItem.findViewById<TextView>(R.id.name_text).text = food.name
            viewItem.findViewById<TextView>(R.id.calorie_text).text = food.calorie+" Calories"
            val check = viewItem.findViewById<CheckBox>(R.id.checkbox)
            check.setOnClickListener {
                if (check.isChecked)
                    food.isSelected=true
                if (!check.isChecked)
                    food.isSelected=false
            }

            val drawableResourceId = viewItem.getResources().getIdentifier(food.imageId, "drawable", "com.example.burgerapp")
            viewItem.findViewById<ImageView>(R.id.profile_image).setImageResource(drawableResourceId)
            viewItem.setOnClickListener {
                clickLambda(food)
            }
        }
    }


}