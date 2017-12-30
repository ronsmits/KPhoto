package nl.codetribe

import javafx.application.Application
import nl.codetribe.controller.PhotoController
import nl.codetribe.model.Directory
import nl.codetribe.model.PhotoCategory
import nl.codetribe.view.MainView
import tornadofx.*

val rootCategory = PhotoCategory("root")
val directoryCategory = Directory(name = "directories", absolutePath = "")
class MyApp : App(MainView::class) {
    private val controller: PhotoController by inject()
    init {
        importStylesheet(Styles::class)

        rootCategory.children.add(controller.tags)
        rootCategory.children.add(directoryCategory)
//        startScan("./src/main/resources", directoryCategory)
    }
}

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java)
}