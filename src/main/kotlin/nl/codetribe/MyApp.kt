package nl.codetribe

import nl.codetribe.model.PhotoCategory
import nl.codetribe.scanner.startScan
import nl.codetribe.view.ImageTableView
import tornadofx.App
import tornadofx.importStylesheet

val categories = mutableListOf<PhotoCategory>()

class MyApp : App(ImageTableView::class) {
    init {
        importStylesheet(Styles::class)

        startScan("/media/ron/big2/fotos")
        categories.removeAll { it.photolist.size==0 }
    }
}