import java.util.*
import kotlin.collections.ArrayList

fun main(args:Array<String>){
    println("Hola esto es una prueba ")
    //Mutables
    var edadProfesor = 31 //No especificamos el tipo de dato
                          // ; No especificamos el punto y coma
    // Duck Typing
    //var edadCachorro: X -> necesitamos el tipo de datos
    var edadCachorro:Int
    edadCachorro = 5
    edadCachorro = 8
    edadCachorro = 11
    edadCachorro = 32

    //Inmutables
    val numeroCuenta = 123456
    //numeroCuenta = 154621 No podemos cambiar

    //Tipos de variables
    val nombreEstudiante: String = "Jonathan Alarcon"
    val sueldo = 12.20
    val letras: Char = 'a'
    val fechaNacimiento = Date()

    //
    when(sueldo){
        12.20 -> println("Sueldo normal, comprobado")
        -12.20 -> println("Sueldo normal")
        else -> println("No se reconoce el sueldo")
    }

    val esSueldoMayorAlEstablecido = if(sueldo == 12.20) true else false
    // EXPRESION ? X : Y

    //parametros nombrado
    //calcularSueldo(29.00)
    calcularSueldo(1000.00, 14.00)
    //named parameters
    calcularSueldo(sueldo = 800.00, tasa = 16.00)

    calcularSueldo1(1250.00, 18.00, null)
    calcularSueldo1(1250.00, 18.00)

    val arregloConstante: Array<Int> = arrayOf(1,2,3) //array para datos que no van a modificarse (no eliminar, no aumentar)
    val arregloNoConstante: ArrayList<Int> = arrayListOf(15,31,32) // en este tipo de array si se puede modificar
    print(arregloNoConstante)
    arregloNoConstante.add(50)
    print(arregloNoConstante)
    //arregloNoConstante.remove(30)
    arregloNoConstante.remove(30) //el element no tenemos que escribir nosotros
    print(arregloNoConstante)

    //val arregloDou: Array<Double> = arrayOf(45.36,27.8)
    //val arregloLetras: Array<String> = arrayOf("Marco", "Luis", "Pedro")
    //println(arregloDou)
    //println(arregloLetras)

    //para imprimir un arreglo
    arregloNoConstante.forEach {
        println("Valor de la iteración " + it)
    }
    //segunda forma para imprimir un arreglo
    arregloNoConstante
            .forEach { valorIteracion: Int ->
            println("Valor iteracion: " + valorIteracion)
        }

    arregloNoConstante
            .forEachIndexed { index:Int, it:Int ->
                println("Valor de la iteracion " + it)
            }
    //OPERADORES  -> TENEMOS EN TODOS LOS LENGUAJES

    //Map
    //Mutar o modificar el arreglo
    val respuestaMap = arregloNoConstante
            .map { iterador: Int ->
                iterador * -1
            }
    println(respuestaMap)
    println(arregloNoConstante)

    val respuestaMapDos: List<Int> = arregloNoConstante
            .map { iterador: Int ->
                val nuevoValor = iterador * -1
                val otroValor = nuevoValor * 2
                return@map otroValor
            }
    println(respuestaMap)
    println(respuestaMapDos)
    println(arregloNoConstante)

    val respuestaFilter = arregloNoConstante
            .filter {
                iteracion: Int ->
                val esMayorA23 = iteracion > 23
                return@filter esMayorA23
            }
    println(respuestaFilter)
    println(arregloNoConstante)


}

fun calcularSueldo( sueldo: Double, tasa: Double = 12.00): Int {
    return 10
}

fun calcularSueldo1( sueldo: Double, //requeridos
                     tasa: Double = 12.00, //por defecto
                     calculoEspecial:Int? = null // Pueden ser nulos
): Double {

    if (calculoEspecial != null){
        return sueldo * tasa * calculoEspecial
    } else {
        return sueldo * tasa
    }
}

fun imprimirMensaje(){ //unit = void
    println("Funcion")
}


