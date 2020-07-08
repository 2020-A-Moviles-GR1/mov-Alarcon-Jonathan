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
