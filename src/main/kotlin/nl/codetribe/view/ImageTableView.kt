package nl.codetribe.view

import javafx.event.EventHandler
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.DataFormat
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import javafx.stage.Screen
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
        bindSelected(controller.selectedPhoto)
        onUserSelect(2) { showImagePopup(it) }
        cellCache {
            imageview {
                image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)
                onDragDetected = EventHandler<MouseEvent> { e ->
                    this.startDragAndDrop(TransferMode.LINK).apply {
                        setContent { put(photoformat, it.toJSON().toString()) }
                    }
                    e.consume()
                }
            }
        }
        cellWidth = 200.0
        cellHeight = 200.0

    }

    private fun showImagePopup(photo: Photo) {
        runAsync {
            ImageView(Image(photo.toURL().toExternalForm()))
        } ui {

            var height: Double
            var width: Double

            val visualBounds = Screen.getPrimary().visualBounds
            val screenRatio = visualBounds.width / visualBounds.height
            if (it.image.ratio() <= screenRatio) {
                // The scaled size is based on the height
                height = Math.min(visualBounds.height, it.image.height)
                width = height * it.image.ratio()
            } else {
                // The scaled size is based on the width
                width = Math.min(visualBounds.width, it.image.width)
                height = width / it.image.ratio()
            }

            builderWindow {
                stackpane {
                    prefWidth = width
                    prefHeight = height
                    it.apply {
                        fitHeightProperty().bind(this@stackpane.heightProperty())
                        fitWidthProperty().bind(this@stackpane.widthProperty())
                    }
                    add(it)
                }
            }
        }
    }

    fun update(category: PhotoCategory) {
        root.items = category.photolist.observable()
    }
}

fun Image.ratio(): Double {
    return width / height
}
