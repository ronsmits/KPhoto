package nl.codetribe.view

import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import nl.codetribe.controller.TestImageController
import tornadofx.View
import tornadofx.imageview
import tornadofx.scrollpane
import tornadofx.tilepane
import java.nio.file.Paths

/**
 * Created by ron on 8/21/16.
 */

class ImageTableView : View() {
    override val root = VBox()
    val controller: TestImageController by inject()

    init {
        (controller.getAllPhotos())
        with(root) {
            prefWidth=800.0
            prefHeight=800.0
            scrollpane {
                prefHeight=800.0
                hbarPolicy=ScrollPane.ScrollBarPolicy.NEVER
                vbarPolicy=ScrollPane.ScrollBarPolicy.AS_NEEDED
                isFitToWidth=true
                tilepane {
                    hgap=3.0
                    vgap=3.0
                    controller.getAllPhotos().forEach {
                        imageview {
                            image = Image(Paths.get(it.filepath).toUri().toURL().toExternalForm(), 200.0, 200.0, true, true, true)
                        }
                    }
                }
            }
        }
    }
}
