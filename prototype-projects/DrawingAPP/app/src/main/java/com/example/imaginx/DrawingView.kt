package com.example.imaginx

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.createBitmap

class DrawingView(context: Context,attrs : AttributeSet) : View(context, attrs){
    private lateinit var drawPath : FingerPath
    private lateinit var canvasPaint: Paint
    private lateinit var drawPaint: Paint
    private  var color = Color.CYAN
    private lateinit var canvas: Canvas
    private lateinit var canvasBitmap: Bitmap
    private var brushSize = 0.toFloat()
    private val paths = mutableListOf<FingerPath>()
    init {
        setUpDrawing()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = createBitmap(w, h)
        canvas = Canvas(canvasBitmap)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                drawPath.color = color
                drawPath.brushThickness = brushSize.toFloat()

                drawPath.reset()
                drawPath.moveTo(touchX!!,touchY!!)
            }

            MotionEvent.ACTION_MOVE->{
                drawPath.lineTo(touchX!!,touchY!!)
                paths.add(drawPath)
            }

            MotionEvent.ACTION_UP->{
                drawPath = FingerPath(color,brushSize)
            }
            else -> return false
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap,0f,0f,drawPaint)

        for(path in paths){
            drawPaint.strokeWidth = path.brushThickness
            drawPaint.color = path.color
            canvas.drawPath(path,drawPaint)
        }
        if(!drawPath.isEmpty){
            drawPaint.strokeWidth = drawPath.brushThickness
            drawPaint.color = drawPath.color
            canvas.drawPath(drawPath,drawPaint)
        }
    }
    private fun setUpDrawing(){
        drawPaint = Paint()
        drawPath = FingerPath(color,brushSize)
        drawPaint.color = color
        drawPaint.style = Paint.Style.STROKE

        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)
        brushSize = 20.toFloat()
    }
    fun changeBrushSize(newSize : Float){
        brushSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            newSize,resources.displayMetrics
        )
        drawPaint.strokeWidth = brushSize
    }
    internal inner class FingerPath(var color: Int, var brushThickness: Float) : Path()
}