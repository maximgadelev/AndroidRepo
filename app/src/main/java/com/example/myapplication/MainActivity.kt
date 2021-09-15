package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var worker = Worker("John","McCarty",123,false,100)
        var projectWorker = ProjectWorker("Tom","Holand",124,true,200,4)
        var manager = Manager("Shamil","Zaripov",432,true,500,"Legend")
 println("Worker decision is "+ projectWorker.makeProject())
        println("Manager decision is" + manager.toManageWorker(worker))
        println(worker.toString())
        println(projectWorker.toString())
        println(manager.toString())
    }
}
