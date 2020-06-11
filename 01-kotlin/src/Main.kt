import java.util.*

fun main(args:Array<String>){
    print("Hola")
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
        12.20 -> println("Sueldo normal")
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

