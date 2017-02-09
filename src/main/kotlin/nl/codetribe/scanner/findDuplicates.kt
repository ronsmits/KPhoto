package nl.codetribe.scanner

import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import java.nio.file.Files
import java.nio.file.Paths
import java.security.DigestInputStream
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

private val photoList = mutableListOf<Photo>()

fun listOfPhotos(start: PhotoCategory) {

    photoList.addAll(start.photolist)
    start.children.forEach(::listOfPhotos)
}

fun findDoubles() {
    val duplicateSet = mutableSetOf<Photo>()
    photoList.forEach { outer ->
        photoList.forEach { inner ->
            if (outer.filepath != inner.filepath) {
                if (outer.md5 == inner.md5) {
                    duplicateSet.add(inner)
                    duplicateSet.add(outer)
                }
            }
        }
    }
    rootCategory.children.filter { it.name == "duplicates" }[0].photolist.addAll(duplicateSet)

}

fun buildMD5Strings() {
    photoList.forEach { it.md5 = MD5(it.filepath) }
}

fun MD5(file: String): String {
    val md = MessageDigest.getInstance("MD5")
    try {
        val fis = Files.newInputStream(Paths.get(file))
        val dis = DigestInputStream(fis, md)
        val buffer = ByteArray(4096)
        while (dis.read(buffer) != -1) {
        }
        fis.close()
        return DatatypeConverter.printHexBinary(md.digest())
    } catch (e: Exception) {
        println(e)
    }
    return ""
}