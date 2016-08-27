package nl.codetribe.model

import tornadofx.observable

/**
 * Created by ron on 8/22/16.
 */

class PhotoCategory(val name: String, val dropAllowed: Boolean = true) {
    val children = mutableListOf<PhotoCategory>().observable()
    val photolist = mutableListOf<Photo>()

    override fun toString() : String {
        return "$name $children ${photolist.size}\n"
    }
}
