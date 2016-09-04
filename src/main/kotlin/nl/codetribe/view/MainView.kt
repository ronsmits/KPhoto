package nl.codetribe.view

import javafx.scene.control.ScrollPane
import javafx.scene.control.TreeItem
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import nl.codetribe.scanner.buildMD5Strings
import nl.codetribe.scanner.findDoubles
import nl.codetribe.scanner.listOfPhotos
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()
    val imageView: ImageTableView by inject()
    val categoryview: CategoryTreeView by inject()
    val topview: TopView by inject()
    val detailView: DetailView by inject()

    init {
        with(root) {
            top = topview.root
            left = categoryview.root
            center = imageView.root
            right = detailView.root
        }
    }
}



fun startLookingForDuplicates() {
    listOfPhotos(rootCategory)
    buildMD5Strings()
    findDoubles()
}