package com.example.pacho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import android.widget.Spinner
import android.widget.ArrayAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

	val spinner: Spinner = findViewById(R.id.ciudad)
	ArrayAdapter.createFromResource(
            this,
            R.array.ciudades_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		     // Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 // Apply the adapter to the spinner
		 spinner.adapter = adapter
	}

    }
}
