package nl.codetribe.view

import com.sun.glass.ui.Cursor
import tornadofx.*

/**
 * Created by ron on 8/28/16.
 */
class DetailView : View() {
    val expandedWith = 250.0
    override val root = vbox {
        isVisible=false
        prefWidth=0.0
        label("test")
    }

    fun toggle() {

        if(root.isVisible) {
            root.prefWidth = 0.0
            root.isVisible=false
        }
        else {
            root.prefWidth=expandedWith
            root.isVisible=true
        }
    }
}