package nl.codetribe.scanner

import nl.codetribe.model.Photo
import nl.codetribe.model.PhotoCategory
import nl.codetribe.rootCategory
import java.io.File

/**
 * Created by ron on 8/21/16.
 */

fun startScan(directoryname: String) {
    try {

        startScan(File(directoryname), rootCategory)
    } catch (e: Exception){
        println(e)
    }
}

fun startScan(directory: File, parent : PhotoCategory) {
    val category = PhotoCategory(name = directory.name)
    directory.listFiles({ file -> file.isDirectory }).map {
        try {startScan(it, category)} catch (e: Exception){
        println(e)}
    }
    directory.listFiles({
        file ->
        file.isFile && (file.name.toLowerCase().endsWith(".jpg")
                || file.name.toLowerCase().endsWith("jpeg")
                || file.name.toLowerCase().endsWith("png")
                || file.name.toLowerCase().endsWith("gif"))
    }).forEach { category.photolist.add(Photo(it.name, it.absolutePath)) }
    if(category.photolist.size>0 || category.children.size>0) parent.children.add(category)
}
