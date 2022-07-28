package com.example.petlist.models

import android.content.Context
import android.util.Log
import com.example.petlist.utils.readFile
import com.google.gson.Gson

object DataRepository {

    fun getPetListModels(context: Context): PetListModels? {
        return try {
            Gson().fromJson(context.assets.readFile("pets_list.json"), PetListModels::class.java)
        } catch (ex: Exception) {
            ex.localizedMessage?.let { Log.e("DataRepository", it) }
            null
        }
    }

    fun getConfig(context: Context): Configurations? {
        return try {
            Gson().fromJson(context.assets.readFile("config.json"), Configurations::class.java).apply {
                this.settings.initialize()
            }
        } catch (ex: Exception) {
            ex.localizedMessage?.let { Log.e("DataRepository", it) }
            null
        }
    }
}