package com.app.testpexel.presentation.photo_detail

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.app.testpexel.R
import com.app.testpexel.base.BaseActivity
import com.app.testpexel.data.model.DataPhoto
import com.app.testpexel.databinding.ActivityPhotoDetailBinding
import com.app.testpexel.utils.GlideEngine
import com.app.testpexel.utils.viewBindings

class PhotoDetailActivity :
    BaseActivity<ActivityPhotoDetailBinding>(R.layout.activity_photo_detail) {

    companion object {
        const val DATA_PHOTO = "DATA_PHOTO"
    }

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    private var lastX = 0f
    private var lastY = 0f
    private var dx = 0f
    private var dy = 0f
    private var isDragging = false

    private var dataPhoto: DataPhoto? = null

    override val binding: ActivityPhotoDetailBinding by viewBindings(ActivityPhotoDetailBinding::bind)

    override fun setupViews() {
        scaleGestureDetector = ScaleGestureDetector(
            this,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = scaleFactor.coerceIn(1.0f, 4.0f)
                    binding.ivPhoto.scaleX = scaleFactor
                    binding.ivPhoto.scaleY = scaleFactor
                    return true
                }
            })
        if (intent.hasExtra(DATA_PHOTO)) {
            dataPhoto = intent.getParcelableExtra(DATA_PHOTO)
            GlideEngine.instance?.loadPhotoUrl(
                this, dataPhoto?.src?.original.toString(), binding.ivPhoto
            )
        }
        setEvent()
    }

    override fun bindViewModel() {
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setEvent() {
        binding.apply {
            ivBack.setOnClickListener {
                finish()
            }
            ivPhoto.setOnTouchListener { v, event ->
                scaleGestureDetector.onTouchEvent(event)

                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = event.rawX - dx
                        lastY = event.rawY - dy
                        isDragging = true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if (isDragging && scaleFactor > 1f) {
                            dx = event.rawX - lastX
                            dy = event.rawY - lastY

                            val drawable = binding.ivPhoto.drawable
                            val imageWidth = drawable.intrinsicWidth * scaleFactor
                            val imageHeight = drawable.intrinsicHeight * scaleFactor

                            val viewWidth = binding.ivPhoto.width.toFloat()
                            val viewHeight = binding.ivPhoto.height.toFloat()

                            val dxRange = if (imageWidth > viewWidth) {
                                val minDx = viewWidth - imageWidth
                                val maxDx = 0f
                                dx.coerceIn(minDx, maxDx)
                            } else {
                                0f
                            }

                            val dyRange = if (imageHeight > viewHeight) {
                                val minDy = viewHeight - imageHeight
                                val maxDy = 0f
                                dy.coerceIn(minDy, maxDy)
                            } else {
                                0f
                            }

                            binding.ivPhoto.translationX = dxRange
                            binding.ivPhoto.translationY = dyRange
                        }
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        isDragging = false
                    }
                }
                true
            }
        }
    }
}