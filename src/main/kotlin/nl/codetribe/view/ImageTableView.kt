package nl.codetribe.view

import javafx.scene.image.Image
import javafx.scene.input.DataFormat
import javafx.util.Callback
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import org.controlsfx.control.GridCell
import org.controlsfx.control.GridView
import tornadofx.View
import tornadofx.imageview
import tornadofx.observable

/**
 * Created by ron on 8/21/16.
 */
val photoformat = DataFormat("photo")

class ImageTableView : View() {
    override val root = GridView<Photo>().apply {
//
        prefWidth=800.0
        horizontalCellSpacing = 5.0
        verticalCellSpacing = 5.0
        cellWidth = 200.0
        cellFormat {
            imageview {
                image = Image(it.toURL().toExternalForm(), 200.0, 200.0, true, true, true)
            }
        }
    }

    fun update(category: PhotoCategory){
        root.items=category.photolist.observable()
    }
}


fun <S> GridView<S>.cellFormat(formatter: (GridCell<S>.(S) -> Unit)) {
    cellFactory = Callback {
        object : GridCell<S>() {
            override fun updateItem(item: S?, empty: Boolean) {
                super.updateItem(item, empty)

                if (item == null || empty) {
                    text = null
                    graphic = null
                } else {
                    formatter(this, item)
                }
            }
        }
    }
}