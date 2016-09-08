package nl.codetribe.view

import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.DataFormat
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
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
        onUserSelect(1) {controller.selectedPhoto.rebind { photo = selectedItem?: Photo("", "") } }
        onUserSelect(2) { showImagePopup(it)}
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

    private fun showImagePopup(photo: Photo) {
        val image = Image (photo.toURL().toExternalForm())
        println("dimensions ${image.width} ${image.height}")
        val im = ImageView(image)
        val vbox = gridpane { add(im)
        alignment=Pos.CENTER
        }
        with (Stage()) {
            scene = Scene(vbox, 640.0, 480.0)
            initModality(Modality.APPLICATION_MODAL)
            initStyle(StageStyle.DECORATED)
            im.isPreserveRatio=true
            im.fitWidthProperty().bind(scene.widthProperty())
            im.fitHeightProperty().bind(scene.heightProperty())
            show()
        }
    }

    fun update(category: PhotoCategory) {
        root.items = category.photolist.observable()
    }
}
