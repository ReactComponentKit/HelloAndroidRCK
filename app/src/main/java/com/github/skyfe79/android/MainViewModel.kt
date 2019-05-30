package com.github.skyfe79.android

import com.github.skyfe79.android.helloandroidrck.redux.routes
import com.github.skyfe79.android.reactcomponentkit.redux.Output
import com.github.skyfe79.android.reactcomponentkit.redux.State
import com.github.skyfe79.android.reactcomponentkit.viewmodel.RootViewModelType

sealed class Route {
    object None: Route()
    data class Alert(val message: String): Route()
}

data class MainState(val route: Route): State()

class MainViewModel: RootViewModelType<MainState>() {

    val route = Output<Route>(Route.None)

    override fun setupStore() {
        store.set(
            initialState = MainState(Route.None),
            reducers = arrayOf(::routes)
        )
    }

    override fun on(newState: MainState) {
        route.acceptWithoutCompare(newState.route)
    }
}