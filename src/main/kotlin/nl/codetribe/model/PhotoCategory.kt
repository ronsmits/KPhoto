package nl.codetribe.model

import tornadofx.observable

/**
 * Created by ron on 8/22/16.
 */

open class PhotoCategory(val name: String, val dropAllowed: Boolean = true) {
    val children = mutableListOf<PhotoCategory>().observable()
    val photolist = mutableListOf<Photo>()

    override fun toString() : String {
        return "$name ${photolist.size} $children"
    }
}

class Directory (val absolutePath: String, name:String) : PhotoCategory(name, false) {
    override fun toString(): String {
        return "$absolutePath ${super.toString()}"
    }
    fun containsPhoto(absolutePath: String):Boolean {
        return photolist.filter { it.filepath.equals(absolutePath) }.isNotEmpty()
    }

    fun containsChild(absolutePath: String) : Boolean {
        return children.filter { (it as Directory).absolutePath.equals(absolutePath)}.isNotEmpty()
    }
}