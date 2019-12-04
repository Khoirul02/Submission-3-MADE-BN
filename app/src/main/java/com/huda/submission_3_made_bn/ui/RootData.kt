package com.huda.submission_3_made_bn.ui

import android.os.Parcel
import android.os.Parcelable

data class RootData(
    var id: Int = 0,
    var name: String? = "",
    var photo: String? = "",
    var description: String? = "",
    var rate : Double = 0.1
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(photo)
        parcel.writeString(description)
        parcel.writeDouble(rate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RootData> {
        override fun createFromParcel(parcel: Parcel): RootData {
            return RootData(parcel)
        }

        override fun newArray(size: Int): Array<RootData?> {
            return arrayOfNulls(size)
        }
    }
}