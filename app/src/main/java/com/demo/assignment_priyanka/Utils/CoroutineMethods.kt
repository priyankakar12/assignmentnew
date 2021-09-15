package com.demo.assignment_priyanka.Utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CoroutineMethods {
    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch { work() }
}