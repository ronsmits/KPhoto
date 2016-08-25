package nl.codetribe.view

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.control.TreeItem
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.BorderPane
import nl.codetribe.controller.TestImageController
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()
    val controller: TestImageController by inject()
    val imageView: ImageTableView by inject()
    val categoryview: CategoryView by inject()
    val topView: TopView by inject()

    init {
        with(root) {
            top = topView.root
            left = categoryview.root
            center = scrollpane {
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


class CategoryView : View() {
    val imageView: ImageTableView by inject()
    override val root = treeview<PhotoCategory> {
        isEditable=true
        root = TreeItem(rootCategory)
        isShowRoot = false
        root.isExpanded = true
        cellFormat {
            text = it.name
            onDragOver = EventHandler<DragEvent> { e ->
                println(e)
                val db = e.dragboard
                println(db)
                e.acceptTransferModes(TransferMode.COPY)
                e.consume()
            }
            onDragEntered = EventHandler<DragEvent> { e ->
                println("drag entered")
            }
            onDragDropped = EventHandler<DragEvent> { e ->
                println(e)
                val db = e.dragboard
                println(db)
                e.isDropCompleted = true
                e.consume()
            }
        }
        populate { it.value.children }
        onUserSelect { imageView.update(it) }
    }
}

