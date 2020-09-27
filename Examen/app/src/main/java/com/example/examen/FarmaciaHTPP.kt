package com.example.examen

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class FarmaciaHTPP (
    val createdAt : Long,
    val updatedAt: Long,
    val id: Int,
    val idFarmacia : Int?,
    var nombreFarmacia :String?,
    var direccionFarmacia : String?,
    var numeroTrabajadores : Int?,
    var compra : Float?,
    var atencion : Boolean?
) :Parcelable {

    var fechaCreacion : Date
    var fechaActualizacion : Date

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {

    }

    init {
        fechaCreacion= Date(createdAt)
        fechaActualizacion= Date(updatedAt)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
        parcel.writeInt(id)
        parcel.writeValue(idFarmacia)
        parcel.writeString(nombreFarmacia)
        parcel.writeString(direccionFarmacia)
        parcel.writeValue(numeroTrabajadores)
        parcel.writeValue(compra)
        parcel.writeValue(atencion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FarmaciaHTPP> {
        override fun createFromParcel(parcel: Parcel): FarmaciaHTPP {
            return FarmaciaHTPP(parcel)
        }

        override fun newArray(size: Int): Array<FarmaciaHTPP?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString ():String{
        return "${idFarmacia} ${nombreFarmacia} ${direccionFarmacia} ${numeroTrabajadores} ${compra} ${atencion}"
    }

}