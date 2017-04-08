package nl.codetribe.view

import javafx.geometry.Side
import nl.codetribe.rootCategory
import nl.codetribe.scanner.buildMD5Strings
import nl.codetribe.scanner.findDoubles
import nl.codetribe.scanner.listOfPhotos
import tornadofx.*

class MainView : View() {

    override val root = borderpane {
        top { this += TopView::class }
        left { this += CategoryTreeView::class }
        center { this += ImageTableView::class }
        bottom = drawer(side = Side.BOTTOM, multiselect = false) {
            fixedContentSize = 200.0
            item("Basic") { add(BasicViewPane::class) }
            item("Exif") { add(ExifPane::class) }
        }
    }
}


fun startLookingForDuplicates() {
    listOfPhotos(rootCategory)
    buildMD5Strings()
    findDoubles()
}
