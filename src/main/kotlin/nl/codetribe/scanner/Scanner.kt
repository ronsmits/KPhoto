package nl.codetribe.scanner

import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.security.DigestInputStream
import java.security.MessageDigest
import java.util.*
import javax.xml.bind.DatatypeConverter

/**
 * Created by ron on 8/21/16.
 */

fun startScan(directoryname: String, startCategory: PhotoCategory) {
    try {

        startScan(File(directoryname), startCategory)
    } catch (e: Exception) {
        println(e)
    }
}

fun startScan(directory: File, parent: PhotoCategory) {
    val category = PhotoCategory(name = directory.name)
    directory.listFiles({ file -> file.isDirectory }).map {
        try {
            startScan(it, category)
        } catch (e: Exception) {
            println(e)
        }
    }
    directory.listFiles({
        file ->
        file.isFile && (file.name.toLowerCase().endsWith(".jpg")
                || file.name.toLowerCase().endsWith("jpeg")
                || file.name.toLowerCase().endsWith("png")
                || file.name.toLowerCase().endsWith("gif"))
    }).forEach { category.photolist.add(Photo(it.name, it.absolutePath)) }

    if (category.photolist.size != 0 || category.children.size != 0) {
        parent.children.add(category)
    }
}

private val photoList = mutableListOf<Photo>()

fun listOfPhotos(start: PhotoCategory) {

    photoList.addAll(start.photolist)
    start.children.forEach { listOfPhotos(it) }
}

fun findDoubles() {
    val duplicateSet = mutableSetOf<Photo>()
    println("called")
    photoList.forEach { outer ->
        photoList.forEach { inner ->
            if(!outer.filepath.equals(inner.filepath)){
                if(outer.md5.equals(inner.md5)){
                    println("same file $inner $outer")
                    duplicateSet.add(inner)
                    duplicateSet.add(outer)
                }
            }
        }
    }
    println(duplicateSet.size)
    rootCategory.children.filter { it.name.equals("duplicates") }[0].photolist.addAll(duplicateSet)

}
fun buildMD5Strings() {
    photoList.forEach { it.md5 = MD5(it.filepath) }
}

fun MD5(file: String) : String {
    val md = MessageDigest.getInstance("MD5")
    try {
        val fis = Files.newInputStream(Paths.get(file))
        val dis = DigestInputStream(fis, md)
        val buffer = ByteArray(4096)
        while (dis.read(buffer) != -1) { }
        fis.close()
        return DatatypeConverter.printHexBinary(md.digest())
    } catch (e: Exception) {
        println(e)
    }
    return ""
}