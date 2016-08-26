package nl.codetribe.view

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.control.TreeItem
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.BorderPane
import nl.codetribe.controller.TestImageController
import nl.codetribe.model.Photo
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
            center = vbox {
                prefWidth = 800.0

//                    prefHeight=800.0
                scrollpane {
                    prefWidth = 600.0
                    hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                    vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
                    isFitToWidth = true
                    isFitToHeight = true
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
        cellFormat {
            text = "${it.name} ${it.photolist.size} ${it.dropAllowed}"
            onUserSelect { imageView.update(it) }
            if (it.dropAllowed) {
                onDragEntered = EventHandler<DragEvent> { event -> println(event) }
                onDragOver = EventHandler<DragEvent> {
                    event ->
                    val dragboard = event.dragboard
                    val content = dragboard.getContent(photoformat) as Photo
                    if (!it.photolist.contains(content)) {
                        println("$content not found in list")
                        event.acceptTransferModes(TransferMode.LINK)
                    } else println("found $content in list")
                    event.consume()
                }
                onDragDropped = EventHandler<DragEvent> { event ->
                    val dragboard = event.dragboard
                    val content = dragboard.getContent(photoformat)
                    it.photolist.add(content as Photo)
                    this.treeView.refresh()
                    event.isDropCompleted = true
                    event.consume()

                }
            }
        }
        populate { it.value.children }

    }
}

