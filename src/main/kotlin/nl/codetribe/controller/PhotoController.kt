package nl.codetribe.controller

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Tag
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoModel
import nl.codetribe.rootCategory
import tornadofx.Controller
import tornadofx.onChange
import java.io.File

/**
 * Created by ron on 8/20/16.
 */

class PhotoController : Controller() {
    val taglist : ObservableList<Tag> = FXCollections.observableArrayList()
    val selectedPhoto = PhotoModel()
    init {
        selectedPhoto.itemProperty.onChange { exifInformation(selectedPhoto) }
    }
    fun exifInformation(selectedPhoto: PhotoModel) {
        taglist.remove(0, taglist.size)
        if(selectedPhoto.itemProperty.isNotNull.value) {
            val metadata = ImageMetadataReader.readMetadata(File(selectedPhoto.filepath.value))

            metadata.directories.forEach {
                it.tags.forEach {
                    taglist.add(it)
                }
            }
        }
    }
}
