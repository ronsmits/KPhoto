package nl.codetribe.view

import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()
    val imageView: ImageTableView by inject()
    val categoryview: CategoryTreeView by inject()
    val topview : TopView by inject()

    init {
        with(root) {
            top = topview.root
            left = categoryview.root
            center = vbox {
                prefWidth = 800.0
                scrollpane {
                    prefWidth = 600.0
                    hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                    vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
                    isFitToWidth = true
                    isFitToHeight = true
                    add(imageView.root)
                }
            }
        }
    }
}

class TopView : View(){
    override val root = vbox { label("top") }

}


