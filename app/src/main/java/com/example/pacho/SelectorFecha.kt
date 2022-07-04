package com.example.pacho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Dialog
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Fragment
import android.app.DialogFragment

import java.util.Calendar

import android.widget.DatePicker

class SelectorFecha : DialogFragment() {

    private var escuchador: DatePickerDialog.OnDateSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
	val calendario = Calendar.getInstance()
	val anio = calendario.get(Calendar.YEAR)
	val mes  = calendario.get(Calendar.MONTH)
	val dia  = calendario.get(Calendar.DAY_OF_MONTH)

	val rfecha = DatePickerDialog(activity, escuchador, anio, mes, dia)
	
	calendario.set(Calendar.YEAR, anio - 150)
	rfecha.datePicker.minDate = calendario.timeInMillis
	calendario.set(Calendar.YEAR, anio)
	rfecha.datePicker.maxDate = calendario.timeInMillis

	return rfecha
    }

    companion object {
	fun newInstance(escuchador: DatePickerDialog.OnDateSetListener): SelectorFecha {
	    val selector = SelectorFecha()
	    selector.escuchador = escuchador
	    return selector
	}
    }
}



