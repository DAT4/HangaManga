package com.example.hangamanga.observer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.hangamanga.enumerators.RequestType
import com.example.hangamanga.libs.DataGetter.getStuff
import com.example.hangamanga.libs.DataGetter.updateStuff
import java.lang.reflect.Type

interface ICache<T> : IObservable {
    val type: Type
    val url: String

    var content: T

    fun cache() {
        GlobalScope.launch(Dispatchers.IO) {
            val data: T = getStuff(this@ICache.url, this@ICache.type)
            launch(Dispatchers.Main) {
                this@ICache.content = data
                sendUpdateEvent()
            }
        }
    }

    fun update(model: Any, method: RequestType) {
        GlobalScope.launch(Dispatchers.IO) {
            updateStuff(model, this@ICache.url, method)
            cache()
        }
    }
}