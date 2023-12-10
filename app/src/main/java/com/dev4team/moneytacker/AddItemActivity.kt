package com.dev4team.moneytacker

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class AddItemActivity : AppCompatActivity() {

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item)


        val toolbar = findViewById<Toolbar>(R.id.add_item_topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle(R.string.add_item_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val nameField = findViewById<EditText>(R.id.item_name)
        val priceField = findViewById<EditText>(R.id.amount)
        val addItemButton = findViewById<Button>(R.id.add_button)

        type = intent.getStringExtra(TYPE_KEY)!!

        addItemButton.setOnClickListener {
            val nameValue = nameField.text.toString()
            val priceValue = priceField.text.toString()

            val intent = Intent()
            intent.putExtra("name", nameValue)
            intent.putExtra("price", priceValue)
            setResult(RESULT_OK, intent)
            finish()
        }
    }



    companion object {
        private const val TAG = "AddItemActivity"
        const val TYPE_KEY = "type"
    }

}


