package com.example.examen

class MedicamentoAtributos (
    val idMed : Int?,
    var nombreFarmacia :String,
    var nombreMedicamento : String,
    var precioMedicamento : Float?,
    var fechaMedicamento: String,
    var unidadesMedicamento : Int?,
    var prevencion : Boolean?
) {
    override fun toString ():String{
        return "${idMed} ${nombreFarmacia} ${nombreMedicamento} ${precioMedicamento} ${fechaMedicamento} ${unidadesMedicamento} ${prevencion}"
    }

}