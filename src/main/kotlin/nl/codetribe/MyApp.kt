package nl.codetribe

import nl.codetribe.model.PhotoCategory
import nl.codetribe.scanner.startScan
import nl.codetribe.view.MainView
import tornadofx.App
import tornadofx.importStylesheet

val rootCategory = PhotoCategory("root")

class MyApp : App(MainView::class) {
    init {
        importStylesheet(Styles::class)

        startScan("c:\\Users\\ronsmi\\src\\tornadofx")
//        startScan("/media/ron/Bigdisk")
        println("done")
    }
}
