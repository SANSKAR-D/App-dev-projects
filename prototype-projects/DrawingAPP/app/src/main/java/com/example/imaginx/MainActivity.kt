package com.example.imaginx

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import yuku.ambilwarna.AmbilWarnaDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var drawingView: DrawingView
    private lateinit var brushButton: ImageButton
    private lateinit var purple : ImageButton
    private lateinit var green : ImageButton
    private lateinit var blue : ImageButton
    private lateinit var yellow : ImageButton
    private lateinit var red : ImageButton
    private lateinit var orange : ImageButton
    private lateinit var cyan : ImageButton
    private lateinit var undo : ImageButton
    private lateinit var colorChanger : ImageButton
    private lateinit var gallery : ImageButton

    private val openGalleryLauncher : ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            findViewById<ImageView>(R.id.gallery1).setImageURI(result.data?.data)
        }

    val requestPermission : ActivityResultLauncher<Array<String>> = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()){
        permissions -> permissions.entries.forEach{
            val permissionName = it.key
            val isGranted = it.value

            if(isGranted && permissionName == android.Manifest.permission.READ_MEDIA_IMAGES){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                openGalleryLauncher.launch(pickIntent)
            }else{
                if(permissionName == android.Manifest.permission.READ_MEDIA_IMAGES){
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        drawingView = findViewById(R.id.drawing_view)
        brushButton = findViewById(R.id.brush_button)
        purple = findViewById(R.id.purple)
        green = findViewById(R.id.green)
        red = findViewById(R.id.red)
        orange = findViewById(R.id.orange)
        yellow = findViewById(R.id.yellow)
        cyan = findViewById(R.id.cyan)
        blue = findViewById(R.id.blue)
        undo = findViewById(R.id.undo)
        gallery = findViewById(R.id.gallery)
        colorChanger = findViewById(R.id.color)
        drawingView.changeBrushSize(23.toFloat())
        purple.setOnClickListener(this)
        green.setOnClickListener(this)
        yellow.setOnClickListener(this)
        blue.setOnClickListener(this)
        cyan.setOnClickListener(this)
        orange.setOnClickListener(this)
        red.setOnClickListener(this)
        undo.setOnClickListener(this)
        gallery.setOnClickListener(this)
        colorChanger.setOnClickListener(this)
        brushButton.setOnClickListener {
            showBrushChooserDialog()
        }
    }
    private fun showBrushChooserDialog(){
        val brushDialog = Dialog(this@MainActivity)
        brushDialog.setContentView(R.layout.dialog_brush)
        val seekBarProgress = brushDialog.findViewById<SeekBar>(R.id.dialog_seekbar)
        val showProgressTv = brushDialog.findViewById<TextView>(R.id.seekbar_progress_text)
        seekBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                drawingView.changeBrushSize(seekBar?.progress!!.toFloat())
                showProgressTv.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        brushDialog.show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.purple ->{
                drawingView.setColor("#BA6FE0")
            }
            R.id.green ->{
                drawingView.setColor("#94FF58")
            }
            R.id.red ->{
                drawingView.setColor("#E91E63")
            }
            R.id.cyan ->{
                drawingView.setColor("#11EDE5")
            }
            R.id.blue ->{
                drawingView.setColor("#173AE9")
            }
            R.id.orange ->{
                drawingView.setColor("#FF9800")
            }
            R.id.yellow ->{
                drawingView.setColor("#FFEB3B")
            }
            R.id.undo ->{
                drawingView.undoPath()
            }
            R.id.color->{
                showColorPickerDialog()
            }
            R.id.gallery->{
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                    requestStoragePermission()
                }else{
                    val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    openGalleryLauncher.launch(pickIntent)
                }

            }
        }
    }
    private fun showColorPickerDialog(){
        val dialog = AmbilWarnaDialog(this,Color.MAGENTA,object: AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {

            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                drawingView.setColor(color)
            }

        } )
        dialog.show()
    }
    private fun requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES)){
            showRationaleDialog()
        }else{
            requestPermission.launch(
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            )
        }
    }
    private fun showRationaleDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Storage permission")
            .setMessage("We need this permission in order to access the internal storage")
            .setPositiveButton(R.string.dialog_yes){
                dialog, _ -> dialog.dismiss()
            }
        builder.create().show()
    }
}