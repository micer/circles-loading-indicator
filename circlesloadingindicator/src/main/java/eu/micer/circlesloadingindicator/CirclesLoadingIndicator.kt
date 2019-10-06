package eu.micer.circlesloadingindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import kotlin.math.cos
import kotlin.math.sin

class CirclesLoadingIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var circlesColor: Int
    var circlesCount: Int
    var circlesRadius: Float
    var rotationDuration: Int
    var showLeadingLine: Boolean

    private lateinit var firstPaint: Paint
    private lateinit var secondPaint: Paint

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CirclesLoadingIndicator,
            0, 0
        ).apply {

            try {
                circlesCount = getInt(R.styleable.CirclesLoadingIndicator_circlesCount, 6)
                circlesRadius = getDimension(R.styleable.CirclesLoadingIndicator_circlesRadius, 16f)
                circlesColor =
                    getColor(R.styleable.CirclesLoadingIndicator_circlesColor, Color.BLACK)
                rotationDuration =
                    getInt(R.styleable.CirclesLoadingIndicator_rotationDuration, 3000)
                showLeadingLine =
                    getBoolean(R.styleable.CirclesLoadingIndicator_showLeadingLine, false)
            } finally {
                recycle()
            }
        }

        startAnimation()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        firstPaint = Paint()
        firstPaint.apply {
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }
        secondPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        secondPaint.apply {
            color = circlesColor
        }

        canvas?.let {
            val startAngle = -Math.PI / 2f
            val centerX = width / 2
            val centerY = height / 2

            if (showLeadingLine) {
                canvas.drawCircle(
                    centerX.toFloat(),
                    centerY.toFloat(),
                    width / 2f - circlesRadius,
                    firstPaint
                )
            }

            for (i in 0 until circlesCount) {
                val angle = if (i == 0) startAngle
                else startAngle + (i * ((2 * Math.PI) / circlesCount))

                val x = centerX + cos(angle) * (width / 2f - circlesRadius)
                val y = centerY + sin(angle) * (width / 2f - circlesRadius)

                canvas.drawCircle(x.toFloat(), y.toFloat(), circlesRadius, secondPaint)
            }
        }
    }

    fun startAnimation() {
        val rotateAnimation = RotateAnimation(
            0f,
            359f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = rotationDuration.toLong()
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
        }
        startAnimation(rotateAnimation)
    }
}