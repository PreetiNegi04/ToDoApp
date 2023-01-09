package com.preeti.inventory

import android.app.Application
import com.preeti.inventory.data.MyDatabase

class TodoApplication : Application(){
    val database: MyDatabase by lazy { MyDatabase.getDatabase(this) }
}