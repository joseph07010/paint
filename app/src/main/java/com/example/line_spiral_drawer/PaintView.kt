package com.example.line_spiral_drawer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.line_spiral_drawer.MainActivity.Companion.paintBrush
import com.example.line_spiral_drawer.MainActivity.Companion.path
import com.example.line_spiral_drawer.PaintView.SharedData.timeList
import com.example.line_spiral_drawer.PaintView.SharedData.xList
import com.example.line_spiral_drawer.PaintView.SharedData.yList
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class PaintView:View {
    var params : ViewGroup.LayoutParams? = null

    companion object{
        var pathList = ArrayList<Path>()
        var currentBrush = Color.BLACK

    }
    constructor(context: Context) : this(context, null) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    object SharedData{
        val timeList: MutableList<Long> = mutableListOf()
        val xList: MutableList<Float> = mutableListOf()
        val yList: MutableList<Float> = mutableListOf()
    }



    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x,y)
                pathList.add(path)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x,y)
                timeList.add(System.currentTimeMillis())
                xList.add(x)
                yList.add(y)
                Log.d("hihi","x:$x,y:$y")
            }
            MotionEvent.ACTION_UP -> {
                pathList.add(path)
            }
        }
        postInvalidate()
        return false
    }


    override fun onDraw(canvas: Canvas) {
        for(i in pathList.indices){
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }
}
