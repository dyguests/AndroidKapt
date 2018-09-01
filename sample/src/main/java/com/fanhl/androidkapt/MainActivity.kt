package com.fanhl.androidkapt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.fanhl.androidkapt.annotations.BindField
import com.fanhl.androidkapt.processor.generated.GeneratedClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, GeneratedClass().message, Toast.LENGTH_SHORT).show()
    }

    @BindField(viewIds = ["name", "amount"], viewName = "itemView")
    fun bind(item: Bill) {
//        bindFields(item, itemViewHere)
    }

}

data class Bill(val name: String, val amount: String)