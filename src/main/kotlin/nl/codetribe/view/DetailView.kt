package nl.codetribe.view

import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.Accordion
import javafx.scene.control.TitledPane
import javafx.scene.layout.VBox
import nl.codetribe.controller.PhotoController
import tornadofx.*

/**
 * Created by ron on 8/28/16.
 */


class DetailView : View() {
    val basicView: BasicViewPane by inject()
    val expandedWith = 250.0
    override val root = squeezebox {
        multiselect=false
        prefWidth = expandedWith
        fold("basic") { this+=basicView }
        fold("exif") {
            vbox {
                label("test")
                label("test")
                label("test")
                label("test")
            }
        }
    }
}
class BasicViewPane : View() {
    val controller: PhotoController by inject()

    override val root = form {
        maxHeight = 100.0
        fieldset("File info") {
            labelPosition = Orientation.VERTICAL
            field("File") { label(controller.selectedPhoto.name) }
            field("Path") { label(controller.selectedPhoto.filepath) }
        }
    }
}
