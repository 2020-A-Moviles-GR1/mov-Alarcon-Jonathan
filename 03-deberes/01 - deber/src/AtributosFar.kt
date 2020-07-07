class AtributosFar (
        val idFarmacia : Int?,
        val nombreFarmacia :String,
        var direccionFarmacia : String,
        var numeroTrabajadores : Int?,
        var compra : Float?,
        val atencion : Boolean?
){
    override fun toString ():String{
        return "${idFarmacia},${nombreFarmacia},${direccionFarmacia},${numeroTrabajadores},${compra},${atencion}\n"
    }
}