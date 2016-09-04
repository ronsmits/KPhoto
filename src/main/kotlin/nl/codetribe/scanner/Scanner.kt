package nl.codetribe.scanner

import nl.codetribe.directoryCategory
import nl.codetribe.model.Directory
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.security.DigestInputStream
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

/**
 * Created by ron on 8/21/16.
 */

fun startScan(directoryname: String, startCategory: Directory) {
    try {

        startScan(File(directoryname), startCategory)
        println("root is now ${rootCategory}")
    } catch (e: Exception) {
        println(e)
    }
    rootCategory.children.remove(directoryCategory)
    rootCategory.children.add(directoryCategory)
}

fun startScan(directory: File, parent: Directory) {
    val validExtension = listOf("jpg", "jpeg", "png", "gif")
    val category = findOrCreateDirectory(directory.name, directory.absolutePath)
    if (!parent.containsChild(directory.absolutePath))
        parent.children.add(category)

    directory.listFiles({ file -> file.isDirectory }).map {
        try {
            startScan(it, category)
        } catch (e: Exception) {
            println(e)
        }
    }
    directory.listFiles().
            filter { it.isFile }.
            filter { validExtension.contains(it.name.toLowerCase().substringAfterLast(".")) }.
            filterNot { category.containsPhoto(it.absolutePath) }.
            forEach { category.photolist.add(Photo(it.name, it.absolutePath)) }

    if (category.photolist.size == 0 && category.children.size == 0) {
        parent.children.remove(category)
    }
}

private fun findOrCreateDirectory(name: String, absolutePath: String): Directory {
    val found = findDirectory(directoryCategory, absolutePath)
    println("found is $found")
    if (found == null)
        return Directory(name = name, absolutePath = absolutePath)
    else
        return found
}

private var result: Directory? = null
private fun findDirectory(directory: Directory, absolutePath: String): Directory? {
    println(directory)
    var list = directory.children.filter { (it as Directory).absolutePath.equals(absolutePath) }

    if (list.isEmpty()) {
        directory.children.forEach {
            val result = findDirectory((it as Directory), absolutePath)
            if (result != null) return result
        }
    } else result = list[0] as Directory
    println("return ${nl.codetribe.scanner.result}")
    return result
}
