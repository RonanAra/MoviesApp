package com.example.moviesapp.utils.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

fun <T, R, E> CoroutineScope.launchSuspendFunZip(
    firstBlockToRun: suspend CoroutineScope.() -> T,
    secondBlockToRun: suspend CoroutineScope.() -> R,
    thirdBlockToRun: suspend CoroutineScope.() -> E,
    onLoading: ((Boolean) -> Unit)? = null,
    onSuccess: ((Triple<T, R, E>) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
) {
    launch {
        supervisorScope {
            try {
                onLoading?.invoke(true)
                val firstResult = async { firstBlockToRun() }
                val secondResult = async { secondBlockToRun() }
                val thirdResult = async { thirdBlockToRun() }

                onSuccess?.invoke(
                    Triple(
                        first = firstResult.await(),
                        second = secondResult.await(),
                        third = thirdResult.await()
                    )
                )
                onLoading?.invoke(false)
            } catch (ex: Exception) {
                onLoading?.invoke(false)
                onError?.invoke(ex)
            }
        }
    }
}