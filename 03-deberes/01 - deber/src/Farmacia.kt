import java.io.*


class Farmacia {

    var listaMutable = mutableListOf<String>()
    //var listaMutable = arrayListOf<String>()

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

        /*listaMutable.forEach { valorIteracion: String ->
            println("Valor interacion: "+valorIteracion)
        }*/
        //print(listaMutable[1])
        //guardarFarmacia(listaMutable)
        //ArrayList<String>
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
        //parsearFarm(text)
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
        parsearFarm(arreglo)
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

    fun parsearFarm(arregloDeStrings: List<String>) :ArrayList<AtributosFar>{
        var farmacias: ArrayList<AtributosFar> = arrayListOf()
        var arreglo2: Array<String> = arrayOf()
        arregloDeStrings.forEach { valor ->
            //var arregloDatosEnString: Array<String> = arrayOf()
            arreglo2 = valor.split(",").toTypedArray()

            //println("Imprimir arreglo separado de comas")
            arreglo2.forEach { v -> println(v) }
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
        //listaMutable.add(farmacias.toString())
        print(farmacias[0])
        print("Ahora va la lista mutable")
        //print(listaMutable[1])
        return farmacias
    }

    fun buscarFarmacia(nombreABuscar: String = "todas"){
        val elementos: List<String> = lectura()
        var farmacias: ArrayList<AtributosFar> = arrayListOf()
        var arreglo2: Array<String> = arrayOf()
        if (nombreABuscar.equals("todas")){
            elementos.forEach { valor ->
                arreglo2 = valor.split(",").toTypedArray()

                //println("Imprimir arreglo separado de comas")
                arreglo2.forEach { v -> println(v) }
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