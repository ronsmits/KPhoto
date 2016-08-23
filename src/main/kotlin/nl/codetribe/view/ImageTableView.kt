package nl.codetribe.view

import javafx.scene.image.Image
import nl.codetribe.model.PhotoCategory
import tornadofx.View
import tornadofx.*
import java.nio.file.Paths

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
        println("called for $category")
        with(root) {
            root.children.clear()
            category.photolist.sortBy { it.name }
            category.photolist.forEach { imageview { image = Image(Paths.get(it.filepath).toUri().toURL().toExternalForm(), 200.0, 200.0, true, true, true) } }
        }
    }
}
