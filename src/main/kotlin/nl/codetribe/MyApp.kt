package nl.codetribe

import com.google.gson.Gson
import nl.codetribe.model.PhotoCategory
import nl.codetribe.scanner.startScan
import nl.codetribe.view.MainView
import tornadofx.App
import tornadofx.importStylesheet

var rootCategory = PhotoCategory("root")

class MyApp : App(MainView::class) {
    init {
        importStylesheet(Styles::class)
        rootCategory.children.add(PhotoCategory("directory"))
        rootCategory.children.add(PhotoCategory("tags"))
        rootCategory.children.add(PhotoCategory("alphabet"))
        startScan("./src/main/resources", rootCategory.children[0])

        val gson = Gson()
        val string = gson.toJson(rootCategory)
        val x = gson.fromJson(string, PhotoCategory::class.java)
        println(x)
    }
}
