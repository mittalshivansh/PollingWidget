package com.livelike.pollingwidget.core

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.livelike.pollingwidget.core.util.distinctUntilChanged
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

/**
 * Created by shivanshmittal on 2019-06-22.
 * it's common to load data from the network while showing the disk copy of that data,
 * it's good to create a helper class that you can reuse in multiple places.
 * Customized it according to current usecase
 * Detailed description : [android recommended architecture](https://developer.android.com/jetpack/docs/guide#addendum)
 */

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) { result.value =
            Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult.value)) {
                try {
                    fetchFromNetwork(dbResult.value)
                } catch (e: Exception) {
                    Log.e("NetworkBoundResource", "An error happened: $e")
                    result.addSource(loadFromDb()){newData ->
                        setValue(Resource.error(e, newData))
                    }
                }
            } else {
                Log.d(NetworkBoundResource::class.java.name, "Return data from local database")
                result.addSource(dbResult){newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
        return this
    }

    fun asLiveData() = (result as LiveData<Resource<ResultType>>).distinctUntilChanged()

    // ---

    private suspend fun fetchFromNetwork(dbResult: ResultType?) {
        Log.d(NetworkBoundResource::class.java.name, "Fetch data from network")
        setValue(Resource.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
        val apiResponse = fromRemote().await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        saveCallResults(apiResponse)
        result.addSource(loadFromDb()){newData ->
            setValue(Resource.success(newData))
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Log.d(NetworkBoundResource::class.java.name, "Resource: "+newValue)
        if (result.value != newValue) result.postValue(newValue)
    }


    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract suspend fun fromRemote(): Deferred<ResultType>
}