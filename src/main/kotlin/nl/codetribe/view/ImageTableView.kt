package nl.codetribe.view

import javafx.collections.FXCollections
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.codetribe.controller.TestImageController
import nl.codetribe.model.Photo
import tornadofx.*

/**
 * Created by ron on 8/21/16.
 */

fun <S, T> TableView<S>.imageColumn(title: String, prop: kotlin.reflect.KProperty1<S, T>): TableColumn<S, T> {
    val column = TableColumn<S, T>(title)

    column.cellFactory = Callback {

        TableCell<S, T>().apply {
            println(prop.get(rowItem))
            //ImageView(loadImageFromResource(prop.get(rowItem) as String))
        }

    }
    columns.add(column)
    return column
}
class ImageTableView  : View() {
    override val root = VBox()
    val controller : TestImageController by inject()

    init {
        with(root){
            tableview(FXCollections.observableArrayList(controller.getPhotoList())) {
                column("name", Photo::name)
                column("filename", Photo::filepath)
                imageColumn("icon", Photo::filepath)

            }
        }
    }
}

fun loadImageFromResource(filepath: String) : Image {
    println("loading $filepath")
    val classLoader = ImageTableView::class.java.classLoader
    return Image(classLoader.getResource(filepath).file)
}
