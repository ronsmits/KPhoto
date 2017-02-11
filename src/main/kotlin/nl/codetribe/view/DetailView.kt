package nl.codetribe.view

import com.drew.metadata.Tag
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.Accordion
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TitledPane
import javafx.scene.layout.VBox
import nl.codetribe.controller.PhotoController
import tornadofx.*
import kotlin.reflect.KFunction

/**
 * Created by ron on 8/28/16.
 */


class DetailView : View() {
    val basicView: BasicViewPane by inject()
    val expandedWith = 250.0
    override val root = squeezebox {
        multiselect=false
        fillHeight=true
        prefWidth = expandedWith
        fold("basic") { this+=basicView }
        fold("exif") { this += ExifPane::class}
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

class ExifPane : View() {
    val controller: PhotoController by inject()

    override val root = tableview(controller.taglist) {
        columnResizePolicy = SmartResize.POLICY
        column("name", Tag::getTagName).enableTextWrap()

        column("value", Tag::getDescription).enableTextWrap()
    }
}
/**
 * Create a column using the getter of the attribute you want shown.
 */
@JvmName("pojoColumn") fun <S, T> TableView<S>.column(title: String, getter: KFunction<T>): TableColumn<S, T> {
    val propName = getter.name.substring(3).let { it.first().toLowerCase() + it.substring(1) }
    return this.column( title, propName );
}