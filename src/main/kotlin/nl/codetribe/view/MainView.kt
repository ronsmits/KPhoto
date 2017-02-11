package nl.codetribe.view

import javafx.geometry.HorizontalDirection
import javafx.geometry.Side
import javafx.scene.layout.BorderPane
import nl.codetribe.rootCategory
import nl.codetribe.scanner.buildMD5Strings
import nl.codetribe.scanner.findDoubles
import nl.codetribe.scanner.listOfPhotos
import tornadofx.View
import tornadofx.drawer

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
            right = drawer(side =Side.RIGHT) {
                item("details", showHeader = false) {
                    add(detailView)
                }
            }
        }
    }
}


fun startLookingForDuplicates() {
    listOfPhotos(rootCategory)
    buildMD5Strings()
    findDoubles()
}