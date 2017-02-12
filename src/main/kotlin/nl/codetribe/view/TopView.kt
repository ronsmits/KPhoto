package nl.codetribe.view

import javafx.stage.DirectoryChooser
import nl.codetribe.directoryCategory
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import nl.codetribe.scanner.startScan
import tornadofx.*

/**
 * Created by ronsmits on 02/09/16.
 */
class TopView : View() {
    val detailView: DetailView by inject()
    val categoryTreeView: CategoryTreeView by inject()
    override val root = vbox {
        menubar {
            menu("File") {
                isUseSystemMenuBar = true
                menuitem("_scan") { scanDirectoryAction() }
                menuitem("duplicates") { duplicateAction() }
                separator()
                menuitem("load")
                menuitem("save")
                separator()
                menuitem("quit") {
                    System.exit(0)
                }
            }
        }
    }

    private fun duplicateAction() {
        if (rootCategory.children.filter { it.name == "duplicates" }.isEmpty()) {
            with(PhotoCategory("duplicates", dropAllowed = true)) {
                rootCategory.children.add(this)
            }
            startLookingForDuplicates()
        }
    }

    private fun scanDirectoryAction() {
        val selectedFile = DirectoryChooser().showDialog(null)
        runAsync {
            if (selectedFile != null) {
                startScan(selectedFile, directoryCategory)
            }
        } ui {
            categoryTreeView.root.root.children.forEach { it.value.name.equals("directories") }
            rootCategory.children.remove(directoryCategory)
            rootCategory.children.add(directoryCategory)
        }
    }
}
