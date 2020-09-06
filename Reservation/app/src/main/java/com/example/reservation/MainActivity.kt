package com.example.reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(p0: View?) {
        if(p0?.id==clear_button.id){
            reserveList.clear()
            outside_button.isChecked=false
            showItems()
        }
        if (p0?.id==sort_button.id){
            reserveList.sortBy{it.people_num}
            showItems()
        }
    }
    val reserveList=ArrayList<Reservation>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_button.setOnClickListener {
            val name = user_name.text.toString()
            val phone = user_phone.text.toString()
            val num_people = user_people_num.text.toString().toInt()
            val isOutside = outside_button.isChecked
//            Log.d("package", "$name, $weight")
//            Log.d("price rate", Item.ItemPrice.joinToString { "$${it}" })
            if (name.isNotBlank() && phone.isNotBlank()) {
                val reserve = Reservation(name=name, phone_num=phone,
                                          people_num=num_people, isOutside=isOutside)
                reserveList.add(reserve)
            }
            outside_button.isChecked=false
            showItems()
        }
        clear_button.setOnClickListener(this)
        sort_button.setOnClickListener(this)
    }

    fun showItems(){
        var totalPeople=0
        for (item in reserveList) {
            totalPeople += item.people_count
        }
        var info="Total reservation: ${reserveList.size}, Total num of people: $totalPeople\n"
        for (index in 0 until reserveList.size){
            info += reserveList[index].getRecord(index) + "\n"
        }
        textView.text=info
        user_name.text.clear()
        user_phone.text.clear()
        user_people_num.text.clear()
    }


}