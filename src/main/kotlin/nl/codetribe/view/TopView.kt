package nl.codetribe.view

import javafx.scene.Parent
import nl.codetribe.controller.TestImageController
import tornadofx.*

/**
 * Created by ronsmits on 24/08/16.
 */

class TopView : View() {
    val controller : TestImageController by inject()
    override val root = vbox {
        menubar {
            menu("File") {
                menuitem("Scan") {controller.scan()}
                menuitem("Load") {controller.load()}
                menuitem("Save") { controller.save()}
                menuitem("Quit"){
                    System.exit(0)
                }
            }
        }
    }
}