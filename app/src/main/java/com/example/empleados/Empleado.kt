package com.example.empleados

class Empleado {

    var nombre : String = ""
    var tipoEmpleado = 0
    var sueldo_base = 0.00

    constructor(nombre : String, tipoEmpleado: Int, salario : Double) {
        this.nombre = nombre
        this.tipoEmpleado = tipoEmpleado
        this.sueldo_base = salario
    }

    fun getSalario() : Float{
        var salario = sueldo_base
        when(tipoEmpleado){
            1 -> salario *= 1.05
            2 -> salario *= 1.07
            3 -> salario *= 1.09
            4 -> salario *= 1.12
            5 -> salario *= 1.15
        }
        return salario.toFloat()
    }

}