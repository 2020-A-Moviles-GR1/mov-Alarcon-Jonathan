package com.example.examen

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class MedicamentoHTTP (
    val createdAt : Long,
    val updatedAt: Long,
    val id: Int,
    val idMed : Int?,
    var nombreFarmacia :String?,
    var nombreMedicamento : String?,
    var precioMedicamento : Float?,
    var fechaMedicamento: String?,
    var unidadesMedicamento : Int?,
    var prevencion : Boolean?
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
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
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
        parcel.writeValue(idMed)
        parcel.writeString(nombreFarmacia)
        parcel.writeString(nombreMedicamento)
        parcel.writeValue(precioMedicamento)
        parcel.writeString(fechaMedicamento)
        parcel.writeValue(unidadesMedicamento)
        parcel.writeValue(prevencion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MedicamentoHTTP> {
        override fun createFromParcel(parcel: Parcel): MedicamentoHTTP {
            return MedicamentoHTTP(parcel)
        }

        override fun newArray(size: Int): Array<MedicamentoHTTP?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString ():String{
        return "${idMed} ${nombreFarmacia} ${nombreMedicamento} ${precioMedicamento} ${fechaMedicamento} ${unidadesMedicamento} ${prevencion}"
    }

}