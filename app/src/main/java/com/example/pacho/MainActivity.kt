package com.example.pacho

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import android.os.Bundle

import android.widget.TextView
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.DatePicker

import android.app.DatePickerDialog
import android.app.FragmentManager

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener

import com.example.pacho.SelectorFecha

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
	fun Int.dosDigitos() =
	    if (this < 9) "0$this" else this.toString()

	val fechaNacimiento: EditText = findViewById(R.id.EditText4)
	fechaNacimiento.setOnClickListener {
	    SelectorFecha.newInstance { _, anio, mes, dia ->
					    val ndia = dia.dosDigitos()
					val nmes = mes.dosDigitos()
					val fechaSeleccionada = "$ndia/$nmes/$anio"
					fechaNacimiento.setText(fechaSeleccionada)
	    }.show(fragmentManager, "Selector de Fecha")
	    /*
	    val selector = SelectorFecha.newInstance(DatePickerDialog.OnDateSetListener { _, anio, mes, dia ->
											      val fechaSeleccionada = dia.toString() + " / " + (mes + 1) + " / " + anio
											  fechaNacimiento.setText(fechaSeleccionada)
	    })
	    //val gfragmentos = (fechaNacimiento.context as FragmentActivity).supportFragmentManager
	    //selector.show(gfragmentos, "Selector de fecha")
	     */
	}
	
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
	
	/* Id's de los cuadros de texto para configurar el escuchador y validar el campo */
	val ids: List<Int> = listOf(R.id.EditText1, R.id.EditText2, R.id.EditText3)
	for (id in ids) {
	    val cuadroTexto: EditText = findViewById(id)
	    cuadroTexto.onFocusChangeListener = View.OnFocusChangeListener {
		_, hasFocus ->
		    val contenido = cuadroTexto.getText().toString()
		if (!hasFocus && contenido != "") {
		    if (!esTextoCorrecto(contenido)) {
			cuadroTexto.setError("El contenido no es valido")
		    }
		}
	    }
	}

    }

    /*
     * Verifica si el texto ingresado de los nombres y apellidos son correctos
     */
    private fun esTextoCorrecto(texto: String): Boolean {
	texto.forEach {
	    if (!it.isLetter() && !it.isWhitespace()) {
		return false
	    }
	}
	return true
    }

}

