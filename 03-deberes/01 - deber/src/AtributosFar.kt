class AtributosFar (
    val idFarmacia : Int?,
    val nombreFarmacia :String,
    val direccionFarmacia : String,
    val numeroTrabajadores : Int?,
    val compra : Float?,
    val atencion : Boolean?
){
    override fun toString ():String{
        return "${idFarmacia},${nombreFarmacia},${direccionFarmacia},${numeroTrabajadores},${compra},${atencion}\n"
    }
}