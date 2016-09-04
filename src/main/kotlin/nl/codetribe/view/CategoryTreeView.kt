package nl.codetribe.view

import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.scene.control.TreeItem
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import tornadofx.*

/**
 * Created by ronsmi on 8/26/2016.
 */
class CategoryTreeView : View() {
    val imageView: ImageTableView by inject()
    override val root = treeview<PhotoCategory> {
        root = TreeItem(rootCategory)
        root.isExpanded = true

        cellFormat {
            text = it.name
            onUserSelect { imageView.update(it) }
            if (it.dropAllowed) {
                onDragEntered = EventHandler<DragEvent> { event ->
                    event.consume()
                }
                onDragExited = EventHandler<DragEvent> { event ->
                    event.consume()
                }
                onDragOver = EventHandler<DragEvent> {
                    event ->
                    with(event.dragboard.getContent(photoformat) as Photo) {
                        if (!it.photolist.contains(this))
                            event.acceptTransferModes(TransferMode.LINK)
                    }
                    event.consume()
                }
                onDragDropped = EventHandler<DragEvent> { event ->
                    with(event.dragboard.getContent(photoformat) as Photo) {
                        it.photolist.add(this)
                        this@treeview.refresh()
                    }
                    with(event) {
                        isDropCompleted = true
                        consume()
                    }
                }
            }
        }
        populate {
            it.value.children.addListener(ListChangeListener {
                e ->
                it.children.forEach { clearAll(it) }
                populate { it.value.children }
            });
            it.value.children
        }
    }

    private fun clearAll(item: TreeItem<PhotoCategory>?) {
        item?.children?.forEach { clearAll(it) }
        item?.children?.clear()
    }
}