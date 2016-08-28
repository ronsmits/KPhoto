package nl.codetribe.view

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.DataFormat
import javafx.scene.input.MouseEvent
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import org.controlsfx.control.PopOver
import tornadofx.*

/**
 * Created by ron on 8/21/16.
 */
val photoformat = DataFormat("photo")

class ImageTableView : View() {
    override val root = datagrid<Photo> {
        cellWidth=200.0
        cellHeight=200.0
//        cachedGraphic { ImageView(Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)) }
        cellFormat {
            imageview { image=Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true) }
            addEventHandler(MouseEvent.MOUSE_CLICKED, { e ->
                PopOver().apply {
                    contentNode = vbox {
                        label(it.name)
                        label(it.filepath)
                    }
                    show(this@cellFormat)
                }
            })
        }
    }

    fun update(category: PhotoCategory){
        root.items=category.photolist.observable()
    }
    val rootk = flowpane {
        hgap = 10.0
        vgap = 10.0
        prefWidth = 600.0

    }

//    fun update(category: PhotoCategory) {
//        with(root) {
//            root.children.clear()
//            category.photolist.sortBy { it.name }
//            category.photolist.forEach {
//                imageview {
//                    onDragDetected =  EventHandler<MouseEvent>(){ e->
//                        this.startDragAndDrop(TransferMode.LINK).apply {
//                            setContent { put(photoformat,it) }
//                        }
//                        e.consume()
//                    }
//
//                    image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)
//                    addEventHandler(MouseEvent.MOUSE_CLICKED, { e ->
//                        PopOver().apply {
//                            contentNode = vbox {
//                                label(it.name)
//                                label(it.filepath)
//                            }
//                            show(this@imageview)
//                        }
//                    })
//                }
//            }
//        }
//    }
}
