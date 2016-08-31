package nl.codetribe.view

import javafx.geometry.Orientation
import javafx.scene.layout.VBox
import nl.codetribe.controller.TestImageController
import tornadofx.*

/**
 * Created by ron on 8/28/16.
 */


class DetailView : View() {
    val basicView: BasicViewPane by inject()
    val expandedWith = 250.0
    val accordion = accordion {
        prefWidth = expandedWith
        fold("basic", basicView.root)
        fold("exif", VBox()) {
            label("test")
            label("test")
            label("test")
            label("test")
        }
    }
    override val root =
            vbox {
                isVisible = false
            }

    fun toggle() {

        if (root.isVisible) {
            root.isVisible = false
            accordion.removeFromParent()
        } else {
            root.add(accordion)
            root.isVisible = true
        }
    }
}

class BasicViewPane : View() {
    val controller: TestImageController by inject()

    override val root = form {
        fieldset("File info") {
            labelPosition = Orientation.VERTICAL
            field("File") { label(controller.selectedPhoto.name) }
            field("Path") { label(controller.selectedPhoto.filepath) }
        }
    }
}
