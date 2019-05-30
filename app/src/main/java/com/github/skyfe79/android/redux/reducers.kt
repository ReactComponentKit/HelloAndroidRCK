package com.github.skyfe79.android.helloandroidrck.redux

import com.github.skyfe79.android.MainState
import com.github.skyfe79.android.MainViewModel
import com.github.skyfe79.android.Route
import com.github.skyfe79.android.helloandroidrck.action.ClickButtonAction
import com.github.skyfe79.android.reactcomponentkit.redux.Action
import com.github.skyfe79.android.reactcomponentkit.redux.State
import io.reactivex.Observable

fun MainViewModel.routes(state: State, action: Action): Observable<State> {
    val mainState = (state as? MainState) ?: return Observable.just(state)

    return when (action) {
        is ClickButtonAction ->  Observable.just(mainState.copy(route = Route.Alert(action.message)))
        else -> Observable.just(state)
    }
}