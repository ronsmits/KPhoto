package nl.codetribe.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.io.Serializable
import java.net.URL
import java.nio.file.Paths

/**
 * Created by ron on 8/20/16.
 */

class Photo() : Serializable, JsonModel {
    val filepathProperty = SimpleStringProperty()
    var filepath by filepathProperty

    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    constructor(name: String, filepath: String) : this() {
        this.name = name
        this.filepath = filepath
    }

    lateinit var md5 : String
    override fun toJSON(json: JsonBuilder) {
        with(json){
            add("name", name)
            add("file", filepath)
        }
    }

    override fun toString() : String {
        return "\t$name - $filepath\n"
    }

    fun toURL(): URL {
        return Paths.get(filepath).toUri().toURL()
    }

}
