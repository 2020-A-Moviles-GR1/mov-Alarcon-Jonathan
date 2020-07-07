import java.io.*


class Farmacia {

    //var listaMutable = mutableListOf<String>()
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

    fun buscarFarmacia(nombreFarmacia: String = "todas"){
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

    }

}