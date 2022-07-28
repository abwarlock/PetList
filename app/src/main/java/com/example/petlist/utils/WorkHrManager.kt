package com.example.petlist.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.example.petlist.models.Configurations
import com.example.petlist.models.DataRepository
import com.example.petlist.models.OutsideWorkHourEvent
import org.greenrobot.eventbus.EventBus
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class WorkHrManager private constructor(context: Context) {

    companion object {
        private var instance: WorkHrManager? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            return@synchronized WorkHrManager(context).also { instance = it }
        }
    }

    private var config: Configurations? = DataRepository.getConfig(context)

    private val filter: IntentFilter = IntentFilter().apply {
        addAction(Intent.ACTION_TIME_TICK)
        addAction(Intent.ACTION_TIME_CHANGED)
        addAction(Intent.ACTION_DATE_CHANGED)
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("", "onReceive:: ${intent?.action}")
            EventBus.getDefault().post(OutsideWorkHourEvent(true))
        }
    }

    fun isAppIsAllowedToUse(): Boolean {
        return config?.let {
            val instance = Calendar.getInstance()
            val settings = it.settings

            settings.workDays.contains(instance.getWeekDay()) &&
                    (settings.workStartHr?.let { it1 -> timeIsBefore(it1, instance.time) } ?: false
                            && settings.workEndHr?.let { it2 -> timeIsBefore(instance.time, it2) } ?: false)
        } ?: false
    }

    private fun timeIsBefore(date1: Date, date2: Date): Boolean {
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return timeFormat.format(date1) < timeFormat.format(date2)
    }


    fun registerEvents(context: Context) {
        context.registerReceiver(receiver, filter)
    }

    fun unRegisterEvents(context: Context) {
        try {
            context.unregisterReceiver(receiver)
        }catch (ex:Exception){
            ex.localizedMessage?.let { Log.e("", it) }
        }
    }
}