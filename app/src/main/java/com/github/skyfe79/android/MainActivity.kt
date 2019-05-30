package com.github.skyfe79.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelProviders
import com.github.skyfe79.android.helloandroidrck.component.ButtonComponent
import com.github.skyfe79.android.reactcomponentkit.component.component
import com.github.skyfe79.android.reactcomponentkit.rx.AutoDisposeBag
import com.github.skyfe79.android.reactcomponentkit.rx.disposedBy
import org.jetbrains.anko.alert
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val disposeBag: AutoDisposeBag by lazy {
        AutoDisposeBag(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        verticalLayout {
            lparams(matchParent, matchParent)
            gravity = Gravity.CENTER
            component(ButtonComponent(viewModel.token))
        }

        handleViewModelOutputs()
    }

    private fun handleViewModelOutputs() {
        viewModel
            .route
            .asObservable()
            .skip(1)
            .subscribe {
                handleRoute(it)
            }
            .disposedBy(disposeBag)
    }

    private fun handleRoute(route: Route) {
        when(route) {
            is Route.Alert -> {
                alert(route.message).show()
            }
            else -> Unit
        }
    }
}
