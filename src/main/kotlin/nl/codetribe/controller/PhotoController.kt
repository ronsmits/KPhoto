package nl.codetribe.controller

import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoModel
import nl.codetribe.rootCategory
import tornadofx.Controller

/**
 * Created by ron on 8/20/16.
 */

class PhotoController : Controller() {

    val selectedPhoto = PhotoModel(Photo("", ""))

}
