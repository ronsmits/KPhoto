package nl.codetribe.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

/**
 * Created by ron on 8/22/16.
 */

open class PhotoCategory() : JsonModel {
    val dropAllowedProperty = SimpleBooleanProperty(true)
    var dropAllowed by dropAllowedProperty
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val childrenProperty = SimpleObjectProperty(mutableListOf<PhotoCategory>().observable())
    var children by childrenProperty

    val photolistProperty = SimpleObjectProperty(mutableListOf<Photo>().observable())
    var photolist by photolistProperty


    override fun toString(): String {
        return "$name ${photolist.size} $children"
    }

    //constructor()
    constructor(name: String, dropAllowed: Boolean = true) : this() {
        this.name = name
        this.dropAllowed = dropAllowed
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("dropAllowed", dropAllowed)
            println(println("children is $children"))
            if (children != null && children.isNotEmpty()) add("children", children.toJSON())
            add("photolist", photolist.toJSON())
        }
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            println(json.toPrettyString())
            name = json.getString("name")
            dropAllowed = json.getBoolean("dropAllowed")
            children = json.getJsonArray("children")?.toModel()
            photolist = json.getJsonArray("photolist")?.toModel()
        }
    }
}

class Directory(name: String) : PhotoCategory(name, false) {
    val absolutePathProperty = SimpleStringProperty()
    var absolutePath: String by absolutePathProperty

    constructor(name: String, absolutePath: String) : this(name) {
        this.absolutePath = absolutePath
    }

    override fun toString(): String {
        return "$absolutePath ${super.toString()}"
    }

    fun containsPhoto(absolutePath: String): Boolean {
        return photolist.filter { it.filepath == absolutePath }.isNotEmpty()
    }

    fun containsChild(absolutePath: String): Boolean {
        return children.filter { (it as Directory).absolutePath == absolutePath }.isNotEmpty()
    }
}