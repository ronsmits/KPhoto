package nl.codetribe.view

import javafx.scene.image.Image
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
        cachedGraphic {
            imageview {
                image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)
                addEventHandler(MouseEvent.MOUSE_CLICKED, { e ->
                    PopOver().apply {
                        contentNode = vbox {
                            label(it.name)
                            label(it.filepath)
                        }
                        show(this@imageview)
                    }
                })
            }
        }

        cellWidth = 200.0
        cellHeight = 200.0
//        cellFormat {
////            imageview { image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true) }
//            addEventHandler(MouseEvent.MOUSE_CLICKED, { e ->
//                PopOver().apply {
//                    contentNode = vbox {
//                        label(it.name)
//                        label(it.filepath)
//                    }
//                    show(this@cellFormat)
//                }
//            })
//        }
    }

    fun update(category: PhotoCategory) {
        root.items = category.photolist.observable()
    }
}
