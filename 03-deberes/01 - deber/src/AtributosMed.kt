import java.util.*

class AtributosMed (
        val idMed : Int?,
        var nombreFarmacia :String,
        var nombreMedicamento : String,
        var precioMedicamento : Float?,
        var fechaMedicamento: String,
        var unidadesMedicamento : Int?,
        val prevencion : Boolean?
) {
    override fun toString ():String{
        return "${idMed},${nombreFarmacia},${nombreMedicamento},${precioMedicamento},${fechaMedicamento},${unidadesMedicamento}, ${prevencion}\n"
    }
}


//nombre_farm,nombre_medic,precio_medic,fecha_medic,unidades_medic,prevencion