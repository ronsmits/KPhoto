package nl.codetribe.scanner

import org.junit.Assert
import org.junit.Test
import java.io.File

class DhashKtTest {

    @Test
    fun dhash() {
        val file = File("./src/main/resources/nl/codetribe/view")
        println(file.absolutePath)
        println(file.isDirectory)
        val left = dhash("./src/main/resources/thumbnail.wallhaven-6409.jpg")
        Assert.assertEquals("980b062a5a4d4", left)
        val right = dhash("./src/main/resources/nl/codetribe/view/thumbnail.wallhaven-6409-1.jpg")
        Assert.assertEquals("980b062a5a4d4", right)
    }

    @Test
    fun hammingdistanceEqualStringsTest() {
        val left = "980b062a5a4d4"
        var right = "980b062a5a4d4"
        Assert.assertEquals(0, hammingDistance(left, right))
    }

    @Test
    fun hammingdistanceOnecharDifference() {
        val left = "12345"
        val right = "12344"
        Assert.assertEquals(1, hammingDistance(left, right))
    }

    @Test
    fun baseAndGrey() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val grey = dhash("./src/test/resources/testSimilarityGray.jpg")
        Assert.assertEquals(0, hammingDistance(base, grey))
    }

    @Test
    fun baseAndDithered() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val dithered = dhash("./src/test/resources/testSimilarityDithered.jpg")
        println(hammingDistance(base, dithered))
    }

    @Test
    fun baseAndWatermarked() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val right = dhash("./src/test/resources/testSimilarityWatermarked.jpg")
        println(hammingDistance(base, right))
    }

    @Test
    fun baseAndDifferent() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val right = dhash("./src/test/resources/testSimilarityDiff.jpg")
        println(hammingDistance(base, right))
    }
}