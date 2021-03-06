package nl.codetribe.model

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.Tag
import tornadofx.*
import java.io.File

/**
 * Created by ron on 8/31/16.
 */
class PhotoModel : ItemViewModel<Photo>() {
    val metadata: Metadata? get() = if (isNotEmpty) ImageMetadataReader.readMetadata(File(filepath.value)) else null
    val tags: List<Tag>? get() = metadata?.directories?.flatMap { it.tags }
    val name = bind(Photo::name)
    val filepath = bind(Photo::filepath)
    val hash = bind(Photo::hash)
}