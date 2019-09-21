package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity() {

    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {

                val angleDeg1 = 0.0
                circle1.startAnimation(getBallAnimation(angleDeg1))

                val circlesList = arrayListOf(
                    circle2,
                    circle3,
                    circle4,
                    circle5,
                    circle6
                )

            circlesList.forEachIndexed { index, circle ->
                val angleDeg = (index + 1) * 30.0
                Handler().postDelayed({
                    circle.startAnimation(getBallAnimation(angleDeg))
                }, DELAY_COEFFICIENT * angleDeg.toLong())
            }


//                val angleDeg2 = 30.0
//                Handler().postDelayed({
//                    circle2.startAnimation(getBallAnimation(angleDeg2))
//                }, delayCoefficient * angleDeg2.toLong())
//
//                val angleDeg3 = 60.0
//                val subscribe2 = Completable.timer(
//                    delayCoefficient * angleDeg3.toLong(),
//                    TimeUnit.MICROSECONDS,
//                    Schedulers.computation()
//                )
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        circle3.startAnimation(getBallAnimation(angleDeg3))
//                    }
//
//                val angleDeg4 = 90.0
//                val subscribe3 = Completable.timer(
//                    delayCoefficient * angleDeg4.toLong(),
//                    TimeUnit.MICROSECONDS,
//                    Schedulers.computation()
//                )
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        circle4.startAnimation(getBallAnimation(angleDeg4))
//                    }
//
//                val angleDeg5 = 120.0
//                val subscribe4 = Completable.timer(
//                    delayCoefficient * angleDeg5.toLong(),
//                    TimeUnit.MICROSECONDS,
//                    Schedulers.computation()
//                )
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        circle5.startAnimation(getBallAnimation(angleDeg5))
//                    }
//
//                val angleDeg6 = 150.0
//                val subscribe5 = Completable.timer(
//                    delayCoefficient * angleDeg6.toLong(),
//                    TimeUnit.MICROSECONDS,
//                    Schedulers.computation()
//                )
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        circle6.startAnimation(getBallAnimation(angleDeg6))
//                    }
//==================================
//            val angleDeg7 = 120.0
//            Completable.timer(delayCoefficient * angleDeg7.toLong(), TimeUnit.MICROSECONDS, Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    circle7.startAnimation(getBallAnimation(angleDeg7))
//                }
//
//            val angleDeg8 = 140.0
//            Completable.timer(delayCoefficient * angleDeg8.toLong(), TimeUnit.MICROSECONDS, Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    circle8.startAnimation(getBallAnimation(angleDeg8))
//                }
//
//            val angleDeg9 = 160.0
//            Completable.timer(delayCoefficient * angleDeg9.toLong(), TimeUnit.MICROSECONDS, Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    circle9.startAnimation(getBallAnimation(angleDeg9))
//                }

//            circle2.animate().translationXBy(xDelta).duration = 1000
//            circle2.animate().translationYBy(yDelta).duration = 1000
//            }
//            isRunning = !isRunning
        }
    }

    private fun getBallAnimation(angleDeg: Double) : TranslateAnimation {
        val circleRadius = 36.toPx()
        val angle = Math.toRadians(angleDeg)

        val xDelta = 2 * (-(circleRadius * sin(angle))).toFloat()
        val yDelta = 2 * (circleRadius * cos(angle)).toFloat()

        val animation = TranslateAnimation(0f, xDelta, 0f, yDelta)
        animation.interpolator = AccelerateDecelerateInterpolator()
        animation.duration = ANIMATION_DURATION
        animation.repeatCount = 100
        animation.repeatMode = TranslateAnimation.REVERSE
//        animation.startOffset = (3 * angleDeg).toLong()

        return animation
    }

    companion object {
        // ~ 54 delay duration when animation duration 10000 ms
        private const val DELAY_COEFFICIENT = 54
        private const val ANIMATION_DURATION : Long = 10_000
    }
}
