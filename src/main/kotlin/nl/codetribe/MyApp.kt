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

        val tag = PhotoCategory("tags", dropAllowed = false)
        tag.children.add(PhotoCategory("test tag", true))
        rootCategory.children.add(tag)
        val directoryCategory = PhotoCategory("directories", dropAllowed = false)
        rootCategory.children.add(directoryCategory)
        startScan("./src/main/resources", directoryCategory)
//        startScan("/Volumes/big2/fotos/kids", directoryCategory)
    }
}
