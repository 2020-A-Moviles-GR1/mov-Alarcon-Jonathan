import java.io.File
import java.io.FileOutputStream
import kotlin.system.exitProcess

class Medicamento {

    fun menuMedicamento(){
        println("***************************BIENVENIDO AL MENÚ DE GESTIÓN MEDICAMENTO****************")
        println("1. Crear un Medicamento")
        println("2. Buscar un Medicamento")
        println("3. Actualizar un Medicamento")
        println("4. Eliminar un Medicamento")
        println("5. Volver al menú principal")
        print("Elija una opción -> ")
        var eleccion = readLine()?.toInt()
        when (eleccion){
            1 -> {
                println("BIENVENIDO, PUEDE CREAR UN MEDICAMENTO");
                crearMedicamento()
            }
            2 -> {
                print("Ingrese el nombre del medicamento que desea buscar o ingrese todos para mostrar todos los medicamentos: ")
                val opcion = readLine().toString()
                buscarMedicamento(opcion)
            }
            3 -> {
                print("Ingrese el nombre del medicamento que desea actualizar: ")
                val opcion = readLine().toString()
                actualizarMedicamento(opcion)
            }
            4 -> {
                print("Ingrese el nombre del medicamento que desea eliminar: ")
                val opcion = readLine().toString()
                eliminarMedicamento(opcion)
            }
            5 -> {
                exitProcess(1)
            }
        }
        menuMedicamento()
    } // Fin Menu Medicamento

    fun crearMedicamento(nombreFarmOPC : String = "Nada"){//nombre_farm,nombre_medic,precio_medic,fecha_medic,unidades_medic,prevencion
        print("Ingrese el ID del Medicamento: ")
        val idMedicamento = readLine()?.toInt()
        if (nombreFarmOPC == "Nada"){
            print("Ingrese el nombre de la farmacia: ")
            val nombreFarmacia = readLine().toString()
            print("Ingrese el nombre del Medicamento: ")
            val nombreMedicamento = readLine().toString()
            print("¿Cuál es el costo del Medicamento? -> ")
            val precioMedicamento = readLine()?.toFloat()
            print("Ingrese la fecha de fabricación del medicamento: ")
            val fechaMedicamento = readLine().toString()
            print("Ingrese la cantidad de medicamentos para la farmacia $nombreFarmacia: ")
            val unidadesMedicamento = readLine()?.toInt()
            println("\nATENCIÓN -> En la siguiente pregunta ingrese Si o No")
            print("¿El medicamento $nombreMedicamento es apto para niños? ->: ")
            val prevencion = readLine()?.toBoolean()
            //GUARDAR EN EL ARCHIVO
            val datos = AtributosMed(idMedicamento,nombreFarmacia,nombreMedicamento,precioMedicamento,fechaMedicamento,unidadesMedicamento,prevencion)
            val archivo: File = File("archivos//medicamento.txt")
            val ingreso = FileOutputStream(archivo,true)
            ingreso.bufferedWriter().use { out ->
                out.write("$datos")
            } // Fin INGRESO
        } else {
            println(nombreFarmOPC)
            print("Ingrese el nombre del Medicamento: ")
            val nombreMedicamento = readLine().toString()
            print("¿Cuál es el costo del Medicamento? -> ")
            val precioMedicamento = readLine()?.toFloat()
            print("Ingrese la fecha de fabricación del medicamento: ")
            val fechaMedicamento = readLine().toString()
            print("Ingrese la cantidad de medicamentos para la farmacia $nombreFarmOPC: ")
            val unidadesMedicamento = readLine()?.toInt()
            println("\nATENCIÓN -> En la siguiente pregunta ingrese Si o No")
            print("¿El medicamento $nombreMedicamento es apto para niños? ->: ")
            val prevencion = readLine()?.toBoolean()
            //GUARDAR EN EL ARCHIVO
            val datos = AtributosMed(idMedicamento,nombreFarmOPC,nombreMedicamento,precioMedicamento,fechaMedicamento,unidadesMedicamento,prevencion)
            val archivo: File = File("archivos//medicamento.txt")
            val ingreso = FileOutputStream(archivo,true)
            ingreso.bufferedWriter().use { out ->
                out.write("$datos")
            } // Fin INGRESO
        }

    } // Fin funcion crear Medicamento

    // Esta función permite leer desde el archivo
    fun lectura():List<String>{
        val archivo = File("archivos//medicamento.txt")
        val bufferedReader = archivo.bufferedReader()
        val text:List<String> = bufferedReader.readLines()
        var output = ""
        text.forEachIndexed { index, it -> output = it + (if(index < text.size-2) "\n" else "") }
        obtenerMedicamento(text)
        return text
    }

    //función para guardar en un array
    fun obtenerMedicamento(elementos: List<String>) :ArrayList<AtributosMed>{
        var medicamento: ArrayList<AtributosMed> = arrayListOf()
        var arregloParse: Array<String> = arrayOf()
        elementos.forEach { valor ->
            arregloParse = valor.split(",").toTypedArray()
            medicamento.add(AtributosMed(
                    arregloParse[0].toInt(),
                    arregloParse[1],
                    arregloParse[2],
                    arregloParse[3].toFloat(),
                    arregloParse[4],
                    arregloParse[5].toInt(),
                    arregloParse[6].toBoolean()
            )
            )
        }

        return medicamento
    }

    fun buscarMedicamento(nombreABuscar: String = "todas"){
        val elementos: List<String> = lectura()
        var medicamento: ArrayList<AtributosMed> = obtenerMedicamento(elementos)
        var arreglo2: Array<String> = arrayOf()
        if (nombreABuscar.equals("todas")){
            elementos.forEach { valor ->
                arreglo2 = valor.split(",").toTypedArray()
                medicamento.add(AtributosMed(
                        arreglo2[0].toInt(),
                        arreglo2[1],
                        arreglo2[2],
                        arreglo2[3].toFloat(),
                        arreglo2[4],
                        arreglo2[5].toInt(),
                        arreglo2[6].toBoolean()
                )
                )
            }
            print(medicamento)
        }else if(!nombreABuscar.equals("todas")) {
            elementos.forEach { valor ->
                arreglo2 = valor.split(",").toTypedArray()
                if (arreglo2[1].equals(nombreABuscar)) {
                    println(valor)
                }
            }
        }else {
            print("El medicamento no existe")
        }

    } //Fin Buscar Medicamento

    // funcion actualizar Medicamento

    fun actualizarMedicamento(nombreABuscar: String){
        val elementos: List<String> = lectura()
        val medicamento: ArrayList<AtributosMed> = obtenerMedicamento(elementos)
        medicamento.forEachIndexed { index, atributosMed ->
            if (medicamento[index].nombreMedicamento.equals(nombreABuscar)){
                println("******************************PUNTO DE CONTROL*********************")

                println("1. Precio")
                println("2. Fecha")
                println("3. Unidades")
                println("4. Apto para niños")
                println("Ingrese el número del campo que desea actualizar: ")
                val opcion = readLine()?.toInt()
                when (opcion){
                    1 -> {
                        print("Ingrese el nuevo precio del medicamento: ")
                        //val direccionFarmacia = readLine().toString()
                        medicamento[index].precioMedicamento = readLine()?.toFloat()
                    }
                    2 -> {
                        print("Ingrese el numero de trabajadores de la farmacia: ")
                        //val numeroFarmacia = readLine()?.toInt()
                        medicamento[index].fechaMedicamento = readLine().toString()
                    }
                    3 -> {
                        print("¿Cuánto es la mínima cantidad de compra? -> ")
                        //val compraFarmacia = readLine()?.toFloat()
                        medicamento[index].unidadesMedicamento = readLine()?.toInt()
                    }
                } //Fin When
            } //fin IF
        }// fin forEachIndexed farmacia
        guardarActualizacion(medicamento)

    } //fin funcion


    //funcion guardar nueva actualizacion
    fun guardarActualizacion(nuevaLista: ArrayList<AtributosMed>){
        val archivo: File = File("archivos//medicamento.txt")
        val ingreso = FileOutputStream(archivo,false)
        ingreso.bufferedWriter().use { out ->
            out.write("")
        } // Fin Ingreso
        nuevaLista.forEachIndexed { index, atributosMed ->
            val id = nuevaLista[index].idMed
            val nombre = nuevaLista[index].nombreFarmacia
            val nombreMed = nuevaLista[index].nombreMedicamento
            val precio = nuevaLista[index].precioMedicamento
            val fecha = nuevaLista[index].fechaMedicamento
            val unidades = nuevaLista[index].unidadesMedicamento
            val prevencion = nuevaLista[index].prevencion
            val datos = AtributosMed(id,nombre,nombreMed,precio,fecha,unidades,prevencion)
            val archivo: File = File("archivos//medicamento.txt")
            val ingreso = FileOutputStream(archivo,true)
            ingreso.bufferedWriter().use { out ->
                out.write("$datos")
            } // Fin Ingreso
        } //Fin ForEachIndex
    } //Fin guardar actualizacion

    //funcion eliminar medicamento
    fun eliminarMedicamento(nombreEliminar: String){
        var indice: Int = 0
        val elementos: List<String> = lectura()
        val medicamento: ArrayList<AtributosMed> = obtenerMedicamento(elementos)
        medicamento.forEachIndexed { index, atributosFar ->
            if (medicamento[index].nombreMedicamento.equals(nombreEliminar)){
                //print(index)
                indice = index
            } // fin IF
        } //Fin ForEachIndexed
        medicamento.removeAt(indice)
        guardarActualizacion(medicamento)
        println("LOS CAMBIOS SE GUARDARON CON ÉXITO")
    } //Fin funcion eliminar


} //fin CLASE MEDICAMENTO