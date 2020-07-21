fun main() {

    var fraction1 = Fraction(2.0F, 3.0F)
    var fraction2 = Fraction(4.0F, 7.0F)
    println(fraction1 == fraction2)

    var fraction3 = Fraction(2.0F, 3.0F)
    var fraction4 = Fraction(4.0F, 6.0F)
    println(fraction3.equals(fraction4))

    println(fraction1)

    var rectangle = RectangleBox("Black", 2.0F, 3.0F, 4.0F)
    println(rectangle.getPerimeter())
    println(rectangle.getSurfaceaArea())
    println(rectangle.getFigureInfo())

    var s: I3DDrawing = RectangleBox("Black", 2.0F, 3.0F, 4.0F)

    var k: Figure = RectangleBox("Black", 2.0F, 3.0F, 4.0F)

    var o: Any = Fraction(2.0F, 3.0F)
    o.toString()

    var i = object : I3DDrawing {

        override val drawingSpeed: Int
            get() = TODO("not implemented")

        override fun drawSurface() {
            TODO("not implemented")
        }

    }

}

class Fraction(numerator: Float, denominator: Float) {

    var numerator: Float = numerator
    var denominator: Float = denominator

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Fraction)
            if (numerator * other.denominator / denominator == other.numerator)
                return true
        return false
    }

}

abstract class Figure(color: String) {

    // შეგვიძლია open დავუწეროთ მხოლოდ set protected ან public უნდა იყოს
    var color: String = color
        private set

    abstract fun getArea(): Float
    abstract fun getPerimeter(): Float

    fun getFigureInfo() {
        println("Perimeter - ${getPerimeter()}; Area - ${getArea()};")
    }

    open fun draw() {
        println("Drawing color - $color")
    }

}

open class Rectangle(color: String, protected val width: Float, protected val height: Float) : Figure(color) {

    open var numberOfPoints: Int = 4

    override fun getArea(): Float {
        return width * height
    }

    override fun getPerimeter(): Float {
        return 2 * (width + height)
    }

    override fun draw() {
        super.draw()
        println("Rectangle is drawing!")
    }

}

// ბირთვი
class Circle(color: String, private val radius: Int) : Figure(color) {

    private val PI: Float = 3.14F

    override fun getArea(): Float {
        return PI * radius * radius
    }

    override fun getPerimeter(): Float {
        return 2 * PI * radius
    }

}

// პარალელეპიპედი
class RectangleBox(color: String, width: Float, height: Float, private val length: Float) :
        Rectangle(color, width, height), I3DDrawing {

    private var isPaintedSurface: Boolean = false

    override fun getArea(): Float {
        return super.getArea() * length
    }

    override fun getPerimeter(): Float {
        return super.getPerimeter() * 2 + (4 * length)
    }

    fun getSurfaceaArea(): Float {
        return 2 * (width * height + width * length + height * length)
    }

    override fun draw() {
        println("Drawing color - $color")
        println("RectangleBox is drawing!")
    }

    override val drawingSpeed: Int
        get() = 20

    override fun drawSurface() {
        isPaintedSurface = true
        println("RectangleBox Surface is drawing!")
    }

    override val surfaceColor: String
        get() = "Green"

    override fun isSurfacePainted(): Boolean {
        return isPaintedSurface
    }

}

interface I3DDrawing {

    val drawingSpeed: Int
    val surfaceColor: String
        get() = "Red"

    fun drawSurface()
    fun isSurfacePainted(): Boolean {
        return false
    }

}