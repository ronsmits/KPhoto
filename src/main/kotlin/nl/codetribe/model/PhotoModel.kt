package nl.codetribe.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

/**
 * Created by ron on 8/31/16.
 */
class PhotoModel(var photo: Photo) : ViewModel() {
    val name = bind { SimpleStringProperty(photo.name) }
    val filepath = bind { SimpleStringProperty(photo.filepath) }
}