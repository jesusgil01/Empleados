package com.example.empleados

class arregloEmpleados {

    fun getEmpleados() : Array<Empleado> {

        //Dummies
        var gil : Empleado = Empleado("Jesús Gil", 1, 10000.00)
        var villa : Empleado = Empleado("Jesús Villalobos", 2, 11000.00)
        var beto : Empleado = Empleado("Alberto Ornelas", 3, 12000.00)
        var arody : Empleado = Empleado("Arody Monares", 4, 13000.00)
        var nani : Empleado = Empleado("Daniel Lacarra", 5, 14000.00)

        var empleados : Array<Empleado> = arrayOf(gil,villa,beto,arody,nani)

        return empleados
        
    }
}