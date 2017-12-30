package nl.codetribe.scanner

import org.junit.Assert
import org.junit.Test

class DhashKtTest {


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
        Assert.assertEquals(0, hammingDistance(base, dithered))
    }

    @Test
    fun baseAndWatermarked() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val right = dhash("./src/test/resources/testSimilarityWatermarked.jpg")
        Assert.assertEquals(1, hammingDistance(base, right))
    }

    @Test
    fun baseAndCropped() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val right = dhash("./src/test/resources/testSimilarityCropped.jpg")
        println(hammingDistance(base, right))
    }

    @Test
    fun baseAndDifferent() {
        val base = dhash("./src/test/resources/testSimilarityBase.jpg")
        val right = dhash("./src/test/resources/testSimilarityDiff.jpg")
        println(hammingDistance(base, right))
    }
}