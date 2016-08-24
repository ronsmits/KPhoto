package nl.codetribe.view

import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import nl.codetribe.model.PhotoCategory
import org.controlsfx.control.PopOver
import tornadofx.*

/**
 * Created by ron on 8/21/16.
 */

class ImageTableView : View() {
    override val root = flowpane {
        hgap = 10.0
        vgap = 10.0
        prefWidth = 600.0

    }

    fun update(category: PhotoCategory) {
        with(root) {
            root.children.clear()
            category.photolist.sortBy { it.name }
            category.photolist.forEach {
                imageview {
                    image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)
                    addEventHandler(MouseEvent.MOUSE_CLICKED, {
                        e ->
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
        }
    }
}
