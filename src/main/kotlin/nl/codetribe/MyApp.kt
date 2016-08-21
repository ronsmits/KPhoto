package nl.codetribe

import nl.codetribe.view.ImageTableView
import tornadofx.App
import tornadofx.importStylesheet
import nl.codetribe.view.MainView
import java.io.File

class MyApp: App(ImageTableView::class) {
    init {
        importStylesheet(Styles::class)

        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(".")!!.file)
        println(file.absolutePath)
    }
}