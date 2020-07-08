import kotlin.system.exitProcess

fun main(args:Array<String>) {

    fun menu(){
        println("¿Qué desea gestionar?")
        println("1. Farmacia")
        println("2. Medicamento")
        println("3. Salir")
        print("Elija una opción -> ")
        var eleccion = readLine()?.toInt()
        when (eleccion){
            1 -> {
                println("BIENVENIDO, PUEDE CREAR UNA FARMACIA");
                Farmacia().menuFarmacia()
            }
            2 -> {
                println("BIENVENIDO, PUEDE CREAR UN MEDICAMENTO");
                Medicamento().menuMedicamento()
            }
            3 -> {
                exitProcess(2)
            }
        }
    }
    menu()

}




/*class Farmacia(){
    fun crearFarmacia(){

        print("Ingrese el nombre de la farmacia: ")
        val nombreFarmacia = readLine().toString()
        //println(nombreFarmacia)
        print("Ingrese la dirección de la farmacia $nombreFarmacia: ")
        val direccionFarmacia = readLine().toString()
        print("Ingrese el numero de telefono de la farmacia $nombreFarmacia: ")
        val numeroFarmacia = readLine()?.toInt()
        print("¿Cuánto es la mínima cantidad de compra? -> ")
        val compraFarmacia = readLine()?.toFloat()
        println("\nATENCIÓN -> En la siguiente pregunta ingrese Si o No")
        print("¿La farmacia $nombreFarmacia atiende las 24 horas? ->: ")
        val horarioFarmacia = readLine().toString()

    }
    fun guardarFarmacia(){

    }

}*/