package nl.codetribe.scanner

import nl.codetribe.directoryCategory
import nl.codetribe.model.Directory
import nl.codetribe.model.Photo
import java.io.File

/**
 * Created by ron on 8/21/16.
 */

fun startScan(directoryname: String, startCategory: Directory) {
    try {

        startScan(File(directoryname), startCategory)
    } catch (e: Exception) {
        println(e)
    }
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
    if (found == null)
        return Directory(name = name, absolutePath = absolutePath)
    else
        return found
}

private var result: Directory? = null
private fun findDirectory(directory: Directory, absolutePath: String): Directory? {
    val list = directory.children.filter { (it as Directory).absolutePath == absolutePath }

    if (list.isEmpty()) {
        directory.children.forEach {
            val result = findDirectory((it as Directory), absolutePath)
            if (result != null) return result
        }
    } else result = list[0] as Directory
    return result
}
