package com.fanhl.androidkapt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fanhl.androidkapt.annotations.BindField

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @BindField(viewIds = ["name", "amount"], viewName = "itemView")
    fun bind(item: Bill) {
//        bindFields(item, itemViewHere)
    }

}

data class Bill(val name: String, val amount: String)