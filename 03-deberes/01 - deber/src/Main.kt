
fun main(args:Array<String>) {
    //println("Hola esto es una prueba ")
    //var numeroPrueba = 5
    //println(numeroPrueba)
    //numeroPrueba = 10
    //println(numeroPrueba)
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
                Farmacia().crearFarmacia()
            }
            2 -> {
                println("Ingrese el nombre de la farmacia que desea buscar o ingrese todas para mostrar todas las farmacias")
                val opcion = readLine().toString()
                Farmacia().buscarFarmacia(opcion)
            }
        }

    }
    menu()
    //Farmacia().crearFarmacia()
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