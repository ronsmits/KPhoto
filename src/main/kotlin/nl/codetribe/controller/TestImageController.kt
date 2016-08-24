package nl.codetribe.controller

import nl.codetribe.model.Photo
import nl.codetribe.rootCategory
import tornadofx.Controller

/**
 * Created by ron on 8/20/16.
 */

class TestImageController : Controller() {

    val list = listOf(
            Photo("1", "thumbnail.wallhaven-6409.jpg"),
            Photo("2", "thumbnail.wallhaven-6461.jpg"),
            Photo("3", "thumbnail.wallhaven-6525.jpg"),
            Photo("4", "thumbnail.wallhaven-32808.jpg")
    )

    fun getPhotoList() = rootCategory.children[0].photolist
}
