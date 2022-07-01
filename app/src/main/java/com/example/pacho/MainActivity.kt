package com.example.pacho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

	val spinner: Spinner = findViewById(R.id.Spinner1)
	ArrayAdapter.createFromResource(
            this,
            R.array.estado_civil_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		     // Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 // Apply the adapter to the spinner
		 spinner.adapter = adapter
	}


	val spinner2: Spinner = findViewById(R.id.Spinner2)
	ArrayAdapter.createFromResource(
            this,
            R.array.estado_civil_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		     // Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 // Apply the adapter to the spinner
		 spinner2.adapter = adapter
	}

	val spinner3: Spinner = findViewById(R.id.Spinner3)
	ArrayAdapter.createFromResource(
            this,
            R.array.estado_civil_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		     // Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 // Apply the adapter to the spinner
		 spinner3.adapter = adapter
	}

	val spinner4: Spinner = findViewById(R.id.Spinner4)
	ArrayAdapter.createFromResource(
            this,
            R.array.estado_civil_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		     // Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 // Apply the adapter to the spinner
		 spinner4.adapter = adapter
	}

	val spinner5: Spinner = findViewById(R.id.Spinner5)
	ArrayAdapter.createFromResource(
            this,
            R.array.grado_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		     // Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 // Apply the adapter to the spinner
		 spinner5.adapter = adapter
	}


    }
}
