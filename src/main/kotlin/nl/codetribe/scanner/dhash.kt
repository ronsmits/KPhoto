package nl.codetribe.scanner

import net.coobird.thumbnailator.Thumbnails
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.math.BigInteger
import javax.imageio.ImageIO

private val scaleSize = 8
fun dhash(absolutePath: String): String {
    return dhash(ImageIO.read(File(absolutePath)))
}

fun dhash(sourceimage: BufferedImage): String {

    val image = Thumbnails.of(sourceimage).size(scaleSize + 1, scaleSize).keepAspectRatio(false).outputQuality(1.0).asBufferedImage()
    val testImage = BufferedImage(image.width, image.height, image.type)
    var result = BigInteger("0")
    var count = 0
    (0 until scaleSize).forEach { y ->
        (0 until scaleSize).forEach { x ->
            val greyleft = toGrey(image.getRGB(x, y))
            testImage.setRGB(x, y, toGrey(image.getRGB(x, y)))
            val greyright = toGrey(image.getRGB(x + 1, y))

            if (greyleft > greyright) {
                val shifted = BigInteger("1").shiftLeft(count)
                result = result.or(shifted)
            }
            count++
        }
    }

    ImageIO.write(testImage, "png", File("testfile.png"))
    return String.format("%x", result)
}


fun hammingDistance(left: String, right: String): Int {
    if (left.length != right.length)
        return Int.MAX_VALUE
    var diff = 0
    (0 until left.length).forEach { i ->
        if (left[i] != right[i])
            diff++
    }
    return diff
}

fun toGrey(rgb: Int): Int {
    val r = (rgb shr 16) and 0xff
    val g = (rgb shr 8) and 0xff
    val b = rgb and 0xff
    val grey = (r + g + b) / 3
    return grey
}

fun scaleto9(original: BufferedImage): BufferedImage {
    val resized = BufferedImage(scaleSize + 1, scaleSize, original.type)
    with(resized.createGraphics()) {
        setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
        drawImage(original, 0, 0, scaleSize + 1, scaleSize, 0, 0, original.width, original.height, null)
        dispose()
    }
    return resized
}