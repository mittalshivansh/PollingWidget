package com.livelike.pollingwidget.core

/**
 * Created by shivanshmittal on 2019-06-24.
 * LCE states for UI
 */

sealed class State {
    object Success : State()
    data class ShowError(val errorMsg: String) : State()
    object ShowLoading : State()
}
