package nl.codetribe.model

import org.junit.Test
import tornadofx.*

/**
 * Created by ronsmits on 04/04/2017.
 */
class PhotoCategoryTest {

    @Test fun testCategories() {
        val parent = PhotoCategory("top");
        parent.children.add(PhotoCategory("child 1"))
        parent.children[0].children.add(PhotoCategory("child 1.1"))
        parent.children.add(PhotoCategory("child 2"))

        println(parent)

//        val builder = JsonBuilder()
//        parent.toJSON(builder)
//        println(builder.build().toPrettyString())

        val message = parent.toJSON().toPrettyString()
        println(message)
        val temp: PhotoCategory = loadJsonModel(message)
        println(temp)
    }
}