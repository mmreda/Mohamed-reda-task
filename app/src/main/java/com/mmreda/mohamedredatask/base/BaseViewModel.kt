package com.developnetwork.universe.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.mmreda.mohamedredatask.utils.errorHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(
    application: Application,
) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e("Coroutine Exception: $exception")
    }
    final override val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val scope: CoroutineScope = CoroutineScope(coroutineContext + exceptionHandler)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    val errorLiveData = MutableLiveData<String?>()
    val showLoading = MutableLiveData<Boolean>()

    fun <T> handleFlowResponse(isMessage: Boolean = false, request: suspend () -> T) =
        flow {
            try {

                if (!isMessage)
                    showLoading.postValue(true)

                val response = withContext(Dispatchers.IO) {
                    request()
                }

                showLoading.postValue(false)
                errorLiveData.postValue(null)

                emit(response)

            } catch (e: Exception) {

                Timber.e("Ex: $e")

                showLoading.postValue(false)
                errorHandler(e)?.let { errorLiveData.postValue(it) }

                val bundle = Bundle()
                bundle.putString("api_error", e.toString())

            }

            return@flow
        }.map {
            it
        }.cancellable().asLiveData()
}