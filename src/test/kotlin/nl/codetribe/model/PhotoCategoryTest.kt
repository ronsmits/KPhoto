package nl.codetribe.model

import org.junit.Test
import tornadofx.*
import java.io.FileOutputStream
import java.nio.file.Paths
import javax.json.JsonStructure

/**
 * Created by ronsmits on 04/04/2017.
 */
class PhotoCategoryTest {

    @Test fun testCategories() {
        val parent = PhotoCategory("top");
        parent.children.add(PhotoCategory("child 1"))
        parent.children[0].children.add(PhotoCategory("child 1.1"))
        parent.children.add(PhotoCategory("child 2"))

        parent.children[0].photolist.add(Photo("naam", "/tymp/file"))
        println(parent)


        val message = parent.toJSON().toPrettyString()
        val toJSON: JsonStructure = parent.toJSON()
        println(message)
        val temp: PhotoCategory = loadJsonModel(message)
        println(temp)

        toJSON.save(FileOutputStream("temp.json"))

        val jsonModel = loadJsonModel<PhotoCategory>(Paths.get("temp.json"))
        println(jsonModel.toJSON().toPrettyString())

    }
}