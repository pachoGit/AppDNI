package com.example.pacho

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import android.os.Bundle
import android.content.Context
import android.graphics.Color

import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener 
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast

import java.util.Calendar

import android.app.DatePickerDialog
import android.app.FragmentManager

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;

import com.example.pacho.SelectorFecha

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
	// Para las consultas a las API's
	val colaSolicitud = Volley.newRequestQueue(this)
	
	// Configuramos y mostramos la fecha actual en la pantalla
	val textoFecha: TextView = findViewById(R.id.TextView3)
	textoFecha.setText(obtFechaActual())
	
	// Configuracion de los escuchadores para los eventos de los cuadros de texto de los nombres y apellidos
	/* Id's de los cuadros de texto */
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
	
	// Configuracion para la seleccion de la fecha de nacimiento
	val fechaNacimiento: EditText = findViewById(R.id.EditText4)
	fechaNacimiento.setOnClickListener {
	    SelectorFecha.newInstance { _, anio, mes, dia ->
					    val ndia = dia.dosDigitos()
					val nmes = mes.dosDigitos()
					val fechaSeleccionada = "$ndia/$nmes/$anio"
					fechaNacimiento.setText(fechaSeleccionada)
	    }.show(fragmentManager, "Selector de Fecha")
	}
	
	val departamentos: Spinner = findViewById(R.id.Spinner1)
	val provincias: Spinner = findViewById(R.id.Spinner2)
	val distritos: Spinner = findViewById(R.id.Spinner3)

	// Configuracion y llenado de los departamentos
	obtDepartamentos(colaSolicitud, departamentos)
	// Configuracion de los eventos del spinner de los departamentos
	departamentos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
		
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
		val dep = (view as TextView).getText().toString()
		//Toast.makeText(applicationContext, "Presionaste: " + dep, Toast.LENGTH_LONG).show()
		val id_dep: String = dep.substring(0, 2)
		obtProvincias(colaSolicitud, id_dep, provincias)
            }
	}

	// Configuracion de los eventos del spinner de las provincias
	provincias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
		
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
		val dep = departamentos.getSelectedItem().toString()
		val prov = (view as TextView).getText().toString()
		//Toast.makeText(applicationContext, "Presionaste: " + prov, Toast.LENGTH_LONG).show()
		val id_dep: String = dep.substring(0, 2)
		val id_prov: String = prov.substring(0, 2)
		obtDistritos(colaSolicitud, id_dep, id_prov, distritos)
            }
	}
	
	// Llenado de los estados civiles al spinner
	val estadoCivil: Spinner = findViewById(R.id.Spinner4)
	ArrayAdapter.createFromResource(
            this,
            R.array.estado_civil_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 estadoCivil.adapter = adapter
	}

	// Llenado de los grados de instruccion al spinner
	val grado: Spinner = findViewById(R.id.Spinner5)
	ArrayAdapter.createFromResource(
            this,
            R.array.grado_array,
            android.R.layout.simple_spinner_item
	).also { adapter ->
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		 grado.adapter = adapter
	}
	
	val boton: Button = findViewById(R.id.b_aceptar)

	boton.setOnClickListener {
	    
	    /*
	    val apellidoPaterno: EditText = findViewById(ids.get(0))
            val apellidoMaterno: EditText = findViewById(ids.get(1))
            val nombres: EditText = findViewById(ids.get(2))

            val idsexo: Int = radio.getCheckedRadioButtonId()
            if (idsexo == -1) {
		Toast.makeText(this, "Falta completar algunos campos", Toast.LENGTH_SHORT).show()
            }
            val sexo: RadioButton = findViewById(idsexo)


            val data = mutableMapOf<Any?, Any?>()
            data.put("ApellidoPaterno",  apellidoPaterno.getText().toString())
            data.put("ApellidoMaterno",  apellidoMaterno.getText().toString())
            data.put("Nombres",          nombres.getText().toString())
            data.put("Sexo",             sexo.getText().toString())
            data.put("Departamento",     departamentos.getSelectedItem().toString())
            data.put("Provincia",        provincias.getSelectedItem().toString())
            data.put("Distrito",         distritos.getSelectedItem().toString())
            data.put("EstadoCivil",      estadoCivil.getSelectedItem().toString())
            data.put("NivelInstruccion", grado.getSelectedItem().toString())
            data.put("FechaNacimiento",  fechaNacimiento.getText().toString())
	     */


	    val url = "http://webapp.inei.gob.pe:8080/sisconcode/ubigeo/buscarDepartamentosPorVersion.htm?llaveProyectoPK=6-1"
	    val respuesta = JsonArrayRequest(Request.Method.GET, url, null,
					     Response.Listener {
						 response ->
						     Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
					     },
					     Response.ErrorListener {
						 error ->
						     Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
	    })
	    colaSolicitud.add(respuesta)
	}

    }

    private fun Int.dosDigitos() =
	if (this < 9) "0$this" else this.toString()

	// Obtiene la fecha actual 
    private fun obtFechaActual(): String {
	val calendario = Calendar.getInstance()
	val anio = calendario.get(Calendar.YEAR)
	val mes  = calendario.get(Calendar.MONTH).dosDigitos()
	val dia  = calendario.get(Calendar.DAY_OF_MONTH).dosDigitos()
	return "$dia/$mes/$anio"
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

    /**
     * Crea un adaptador a partir de una lista de textos
     * @return ArrayAdapter<String>
     */
    private fun crearAdaptador(contexto: Context, array: List<String>): ArrayAdapter<String> {
	val adaptador = ArrayAdapter(contexto, android.R.layout.simple_spinner_item, array)
	adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
	return adaptador
    }
    
    /**
     * Obtiene y llena los departamentos en el spinner
     * TODO: El valor de retorno no se usa :D
     */
    private fun obtDepartamentos(cola: RequestQueue, spinner: Spinner): MutableList<String> {
	val url = "http://webapp.inei.gob.pe:8080/sisconcode/ubigeo/buscarDepartamentosPorVersion.htm?llaveProyectoPK=6-1"
	val lista: MutableList<String> = mutableListOf()
	val respuesta = JsonArrayRequest(Request.Method.GET, url, null,
					 Response.Listener {
					     response ->
						 for (i in 0 until response.length()) {
						     val dep = response.getJSONObject(i)
						     lista.add((dep.get("descripcion") as String))
						 }
					     spinner.setAdapter(crearAdaptador(applicationContext, lista))
					 },
					 Response.ErrorListener {
					     error ->
	})
	cola.add(respuesta)
	return lista
    }

    /**
     * Obtiene y llena las provincias en el spinner
     * TODO: El valor de retorno no se usa :D
     */
    private fun obtProvincias(cola: RequestQueue, idDepartamento: String, spinner: Spinner): MutableList<String> {
	val url = "http://webapp.inei.gob.pe:8080/sisconcode/ubigeo/buscarProvinciasPorVersion.htm?llaveProyectoPK=6-1&departamentoId=" + idDepartamento
	val lista: MutableList<String> = mutableListOf()
	val respuesta = JsonArrayRequest(Request.Method.GET, url, null,
					 Response.Listener {
					     response ->
						 for (i in 0 until response.length()) {
						     val dep = response.getJSONObject(i)
						     lista.add((dep.get("descripcion") as String))
						 }
					     spinner.setAdapter(crearAdaptador(applicationContext, lista))
					 },
					 Response.ErrorListener {
					     error ->
	})
	cola.add(respuesta)
	return lista
    }

    /**
     * Obtiene y llena los distritos en el spinner
     * TODO: El valor de retorno no se usa :D
     */
    private fun obtDistritos(cola: RequestQueue, idDepartamento: String, idProvincia: String, spinner: Spinner): MutableList<String> {
	val url = "http://webapp.inei.gob.pe:8080/sisconcode/ubigeo/buscarDistritosPorVersion.htm?llaveProyectoPK=6-1&departamentoId=" + idDepartamento + "&provinciaId=" + idProvincia
	val lista: MutableList<String> = mutableListOf()
	val respuesta = JsonArrayRequest(Request.Method.GET, url, null,
					 Response.Listener {
					     response ->
						 for (i in 0 until response.length()) {
						     val dep = response.getJSONObject(i)
						     lista.add((dep.get("descripcion") as String))
						 }
					     spinner.setAdapter(crearAdaptador(applicationContext, lista))
					 },
					 Response.ErrorListener {
					     error ->
	})
	cola.add(respuesta)
	return lista
    }


}

