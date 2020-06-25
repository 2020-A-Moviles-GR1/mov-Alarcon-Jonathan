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
    val arregloNoConstante: ArrayList<Int> = arrayListOf(15,31,32,23,25,30) // en este tipo de array si se puede modificar
    println(arregloConstante)
    println("Este es el arreglo Constante")
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
    println("Respuesta del filter")
    println(respuestaFilter)
    println(arregloNoConstante)


    //Clase 18/06/2020
    //Any -> or
    //All -> and
    // AND -> TRUE, Todo lo demas falso
    //  OR -> TODO es falso, lo demas es verdadero

    //1) devolver una expresion (TRUE OR FALSE)
    //2) devuelve un booleano

    //(15,31,32,23,25,30)

    val respuestaAny: Boolean = arregloNoConstante.any{
        iterador: Int ->
        return@any iterador < 20
    }

    println(respuestaAny)

    val respuestaAll = arregloNoConstante.all{
        iterador: Int ->
        return@all iterador > 18
    }
    println(respuestaAll)

    // Reduce (devuelve numeros)
    // 1) devuelve el acumulado
    // 2) Tenemos que decirle en que valor empieza
    // 3) el acumulador siempre empieza en 0
    // 4) podemos trabajar con cualquier operacion 
    //Este operador nos permite hacer cualquier tipo de operacion
    //Ejemplo: Promedio
    //iteracion son nuestros valores
    //acumulador siempre empieza en 0
    /*val respuestaReduce = arregloNoConstante
            .reduce{ acumulador, iteracion ->
                return@reduce
            }
    */

    val respuestaReduce = arregloNoConstante
            .reduce{ acumulador, iteracion ->
                return@reduce acumulador + iteracion
            }
    println("Esta es la respuesta del operador reduce")
    println(respuestaReduce)
    println(arregloNoConstante)
    
    // operador para que el acumulador no empiece desde cero
    val respuestaFold: Int = arregloNoConstante
            .fold (
                    100,
                    { acumulador, iteracion ->
                        println(acumulador)
                        return@fold acumulador - iteracion
                    }
            )               
    println("Esta es la respuesta del operador fold")
    println(respuestaFold)

    //arregloNoConstante.foldRight


    // Reducir el daño en 20%
    // menores a 18 ataques no nos hace daño
    println("Aqui comienza la vida actual")
    val vidaActual: Double = arregloNoConstante
            .map { it * 0.8 }
            .filter { it > 20 }
            .fold(
                    100.00,
                    { acumulador, dato ->
                        println(acumulador)
                        return@fold acumulador - dato }
            )
    println("Esta es la respuesta de la vida actual")
    println(vidaActual)
    
    val nuevoNumeroUno = sumarDosNumerosDos(1,1)
    val nuevoNumeroDos = sumarDosNumerosDos(null,1)
    val nuevoNumeroTres = sumarDosNumerosDos(1,null)
    val nuevoNumeroCuatro = sumarDosNumerosDos(null,null)

    println(sumarDosNumerosDos.arregloNumeros)
    sumarDosNumerosDos.agregarNumero(254)
    println(sumarDosNumerosDos.arregloNumeros)
    sumarDosNumerosDos.eliminarNumero(1)
    println(sumarDosNumerosDos.arregloNumeros)



    //errores
    // Elvis Operator 
    val nombre: String? = null
    nombre = "Jonathan"
    println(nombre.length)
    //if (nombre != null){
      //  println(nombre.length)
    //}

    //si esque existe el nombre devuelves la longitud
    
    
    
    

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

//CLASES ABSTRACTAS
//podemos crear clases que se deriven de estas clases
abstract class NumerosJava{  // val nuevosNumeros = Numeros(1,2)
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(uno:Int, dos:Int){
        numeroUno = uno
        numeroDos = dos
    }
}
//la clase abstracta se puede heredar
abstract class Numeros( // val nuevosNumeros = Numeros(1,2)
        protected var numeroUno:Int,
        protected var numeroDos:Int
){

}

class Suma(
        uno: Int,
        dos: Int
) : Numeros(uno, dos) {
    fun sumar():Int{
        // this.uno
        return this.numeroUno + this.numeroDos
    }
}

class SumaDos(
        public var uno: Int,
        public var dos: Int
) : Numeros(uno, dos) {
    fun sumar():Int{
        this.uno
        this.dos
        return this.numeroUno + this.numeroDos
    }
}

class sumarDosNumerosDos(
       uno: Int,
       dos: Int
) : Numeros(uno, dos){
    //pedazo de codigo especial de kotlin

    init {
        println("Hola INIT") //siempre se ejecuta este bloque de codigo
    }

    constructor(uno: Int?, dos: Int): this(if (uno == null) 0 else uno, dos){
        println("Hola 1")
    }
    constructor(uno: Int, dos: Int?): this( uno, if (dos == null) 0 else dos){
        println("Hola 2")
    }
    constructor(uno: Int?, dos: Int?): this(
            if (uno == null) 0 else uno,
            if (dos == null) 0 else dos
        )
     {
        println("Hola 3")
     }



    //propiedad estatica o metodos estaticos
    companion object{
        val arregloNumeros = arrayListOf(1,2,3,4)

        fun agregarNumero(nuevoNumero:Int){
            this.arregloNumeros.add(nuevoNumero)
        }

        fun eliminarNumero(posicionNumero:Int){
            this.arregloNumeros.removeAt(posicionNumero)
        }
    }



}
