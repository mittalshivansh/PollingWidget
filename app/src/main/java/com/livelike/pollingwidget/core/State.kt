package com.livelike.pollingwidget.core

/**
 * Created by shivanshmittal on 2019-06-24.
 */

sealed class State {
    data class DataLoaded<T >(val data: T) : State()
    data class ShowError(val errorMsg: String) : State()
    object ShowLoading : State()

//    companion object {
//        @JvmStatic
//        fun processApiResponse(apiResponse: SamadhanApiResponse): State {
//            return if (apiResponse?.status != null && apiResponse.status)
//                DataLoaded(apiResponse!!)
//            else
//                ShowError(
//                    if (apiResponse.error?.isEmpty() == false)
//                        apiResponse.error
//                    else
//                        apiResponse?.message ?: "not able to get response from server"
//                )
//        }
//    }
}
