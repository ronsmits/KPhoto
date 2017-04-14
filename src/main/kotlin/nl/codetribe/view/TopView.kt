package nl.codetribe.view

import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import nl.codetribe.directoryCategory
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import nl.codetribe.scanner.startScan
import nl.codetribe.tag
import tornadofx.*
import java.io.FileOutputStream
import javax.json.JsonStructure

/**
 * Created by ronsmits on 02/09/16.
 */
class TopView : View() {
    //    val detailView: DetailView by inject()
    val categoryTreeView: CategoryTreeView by inject()
    override val root = vbox {
        menubar {
            menu("File") {
                isUseSystemMenuBar = true
                menuitem("_scan") { scanDirectoryAction() }
                menuitem("duplicates") { duplicateAction() }
                separator()
                menuitem("load")
                menuitem("save as").action {
                    val result = chooseFile(title = "save as", mode = FileChooserMode.Save, filters = arrayOf(FileChooser.ExtensionFilter("json file", "*.json")))
                    if (result.isNotEmpty()) {
                        val filename = if (!result.first().endsWith(".json")) "${result.first()}.json" else result.first().toString()
                        val tosave: JsonStructure = tag.toJSON()
                        tosave.save(FileOutputStream(filename))
                    }
                }

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
