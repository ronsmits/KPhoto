package nl.codetribe.controller

import nl.codetribe.categories
import nl.codetribe.model.Photo
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

    fun getPhotoList() = categories[0].photolist

    fun getAllPhotos(): MutableList<Photo> {
        val list = mutableListOf<Photo>()
        categories.map { list.addAll(it.photolist) }
        println(list.size)
        return list
    }
}
