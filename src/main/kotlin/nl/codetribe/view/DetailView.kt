package nl.codetribe.view

import com.drew.metadata.Tag
import javafx.geometry.Orientation
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import nl.codetribe.controller.PhotoController
import tornadofx.*
import kotlin.reflect.KFunction

/**
 * Created by ron on 8/28/16.
 */


class DetailView : View() {
    val expandedWith = 300.0
    override val root = squeezebox {
        multiselect = false
        fillHeight = true
        prefWidth = expandedWith
        fold("Basic") { this += BasicViewPane::class }
        fold("Exif") { this += ExifPane::class }
    }
}

class BasicViewPane : View() {
    val controller: PhotoController by inject()

    override val root = form {
        prefWidth = 250.0
        fieldset("File info") {
            labelPosition = Orientation.VERTICAL
            field("File") { label(controller.selectedPhoto.name) }
            field("Path") { label(controller.selectedPhoto.filepath) }
            field("Hash") { label(controller.selectedPhoto.hash) }
        }
    }
}

class ExifPane : View() {
    val controller: PhotoController by inject()

    override val root = tableview(controller.taglist) {
        prefHeight = 300.0
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
    return this.column(title, propName);
}