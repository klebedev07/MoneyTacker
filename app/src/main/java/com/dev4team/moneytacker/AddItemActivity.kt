package com.dev4team.moneytacker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item)

        val nameField = findViewById<EditText>(R.id.item_name)
        val amountField = findViewById<EditText>(R.id.amount)
        val addItemButton = findViewById<Button>(R.id.add_button)

        val addItemActivityTextWatcher = AddItemActivityTextWatcher(
            nameField = nameField,
            amountField = amountField,
            addItemButton = addItemButton
        )


        nameField.addTextChangedListener(addItemActivityTextWatcher)
        amountField.addTextChangedListener(addItemActivityTextWatcher)
    }

    class AddItemActivityTextWatcher(
        val nameField: EditText,
        val amountField: EditText,
        val addItemButton: Button
    ) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            Log.i(TAG, "afterTextChanged $s")
            if (nameField.text.isNotBlank() && amountField.text.isNotBlank()) {
                addItemButton.isEnabled = true
            }

            if (nameField.text.isBlank() || amountField.text.isBlank()) {
                addItemButton.isEnabled = false
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Log.i(TAG, " beforeTextChanged s: $s, start: $start, count: $count, after: $after")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.i(TAG, "onTextChanged s: $s, start: $start, count: $count, before: $before")
        }
    }

    companion object {
        private const val TAG = "AddItemActivity"
    }

}


