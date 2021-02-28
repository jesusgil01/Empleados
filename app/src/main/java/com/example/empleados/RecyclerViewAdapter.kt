package com.example.empleados

import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private var empleados : Array<Empleado>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cv_nombre : TextView = itemView.findViewById(R.id.cv_nombre)
        var cv_tipo_empleado : TextView = itemView.findViewById(R.id.cv_tipo_empleado)
        var cv_salario : TextView = itemView.findViewById(R.id.cv_salario)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cv_nombre.text = empleados[position].nombre
        holder.cv_tipo_empleado.text = "Tipo de empleado: " + empleados[position].tipoEmpleado.toString()
        holder.cv_salario.text = empleados[position].getSalario().toString()

        holder.itemView.setOnClickListener { View ->
            initInfoEmpleado(holder, empleados[position])
        }
    }

    override fun getItemCount(): Int {
        return empleados.size
    }

    private fun initInfoEmpleado(holder: RecyclerViewAdapter.ViewHolder, empleado: Empleado) {
        val view = View.inflate(holder.itemView.context, R.layout.modificar_informacion,null)
        view.findViewById<EditText>(R.id.mi_nombre_input).setText(empleado.nombre)
        view.findViewById<EditText>(R.id.mi_salario_input).setText(empleado.sueldo_base.toString())
       view.findViewById<RadioGroup>(R.id.mi_rg).check(selectedRBID(view, empleado.tipoEmpleado))


        val dialog = openWindow(holder, view)
        view.findViewById<Button>(R.id.mi_cancelar).setOnClickListener { v: View ->
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.mi_aceptar).setOnClickListener { v: View ->
            saveInfo(empleado,view)
            Toast.makeText(holder.itemView.context, "Información guardada", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            this.notifyDataSetChanged()
        }
    }

    private fun saveInfo(empleado: Empleado, view: View) {
        empleado.nombre = view.findViewById<EditText>(R.id.mi_nombre_input).text.toString()
        empleado.sueldo_base = view.findViewById<EditText>(R.id.mi_salario_input).text.toString().toDouble()
        empleado.tipoEmpleado =  selectedRBNumber(view)
    }

    private fun selectedRBNumber(view: View): Int {
        var tipo = 0
        var rg = view.findViewById<RadioGroup>(R.id.mi_rg)
        var rb_uno = view.findViewById<RadioButton>(R.id.mi_rb_uno)
        var rb_dos = view.findViewById<RadioButton>(R.id.mi_rb_dos)
        var rb_tres = view.findViewById<RadioButton>(R.id.mi_rb_tres)
        var rb_cuatro = view.findViewById<RadioButton>(R.id.mi_rb_cuatro)
        var rb_cinco = view.findViewById<RadioButton>(R.id.mi_rb_cinco)
        when (rg.checkedRadioButtonId.toString()){
            rb_uno.id.toString() -> tipo = 1
            rb_dos.id.toString() -> tipo = 2
            rb_tres.id.toString() -> tipo = 3
            rb_cuatro.id.toString() -> tipo = 4
            rb_cinco.id.toString() -> tipo = 5
        }
        return tipo
    }

    private fun selectedRBID(view: View, tipo : Int): Int {
        var id = 0
        var rg = view.findViewById<RadioGroup>(R.id.mi_rg)
        var rb_uno = view.findViewById<RadioButton>(R.id.mi_rb_uno)
        var rb_dos = view.findViewById<RadioButton>(R.id.mi_rb_dos)
        var rb_tres = view.findViewById<RadioButton>(R.id.mi_rb_tres)
        var rb_cuatro = view.findViewById<RadioButton>(R.id.mi_rb_cuatro)
        var rb_cinco = view.findViewById<RadioButton>(R.id.mi_rb_cinco)
        when (tipo){
            1 -> id = rb_uno.id
            2 -> id = rb_dos.id
            3 -> id = rb_tres.id
            4 -> id = rb_cuatro.id
            5 -> id = rb_cinco.id
        }
        return tipo
    }


    private fun openWindow(holder: RecyclerViewAdapter.ViewHolder, view: View?): AlertDialog {
        val builder = AlertDialog.Builder(holder.itemView.context)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    fun update(array: Array<Empleado>) {
        this.empleados = array
        this.notifyDataSetChanged()
    }

}