package nl.codetribe.view

import com.sun.glass.ui.Cursor
import tornadofx.*

/**
 * Created by ron on 8/28/16.
 */
class DetailView : View() {
    val expandedWith = 250.0
    internal var hidden = true
    override val root = vbox {
        prefWidth=expandedWith
        label("test")
    }

    fun toggle() {

        if(hidden) {
            root.prefWidth = 0.0
            root.maxWidth=0.0
            root.isVisible=false
        }
        else {
            root.prefWidth=expandedWith
            root.maxWidth = expandedWith
            root.isVisible=true
        }
        hidden =!hidden
    }
}