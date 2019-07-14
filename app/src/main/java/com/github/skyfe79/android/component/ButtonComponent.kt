package com.github.skyfe79.android.helloandroidrck.component

import android.content.Context
import android.view.View
import android.widget.Button
import com.github.skyfe79.android.helloandroidrck.action.ClickButtonAction
import com.github.skyfe79.android.reactcomponentkit.component.ViewComponent
import com.github.skyfe79.android.reactcomponentkit.eventbus.Token
import org.jetbrains.anko.button
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.wrapContent

class ButtonComponent(token: Token): ViewComponent(token, false) {
    private lateinit var button: Button

    override fun layout(ui: org.jetbrains.anko.AnkoContext<Context>): View = with(ui) {
        val layout = relativeLayout {
            lparams(wrapContent, wrapContent)
            button = button("Click Me")
        }

        setupActions()

        return layout
    }

    private fun setupActions() {
        button.onClick {
            dispatch(ClickButtonAction("Hello AndroidReactComponentKit :)"))
        }
    }
}