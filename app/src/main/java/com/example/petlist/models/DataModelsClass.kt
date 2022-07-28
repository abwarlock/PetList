package com.example.petlist.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class OutsideWorkHourEvent(val checkStatus: Boolean)

data class PetListModels(@SerializedName("pets") val pets: List<Pets>)

data class Pets(
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("content_url") val contentUrl: String?,
    @SerializedName("date_added") val dateAdded: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(title)
        parcel.writeString(contentUrl)
        parcel.writeString(dateAdded)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pets> {
        override fun createFromParcel(parcel: Parcel): Pets {
            return Pets(parcel)
        }

        override fun newArray(size: Int): Array<Pets?> {
            return arrayOfNulls(size)
        }
    }
}

data class Configurations(@SerializedName("settings") val settings: Settings)

data class Settings(@SerializedName("workHours") val workHours: String) {
    lateinit var workDays: String
        private set
    var workStartHr: Date? = null
        private set
    var workEndHr: Date? = null
        private set

    fun initialize() {
        val displayFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        workHours.trim().split(" ").forEachIndexed { index, value ->
            when (index) {
                0 -> {
                    val dayValue = value.split("-")
                    val workDaysBuilder = java.lang.StringBuilder()
                    val dayList = listOf("SU", "M", "T", "W", "TH", "F", "S")
                    var startDay = ""
                    dayValue.forEachIndexed { dayIndex, day ->
                        if (dayIndex == 0) {
                            startDay = day.uppercase()
                        } else {
                            var startInsertion = false
                            for (endDay in dayList) {
                                if (!startInsertion && endDay == startDay) {
                                    startInsertion = true
                                }
                                if (startInsertion) {
                                    workDaysBuilder.append(",").append(endDay.uppercase())
                                    if (endDay == day.uppercase()) {
                                        break
                                    }
                                }
                            }

                        }
                    }
                    workDays = workDaysBuilder.toString()
                }
                1 -> {
                    value.trim().split("-").forEachIndexed { timeIndex, timeValue ->
                        if (timeIndex == 0) {
                            workStartHr = displayFormat.parse(timeValue.trim())
                        } else {
                            workEndHr = displayFormat.parse(timeValue.trim())
                        }
                    }

                }

            }
        }
    }

}