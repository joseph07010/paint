package com.example.line_spiral_drawer

import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.line_spiral_drawer.PaintView.Companion.pathList
import com.example.line_spiral_drawer.databinding.ActivityMainBinding
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }
    private fun writeTextFile(directory: String, filename: String, content: String, b: Boolean){
        val dir = File(filesDir.path + "/" + directory)
        if(!dir.exists()) dir.mkdirs()

        val fullPath = dir.path + "/" + filename
        val writer = FileWriter(fullPath,b)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
        writer.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide() //hide the actionbar

        binding.eraseButton.setOnClickListener {
            pathList.clear()
            path.reset()
            PaintView.SharedData.timeList.clear()
            PaintView.SharedData.xList.clear()
            PaintView.SharedData.yList.clear()
        }
        binding.recordButton.setOnClickListener{
            val directoryName = "record"
            val filename = "pathRecord1.csv"
            writeTextFile(directoryName, filename, PaintView.SharedData.timeList.toString().replace("[","").replace("]",""),false)
            writeTextFile(directoryName, filename, "\n${PaintView.SharedData.xList.toString().replace("[","").replace("]","")}",true)
            writeTextFile(directoryName, filename, "\n${PaintView.SharedData.yList.toString().replace("[","").replace("]","")}",true)
            PaintView.SharedData.timeList.clear()
            PaintView.SharedData.xList.clear()
            PaintView.SharedData.yList.clear()
        }
    }
}
