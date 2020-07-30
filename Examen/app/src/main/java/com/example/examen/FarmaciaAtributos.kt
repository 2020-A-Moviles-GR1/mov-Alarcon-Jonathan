package com.example.examen

class FarmaciaAtributos (
    val idFarmacia : Int?,
    var nombreFarmacia :String,
    var direccionFarmacia : String,
    var numeroTrabajadores : Int?,
    var compra : Float?,
    var atencion : Boolean?
) {

    override fun toString ():String{
        return "${idFarmacia} ${nombreFarmacia} ${direccionFarmacia} ${numeroTrabajadores} ${compra} ${atencion}"
    }
}