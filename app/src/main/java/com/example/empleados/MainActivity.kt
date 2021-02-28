package com.example.empleados

import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var empleados :Array<Empleado> = arregloEmpleados().getEmpleados()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv_recyclerview = findViewById<RecyclerView>(R.id.rv_recyclerView)
        val mAdapter = RecyclerViewAdapter(empleados)

        rv_recyclerview.layoutManager =LinearLayoutManager(this)
        rv_recyclerview.adapter = mAdapter

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { view ->
            nuevoEmpleado(mAdapter)
        }

    }

    private fun nuevoEmpleado(adapter: RecyclerViewAdapter) {
        val view = View.inflate(this, R.layout.agregar_empleado,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        view.findViewById<Button>(R.id.ae_cancelar).setOnClickListener { v: View ->
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.ae_aceptar).setOnClickListener { v : View ->
            val nuevo_empleado = Empleado(
                view.findViewById<EditText>(R.id.ae_nombre_input).text.toString(),
                selectedRBNumber(view),
                view.findViewById<EditText>(R.id.ae_salario_input).text.toString().toDouble()
            )
            empleados += nuevo_empleado
            adapter.update(empleados)
            Toast.makeText(this, "Empleado agregado", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    private fun selectedRBNumber(view: View): Int {
        var tipo = 0
        var rg = view.findViewById<RadioGroup>(R.id.ae_rg)
        var rb_uno = view.findViewById<RadioButton>(R.id.ae_rb_uno)
        var rb_dos = view.findViewById<RadioButton>(R.id.ae_rb_dos)
        var rb_tres = view.findViewById<RadioButton>(R.id.ae_rb_tres)
        var rb_cuatro = view.findViewById<RadioButton>(R.id.ae_rb_cinco)
        var rb_cinco = view.findViewById<RadioButton>(R.id.ae_rb_cinco)
        when (rg.checkedRadioButtonId.toString()){
            rb_uno.id.toString() -> tipo = 1
            rb_dos.id.toString() -> tipo = 2
            rb_tres.id.toString() -> tipo = 3
            rb_cuatro.id.toString() -> tipo = 4
            rb_cinco.id.toString() -> tipo = 5
        }
        return tipo
    }


}