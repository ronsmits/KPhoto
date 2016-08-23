package nl.codetribe.scanner

import nl.codetribe.categories
import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import java.io.File

/**
 * Created by ron on 8/21/16.
 */

fun startScan(directoryname: String) {
    try {

        startScan(File(directoryname))
    } catch (e: Exception){
        println(e)
    }
}

fun startScan(directory: File) {
    val category = PhotoCategory(name = directory.absolutePath)
    categories.add(category)
    directory.listFiles({ file -> file.isDirectory }).map {
        try {startScan(it)} catch (e: Exception){
        println(e)}
    }
    directory.listFiles({
        file ->
        file.isFile && (file.name.toLowerCase().endsWith(".jpg")
                || file.name.toLowerCase().endsWith("jpeg")
                || file.name.toLowerCase().endsWith("png")
                || file.name.toLowerCase().endsWith("gif"))
    }).forEach { category.photolist.add(Photo(it.name, it.absolutePath)) }
}
