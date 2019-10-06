package eu.micer.circlesloadingindicator

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.doOnTextChanged
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var circlesCount = 8
    private var rotationDuration = 3000
    private var circlesRadius = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateCirclesNumber()

        // Circles count
        btn_circles_count_minus.setOnClickListener {
            if (circlesCount > 1) {
                circlesCount--
                et_circles_count.setText(circlesCount.toString())
            }
        }

        btn_circles_count_plus.setOnClickListener {
            if (circlesCount < 99) {
                circlesCount++
                et_circles_count.setText(circlesCount.toString())
            }
        }

        et_circles_count.doOnTextChanged { text, _, _, _ ->
            try {
                circlesCount = text.toString().toInt()
                updateCirclesNumber()
            } catch (ex: NumberFormatException) {
                Log.e("MainActivity", ex.message ?: "")
            }
        }

        // Rotation duration
        btn_duration_minus.setOnClickListener {
            if (rotationDuration > 200) {
                rotationDuration -= rotationDuration % 200
                if (rotationDuration > 200) {
                    rotationDuration -= 200
                }
                et_rotation_duration.setText(rotationDuration.toString())
            }
        }

        btn_duration_plus.setOnClickListener {
            if (rotationDuration < 100_000) {
                rotationDuration += 200
                rotationDuration -= rotationDuration % 200
                et_rotation_duration.setText(rotationDuration.toString())
            }
        }

        et_rotation_duration.doOnTextChanged { text, _, _, _ ->
            try {
                rotationDuration = text.toString().toInt()
                if (rotationDuration < 200) rotationDuration = 200
                if (rotationDuration > 100_000) rotationDuration = 100_000
                updateRotationDuration()
            } catch (ex: NumberFormatException) {
                Log.e("MainActivity", ex.message ?: "")
            }
        }

        // Leading line
        cb_leading_line.setOnCheckedChangeListener { _, isChecked ->
            updateShowLeadingLine(isChecked)
        }

        // Circles radius
        btn_circles_radius_minus.setOnClickListener {
            if (circlesRadius > 1) {
                circlesRadius--
                et_circles_radius.setText(circlesRadius.toString())
            }
        }

        btn_circles_radius_plus.setOnClickListener {
            if (circlesRadius < 99) {
                circlesRadius++
                et_circles_radius.setText(circlesRadius.toString())
            }
        }

        et_circles_radius.doOnTextChanged { text, _, _, _ ->
            try {
                circlesRadius = text.toString().toInt()
                updateCirclesRadius()
            } catch (ex: NumberFormatException) {
                Log.e("MainActivity", ex.message ?: "")
            }
        }

        // Colors
        iv_color_brown.setOnClickListener {
            ImageViewCompat.getImageTintList(it as ImageView)?.defaultColor?.let { color ->
                updateColor(color)
            }
        }
        iv_color_deep_purple.setOnClickListener {
            ImageViewCompat.getImageTintList(it as ImageView)?.defaultColor?.let { color ->
                updateColor(color)
            }
        }
        iv_color_green.setOnClickListener {
            ImageViewCompat.getImageTintList(it as ImageView)?.defaultColor?.let { color ->
                updateColor(color)
            }
        }
        iv_color_light_blue.setOnClickListener {
            ImageViewCompat.getImageTintList(it as ImageView)?.defaultColor?.let { color ->
                updateColor(color)
            }
        }
        iv_color_red.setOnClickListener {
            ImageViewCompat.getImageTintList(it as ImageView)?.defaultColor?.let { color ->
                updateColor(color)
            }
        }
        iv_color_yellow.setOnClickListener {
            ImageViewCompat.getImageTintList(it as ImageView)?.defaultColor?.let { color ->
                updateColor(color)
            }
        }

        btn_reset.setOnClickListener {
            updateShowLeadingLine(LEADING_LINE_DEFAULT)
            cb_leading_line.isChecked = LEADING_LINE_DEFAULT

            circlesCount = CIRCLES_COUNT_DEFAULT
            updateCirclesNumber()
            et_circles_count.setText(CIRCLES_COUNT_DEFAULT.toString())

            circlesRadius = CIRCLES_RADIUS_DEFAULT
            updateCirclesRadius()
            et_circles_radius.setText(CIRCLES_RADIUS_DEFAULT.toString())

            rotationDuration = ROTATION_DURATION_DEFAULT
            updateRotationDuration()
            et_rotation_duration.setText(ROTATION_DURATION_DEFAULT.toString())

            iv_color_light_blue.performClick()
        }
    }

    private fun updateCirclesNumber() {
        circles_loading_indicator.circlesCount = circlesCount
        circles_loading_indicator.invalidate()
    }

    private fun updateShowLeadingLine(showLeadingLine: Boolean) {
        circles_loading_indicator.showLeadingLine = showLeadingLine
        circles_loading_indicator.invalidate()
    }

    private fun updateRotationDuration() {
        circles_loading_indicator.rotationDuration = rotationDuration
        circles_loading_indicator.clearAnimation()
        circles_loading_indicator.startAnimation()
    }

    private fun updateCirclesRadius() {
        circles_loading_indicator.circlesRadius = circlesRadius.dpToPx.toFloat()
        circles_loading_indicator.invalidate()
    }

    private fun updateColor(color: Int) {
        circles_loading_indicator.circlesColor = color
        circles_loading_indicator.invalidate()
    }

    companion object {
        private const val CIRCLES_COUNT_DEFAULT = 8
        private const val CIRCLES_RADIUS_DEFAULT = 4
        private const val LEADING_LINE_DEFAULT = false
        private const val ROTATION_DURATION_DEFAULT = 3000
    }
}
