package nl.codetribe.model

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.Tag
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.ViewModel
import java.io.File

/**
 * Created by ron on 8/31/16.
 */
class PhotoModel : ItemViewModel<Photo>() {
    val metadata: Metadata? get() = if (isNotEmpty) ImageMetadataReader.readMetadata(File(filepath.value)) else null
    val tags: List<Tag>? get() = metadata?.directories?.flatMap { it.tags }
    val name = bind { SimpleStringProperty(item?.name) }
    val filepath = bind { SimpleStringProperty(item?.filepath) }
}