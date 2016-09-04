package nl.codetribe.view

import javafx.event.EventHandler
import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import nl.codetribe.controller.PhotoController
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import tornadofx.*

/**
 * Created by ron on 8/21/16.
 */
val photoformat = DataFormat("photo")

class ImageTableView : View() {
    val controller: PhotoController by inject()
    override val root = datagrid<Photo> {
        onUserSelect(1) {
            controller.selectedPhoto.rebind { photo = selectedItem!! }
        }
        cellCache {
            imageview {
                image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)
                onDragDetected = EventHandler<MouseEvent>() { e ->
                    this.startDragAndDrop(TransferMode.LINK).apply {
                        setContent { put(photoformat, it) }
                    }
                    e.consume()
                }
            }
        }
        cellWidth = 200.0
        cellHeight = 200.0

    }

    fun update(category: PhotoCategory) {
        root.items = category.photolist.observable()
    }
}
