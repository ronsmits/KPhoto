package nl.codetribe

import javafx.application.Application
import nl.codetribe.model.Directory
import nl.codetribe.model.PhotoCategory
import nl.codetribe.scanner.startScan
import nl.codetribe.view.MainView
import tornadofx.App
import tornadofx.importStylesheet

val rootCategory = PhotoCategory("root")
val directoryCategory = Directory(name = "directories", absolutePath = "")

class MyApp : App(MainView::class) {
    init {
        importStylesheet(Styles::class)

        val tag = PhotoCategory("tags", dropAllowed = false)
        tag.children.add(PhotoCategory("test tag", true))
        rootCategory.children.add(tag)
        rootCategory.children.add(directoryCategory)
        startScan("./src/main/resources", directoryCategory)
    }
}

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java)
}