package br.com.dogs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.lauchOnMain(call:()->Unit) {
    launch(Dispatchers.Main) {
        call.invoke()
    }
}