package com.github.skyfe79.android

import android.app.Application
import com.github.skyfe79.android.helloandroidrck.action.ClickButtonAction
import com.github.skyfe79.android.reactcomponentkit.redux.Output
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.android.reactcomponentkit.viewmodel.RCKViewModel

sealed class Route {
    object None: Route()
    data class Alert(val message: String): Route()
}

data class MainState(
    val route: Route = Route.None
): State() {
    override fun copyState(): State {
        return this.copy()
    }
}

class MainViewModel(application: Application): RCKViewModel<MainState>(application) {

    val route = Output<Route>(Route.None)

    override fun setupStore() {

        initStore { store ->
            store.initialState(MainState())

            store.flow<ClickButtonAction>(
                { state, action ->
                    state.copy(route = Route.Alert(action.message))
                }
            )
        }

    }

    override fun on(newState: MainState) {
        route.accept(newState.route).afterReset(Route.None)
    }
}