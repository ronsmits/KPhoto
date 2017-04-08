package nl.codetribe.view

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ButtonBar
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

        contextmenu {
            menuitem("add tag").action { find<addTagFragment>(mapOf(addTagFragment::category to selectedValue)).openModal() }
        }
        cellFormat {
            text = it.name
            onUserSelect { imageView.update(it) }
            if (it.dropAllowed) {
                setOnDragEntered(DragEvent::consume)
                setOnDragExited(DragEvent::consume)
                setOnDragOver { event ->
                    with(event.dragboard.getContent(photoformat) as Photo) {
                        if (!it.photolist.contains(this))
                            event.acceptTransferModes(TransferMode.LINK)
                    }
                    event.consume()
                }
                setOnDragDropped { event ->
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
        populate { it.value.children }
    }
}

class addTagFragment : Fragment() {
    val model = ViewModel()
    val groupname = model.bind { SimpleStringProperty() }
    val category: PhotoCategory by params
    override val root = form {
        fieldset {
            field("Group name") {
                textfield(groupname)
            }
            buttonbar {
                button("Ok", ButtonBar.ButtonData.OK_DONE).setOnAction {
                    println(category)
                    category.children.add(PhotoCategory(groupname.value))
                    close()
                }
                button("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE).setOnAction {
                    println("cancel")
                    close()
                }
            }
        }
    }
}