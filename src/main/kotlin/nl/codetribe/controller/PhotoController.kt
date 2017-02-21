package nl.codetribe.controller

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
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
        selectedPhoto.itemProperty.onChange { exifInformation() }
    }
    fun exifInformation() {
        taglist.remove(0, taglist.size)
        if(selectedPhoto.isNotEmpty) {
            val metadata: Metadata = ImageMetadataReader.readMetadata(File(selectedPhoto.filepath.value))

            taglist.setAll(metadata.directories.flatMap { it.tags })
        }
    }
}
