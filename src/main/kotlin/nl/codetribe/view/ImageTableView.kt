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

class ImageTableView  : View() {
    override val root = VBox()
    val controller : TestImageController by inject()

    init {
        with(root){
            tableview(FXCollections.observableArrayList(controller.getPhotoList())) {
                column("name", Photo::name)
                column("filename", Photo::filepath)
                column("icon", Photo::filepath).cellFormat {
                    graphic = ImageView(resources.url("/$it")!!.toExternalForm())
                }

            }
            flowpane {
                //hgap=8.0
                //vgap=8.0
                controller.getPhotoList().forEach {
                    println(it.filepath)
                    imageview(resources.url("/${it.filepath}")!!.toExternalForm())
                }
            }
        }
    }
}

fun loadImageFromResource(filepath: String) : Image {
    println("loading $filepath")
    val classLoader = ImageTableView::class.java.classLoader
    return Image(classLoader.getResource(filepath).file)
}
