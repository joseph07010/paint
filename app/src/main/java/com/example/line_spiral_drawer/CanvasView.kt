package com.example.line_spiral_drawer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint: Paint = Paint()
        paint.setColor(Color.MAGENTA)
        paint.strokeWidth = 3f
        canvas?.drawLine(0f,0f,700f,1000f,paint)

        paint.setColor(Color.GREEN)
        paint.style=Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas?.drawCircle(400f,600f,200f,paint)

        paint.setColor(Color.CYAN)
        paint.style=Paint.Style.FILL
        canvas?.drawCircle(400f,600f,200f,paint)


        val path: Path = Path()
        path.moveTo(700f,400f)
        path.lineTo(1000f,1400f)
        path.lineTo(200f,1400f)
        path.close()
        paint.setColor(Color.RED)
        paint.style=Paint.Style.STROKE
        canvas?.drawPath(path, paint)
    }
}