package nl.codetribe.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.ViewModel

/**
 * Created by ron on 8/31/16.
 */
class PhotoModel : ItemViewModel<Photo>() {
    val name = bind { SimpleStringProperty(item?.name) }
    val filepath = bind { SimpleStringProperty(item?.filepath) }
}