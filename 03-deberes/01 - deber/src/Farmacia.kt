import java.io.*
import kotlin.system.exitProcess


class Farmacia {

    fun menuFarmacia(){
        println("***************************BIENVENIDO AL MENÚ DE GESTIÓN FARMACIA****************")
        println("1. Crear una Farmacia")
        println("2. Buscar una Farmacia")
        println("3. Actualizar una Farmacia")
        println("4. Eliminar una farmacia")
        println("5. Volver al menú principal")
        print("Elija una opción -> ")
        var eleccion = readLine()?.toInt()
        when (eleccion){
            1 -> {
                println("BIENVENIDO, PUEDE CREAR UNA FARMACIA");
                crearFarmacia()
            }
            2 -> {
                print("Ingrese el nombre de la farmacia que desea buscar o ingrese todas para mostrar todas las farmacias: ")
                val opcion = readLine().toString()
                buscarFarmacia(opcion)
            }
            3 -> {
                print("Ingrese el nombre de la farmacia que desea actualizar: ")
                val opcion = readLine().toString()
                actualizarFarmacia(opcion)
            }
            4 -> {
                print("Ingrese el nombre de la farmacia que desea eliminar: ")
                val opcion = readLine().toString()
                eliminarFarmacia(opcion)
            }
            5 -> {
                exitProcess(1)
            }
        }
        menuFarmacia()
    }


    fun crearFarmacia(){
        print("Ingrese el ID de la farmacia: ")
        val idFarmacia = readLine()?.toInt()
        print("Ingrese el nombre de la farmacia: ")
        val nombreFarmacia = readLine().toString()
        print("Ingrese la dirección de la farmacia $nombreFarmacia: ")
        val direccionFarmacia = readLine().toString()
        print("Ingrese el numero de trabajadores de la farmacia $nombreFarmacia: ")
        val numeroFarmacia = readLine()?.toInt()
        print("¿Cuánto es la mínima cantidad de compra? -> ")
        val compraFarmacia = readLine()?.toFloat()
        println("\nATENCIÓN -> En la siguiente pregunta ingrese Si o No")
        print("¿La farmacia $nombreFarmacia atiende las 24 horas? ->: ")
        val horarioFarmacia = readLine()?.toBoolean()
        //GUARDAR EN EL ARCHIVO
        val datos = AtributosFar(idFarmacia,nombreFarmacia,direccionFarmacia,numeroFarmacia,compraFarmacia,horarioFarmacia)
        val archivo: File = File("archivos//farmacia.txt")
        val ingreso = FileOutputStream(archivo,true)
        ingreso.bufferedWriter().use { out ->
            out.write("$datos")
        }
        println("****************** ¿DESEA CREAR UN MEDICAMENTO PARA ESTA FARMACIA? ************************")
        println("Digite Si - No : ")
        val crearMed = readLine().toString()
        if (crearMed == "Si"){
            Medicamento().crearMedicamento(nombreFarmacia)
        } else{
            println("La Farmacia fue creada con éxito")
        }

    }

    fun guardarFarmacia( farmacia: MutableList<String> ){

        val archivo: File = File("archivos//farmacia.txt")
        val ingreso = FileOutputStream(archivo,true)
        //ingreso.bufferedWriter().newLine();
        //ingreso.bufferedWriter().write("Que deseas Jonathan?");
        //ingreso.close();
        //FileOutputStream(archivo,true).bufferedWriter().newLine()
        //FileOutputStream(archivo,true).bufferedWriter().write("Que deseas Jonathan?")
        //FileOutputStream(archivo,true).bufferedWriter().close()
        //use { out -> "Hola mundo, soy Jonathan".toByteArray() }
        //ESTA PARTE SI VALE
        /*ingreso.bufferedWriter().use { out ->
            farmacia.forEach { valorIteracion : String -> out.write("$valorIteracion[0], $valorIteracion[1], $valorIteracion[2], $valorIteracion[3], $valorIteracion[4], $valorIteracion[5] \n") }
        }*/
        //val =

            //
        //println("Se pudo escribir en el archivo")
    }

    fun lectura():List<String>{


        val archivo = File("archivos//farmacia.txt")
        val bufferedReader = archivo.bufferedReader()
        val text:List<String> = bufferedReader.readLines()
        var output = ""
        text.forEachIndexed { index, it -> output = it + (if(index < text.size-2) "\n" else "") }
        obtenerFarmacias(text)
        //buscarFarmacia(text)
        return text
    }

    fun prueba(): List<String>{
        val arreglo: ArrayList<String> = arrayListOf()
        var numeroLineas = 0
        val archivo = File("archivos//farmacia.txt")
        val bufferedReader = archivo.bufferedReader()
        while (bufferedReader.readLine()!= null){
            val lectura = bufferedReader.readText()
            //print(lectura)
            arreglo.add(lectura)
            numeroLineas += 1
        }
        //print(numeroLineas)
        //print(arreglo)
        //parsearFarm(arreglo)
        return arreglo
    }



    /*fun leer():ArrayList<String> {
        val archivo: File = File("archivos//farmacia.txt")
        val buffReader: BufferedReader = archivo.bufferedReader()
        val inp: String = buffReader.use { it.readText() }
        var arregloDeStrings:ArrayList<String> = inp.split("\n").toTypedArray().toCollection(ArrayList());
        println("arreglooooo 1 : " + arregloDeStrings.size.toString())
        arregloDeStrings.removeAt(arregloDeStrings.size - 1)
        println("arreglooooo 2 : " + arregloDeStrings.size.toString())
        //println(inp);
        //println("lista de strings: \n")

        return  arregloDeStrings;
    }*/

    fun obtenerFarmacias(elementos: List<String>) :ArrayList<AtributosFar>{
        //val elementos: List<String> = lectura()
        var farmacias: ArrayList<AtributosFar> = arrayListOf()
        var arregloParse: Array<String> = arrayOf()
        elementos.forEach { valor ->
            arregloParse = valor.split(",").toTypedArray()

            //println("Imprimir arreglo separado de comas")
            //arregloParse.forEach { v -> println(v) }
            farmacias.add(AtributosFar(
                    arregloParse[0].toInt(),
                    arregloParse[1],
                    arregloParse[2],
                    arregloParse[3].toInt(),
                    arregloParse[4].toFloat(),
                    arregloParse[5].toBoolean()
                    )
            )
        }
        //listaMutable.add(farmacias.toString())
        //print(farmacias[0])
        //print("Ahora va la lista mutable")
        //print(listaMutable[1])
        //print(farmacias[0].nombreFarmacia)
        //println("\n")
        //println(farmacias[1].compra)
        /*farmacias.forEachIndexed { index, atributosFar ->
            if (farmacias[index].nombreFarmacia.equals("medical2")){
                println("***************************************************")
                println(farmacias[index].compra)
            }
        }*/

        return farmacias
    }

    fun buscarFarmacia(nombreABuscar: String = "todas"){
        val elementos: List<String> = lectura()
        var farmacias: ArrayList<AtributosFar> = obtenerFarmacias(elementos)
        var arreglo2: Array<String> = arrayOf()
        if (nombreABuscar.equals("todas")){
            elementos.forEach { valor ->
                arreglo2 = valor.split(",").toTypedArray()
                //println("Imprimir arreglo separado de comas")
                //arreglo2.forEach { v -> println(v) }
                farmacias.add(AtributosFar(
                        arreglo2[0].toInt(),
                        arreglo2[1],
                        arreglo2[2],
                        arreglo2[3].toInt(),
                        arreglo2[4].toFloat(),
                        arreglo2[5].toBoolean()
                        )
                )
            }
            print(farmacias)
        }else if(!nombreABuscar.equals("todas")) {
            elementos.forEach { valor ->
                arreglo2 = valor.split(",").toTypedArray()
                if (arreglo2[1].equals(nombreABuscar)) {
                    println(valor)
                }
            }
        }else {
            print("La farmacia no existe")
        }

    }

  /*  farmacias.forEachIndexed { index, atributosFar ->
        if (farmacias[index].nombreFarmacia.equals("medical2")){
            println("***************************************************")
            println(farmacias[index].compra)
        }
    }*/

    fun actualizarFarmacia(nombreABuscar: String){
        val elementos: List<String> = lectura()
        val farmacias: ArrayList<AtributosFar> = obtenerFarmacias(elementos)
        //var arreglo2: Array<String> = arrayOf()
        farmacias.forEachIndexed { index, atributosFar ->
            if (farmacias[index].nombreFarmacia.equals(nombreABuscar)){
                println("******************************PUNTO DE CONTROL*********************")
                //println(farmacias[index].compra)
                //println(farmacias[index].direccionFarmacia)
                println("1. Direccion")
                println("2. Cantidad de empleados")
                println("3. Precio mínimo")
                println("4. Horario de atención")
                println("Ingrese el número del campo que desea actualizar: ")
                val opcion = readLine()?.toInt()
                when (opcion){
                    1 -> {
                        print("Ingrese la dirección de la farmacia: ")
                        //val direccionFarmacia = readLine().toString()
                        farmacias[index].direccionFarmacia = readLine().toString()
                    }
                    2 -> {
                        print("Ingrese el numero de trabajadores de la farmacia: ")
                        //val numeroFarmacia = readLine()?.toInt()
                        farmacias[index].numeroTrabajadores = readLine()?.toInt()
                    }
                    3 -> {
                        print("¿Cuánto es la mínima cantidad de compra? -> ")
                        //val compraFarmacia = readLine()?.toFloat()
                        farmacias[index].compra = readLine()?.toFloat()
                    }
                } //Fin When
            } //fin IF
        }// fin forEachIndexed farmacia
        guardarActualizacion(farmacias)
        //print(farmacias)
    } //fin funcion

    fun guardarActualizacion(nuevaLista: ArrayList<AtributosFar>){
        //val listaFar: ArrayList<AtributosFar> = actualizarFarmacia()
        val archivo: File = File("archivos//farmacia.txt")
        val ingreso = FileOutputStream(archivo,false)
        ingreso.bufferedWriter().use { out ->
            out.write("")
        } // Fin Ingreso
        nuevaLista.forEachIndexed { index, atributosFar ->
            val id = nuevaLista[index].idFarmacia
            val nombre = nuevaLista[index].nombreFarmacia
            val direccion = nuevaLista[index].direccionFarmacia
            val trabajadores = nuevaLista[index].numeroTrabajadores
            val compra = nuevaLista[index].compra
            val atencion = nuevaLista[index].atencion
            val datos = AtributosFar(id,nombre,direccion,trabajadores,compra,atencion)
            val archivo: File = File("archivos//farmacia.txt")
            val ingreso = FileOutputStream(archivo,true)
            ingreso.bufferedWriter().use { out ->
                out.write("$datos")
            } // Fin Ingreso
        } //Fin ForEachIndex
    } //Fin guardar actualizacion

    fun eliminarFarmacia(nombreEliminar: String){
        var indice: Int = 0
        val elementos: List<String> = lectura()
        val farmacias: ArrayList<AtributosFar> = obtenerFarmacias(elementos)
        farmacias.forEachIndexed { index, atributosFar ->
            if (farmacias[index].nombreFarmacia.equals(nombreEliminar)){
                //print(index)
                indice = index
            } // fin IF
        } //Fin ForEachIndexed
        farmacias.removeAt(indice)
        guardarActualizacion(farmacias)
        println("LOS CAMBIOS SE GUARDARON CON ÉXITO")
    } //Fin funcion eliminar



   /* println("2. Direccion")
    println("3. Cantidad de empleados")
    println("4. Precio mínimo")
    println("5. Horario de atención")
    println("Ingrese el número del campo que desea actualizar: ")
    val opcion = readLine()?.toInt()
    when (opcion){
        1 -> {
            println("Ingrese el numero nombre de la farmacia: ")
            val nombreFarmacia = readLine().toString()
        }
        2 -> {
            print("Ingrese la dirección de la farmacia: ")
            val direccionFarmacia = readLine().toString()
        }
        3 -> {
            print("Ingrese el numero de trabajadores de la farmacia: ")
            val numeroFarmacia = readLine()?.toInt()
        }
        4 -> {
            print("¿Cuánto es la mínima cantidad de compra? -> ")
            val compraFarmacia = readLine()?.toFloat()
        }
    } //fin when

    */


    // Esta funcion no va ******************************************************************
    /*fun buscarFarmacia(nombreFarmacia: String = "todas"){
        val arregloMuteable = mutableListOf<String>()
        val archivo: File = File("archivos//farmacia.txt")
        val ingreso = FileInputStream(archivo)
        val datos = archivo.bufferedReader()
        when (nombreFarmacia){
            "todas" -> {
                datos.useLines { itera -> itera.forEach { arregloMuteable.add(it) } }
                var contador = 0
                arregloMuteable.forEach { valorIteracion: String ->
                    contador = contador + 1
                    println("Farmacia $contador: "+ valorIteracion)
                } //fin del foreach
            } //fin de "todas"
            else ->{
                datos.useLines { itera -> itera.forEach { arregloMuteable.add(it) } }
                arregloMuteable.forEach{ iteracion: ArrayList ->
                    if (iteracion[1] == nombreFarmacia) {
                        val respuesta = iteracion
                        print(respuesta)
                    }else{
                        print("No existe la farmacia")
                    }

                }
            }// fin else
        } // fin when

        //print(arregloMuteable)
        //ingreso.bufferedWriter().use { out ->
            //out.write("$datos")
        //}

    }*/
}