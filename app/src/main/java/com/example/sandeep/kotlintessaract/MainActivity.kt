package com.example.sandeep.kotlintessaract

import android.Manifest.permission.CAMERA
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var textView : TextView
    lateinit var button : Button
    lateinit var imageView : ImageView
    lateinit var permissionlauncher: ActivityResultLauncher<Array<String>>
    var isCameraPermission = false
    var isReadPermission = false
    var isWritePermission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)

        permissionlauncher =registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->

            isCameraPermission = permissions[android.Manifest.permission.CAMERA] ?: isCameraPermission
            isReadPermission = permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE] ?: isReadPermission
            isWritePermission = permissions[android.Manifest.permission.WRITE_APN_SETTINGS] ?: isWritePermission

        };
        requestPermission()

    }

    fun requestPermission() {

        isCameraPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        isReadPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        isWritePermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        var permisionRequest:MutableList<String> = ArrayList()

        if (!isCameraPermission){
            permisionRequest.add(android.Manifest.permission.CAMERA)
        }

        if (!isWritePermission){
            permisionRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!isReadPermission){
            permisionRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permisionRequest.isNotEmpty()){
            permissionlauncher.launch(permisionRequest.toTypedArray())
        }

    }


}


