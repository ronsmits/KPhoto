package nl.codetribe.model

import tornadofx.JsonBuilder
import java.net.URL
import java.nio.file.Paths

/**
 * Created by ron on 8/20/16.
 */

class Photo(val name: String, val filepath: String)  {
    fun toJSON(json: JsonBuilder) {
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
