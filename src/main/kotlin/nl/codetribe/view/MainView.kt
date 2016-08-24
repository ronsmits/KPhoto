package nl.codetribe.view

import nl.codetribe.controller.DeliciousController
import nl.codetribe.model.DeliciousBookmark
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import javafx.scene.web.WebView
import java.util.logging.Level.INFO
import javafx.scene.control.TableView
import javafx.scene.control.TreeItem
import javafx.scene.layout.VBox
import javafx.stage.Stage
import nl.codetribe.controller.TestImageController
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()
    val controller: TestImageController by inject()
    val imageView: ImageTableView by inject()
    val categoryview: CategoryView by inject()

    init {
        with(root) {
            left = categoryview.root
            center =
                vbox {
                    prefWidth=800.0

//                    prefHeight=800.0
                    scrollpane {
                        prefWidth = 600.0
                        hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                        vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
                        isFitToWidth = true
                        isFitToHeight= true
                        add(imageView.root)
                    }
                }
            }
        }
    }


class CategoryView : View() {
    val imageView: ImageTableView by inject()
    override val root = treeview<PhotoCategory> {
        root = TreeItem(rootCategory)
        root.isExpanded = true
        cellFormat { text = "${it.name} ${it.photolist.size}" }
        populate { it.value.children }
        onUserSelect { imageView.update(it) }
    }
}

