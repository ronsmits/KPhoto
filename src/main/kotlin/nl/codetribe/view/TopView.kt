package nl.codetribe.view

import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import nl.codetribe.controller.PhotoController
import nl.codetribe.directoryCategory
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import nl.codetribe.scanner.startScan
import tornadofx.*
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Created by ronsmits on 02/09/16.
 */
class TopView : View() {
    //    val detailView: DetailView by inject()
    val categoryTreeView: CategoryTreeView by inject()
    val controller: PhotoController by inject()

    val taskStatus: TaskStatus by inject()
    private val arrayOfExtensionFilters = arrayOf(FileChooser.ExtensionFilter("json file", "*.json"))

    override val root = hbox {
        menubar {
            menu("File") {
                //                isUseSystemMenuBar = true
                item("_scan").action { scanDirectoryAction() }
                item("duplicates").action { duplicateAction() }
                separator()
                item("load").action {
                    val result = chooseFile(title = "load tags file", mode = FileChooserMode.Single, filters = arrayOfExtensionFilters)
                    if (result.isNotEmpty()) {
                        val loaded = loadJsonObject(FileInputStream(result[0]))
                        rootCategory.updateModel(loaded)
                    }
                }
                item("save catalog as").action {
                    val result = chooseFile(title = "save catalog as", mode = FileChooserMode.Save, filters = arrayOfExtensionFilters)
                    if (result.isNotEmpty()) {
                        rootCategory.toJSON().save(FileOutputStream(result.first()))
                    }
                }

                separator()
                item("quit").action {
                    System.exit(0)
                }

            }
        }
        progressbar(taskStatus.progress) {
            visibleWhen { taskStatus.running }
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
            categoryTreeView.root.root.children.forEach { it.value.name == "directories" }
            rootCategory.children.remove(directoryCategory)
            rootCategory.children.add(directoryCategory)
        }
    }
}
