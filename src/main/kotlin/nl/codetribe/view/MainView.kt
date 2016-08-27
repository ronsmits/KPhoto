package nl.codetribe.view

import javafx.scene.control.ScrollPane
import javafx.scene.control.TreeItem
import javafx.scene.layout.BorderPane
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
    val topview : TopView by inject()

    init {
        with(root) {
            top = topview.root
            left = categoryview.root
            center = vbox {
                prefWidth = 800.0
                scrollpane {
                    prefWidth = 600.0
                    hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                    vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
                    isFitToWidth = true
                    isFitToHeight = true
                    add(imageView.root)
                }
            }
        }
    }
}

class TopView : View(){
    val categoryTreeView : CategoryTreeView by inject()
    override val root = vbox {
        menubar {
            menu("File") {
                isUseSystemMenuBar=true
                menuitem("scan")
                menuitem("duplicates"){
                    if(rootCategory.children.filter { it.name=="duplicates" }.isEmpty()) {
                        println("creating duplicates category")
                        with(PhotoCategory("duplicates", dropAllowed = true)) {
                            rootCategory.children.add(this)
                            categoryTreeView.root.root.children.add(TreeItem(this))
                        }
                        startLookingForDuplicates()
                    }
                }
                separator()
                menuitem("load")
                menuitem("save")
                separator()
                menuitem("quit") {
                    System.exit(0)
                }
            }
        }
        label("top")
    }

}


fun startLookingForDuplicates() {
    listOfPhotos(rootCategory)
    buildMD5Strings()
    findDoubles()
}