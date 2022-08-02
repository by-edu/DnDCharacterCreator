package edu.msudenver.dndcharcreator

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.lang.Exception
import kotlin.random.Random

class CreateActivity : AppCompatActivity(){

    lateinit var db : SQLiteDatabase
    var btnChangeIcon : Button? = null
    var btnGenerateStats : Button? = null
    var btnSaveCharacter : Button? = null
    var imgIcon : ImageView? = null
    var txtStrStat : TextView? = null
    var txtDexStat : TextView? = null
    var txtConStat : TextView? = null
    var txtIntStat : TextView? = null
    var txtWisStat : TextView? = null
    var stringUri : String? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase

        btnChangeIcon = findViewById(R.id.btnChangeIcon)
        btnGenerateStats = findViewById(R.id.btnGenerateStats)
        btnSaveCharacter = findViewById(R.id.btnSaveChar)

        imgIcon = findViewById(R.id.imgIcon)

        txtStrStat = findViewById(R.id.txtStrStat)
        txtDexStat = findViewById(R.id.txtDexStat)
        txtConStat = findViewById(R.id.txtConStat)
        txtIntStat = findViewById(R.id.txtIntStat)
        txtWisStat = findViewById(R.id.txtWisStat)

        btnChangeIcon?.setOnClickListener{
            getImage()

        }

        btnGenerateStats?.setOnClickListener{
            generateStats()

        }

        btnSaveCharacter?.setOnClickListener{
            createCharacter()
            finish()

        }

    }

    fun generateStats(){

        var statRolls = List(5) { Random.nextInt(3, 19)}

        txtStrStat?.text = statRolls.get(0).toString()
        txtDexStat?.text = statRolls.get(1).toString()
        txtConStat?.text = statRolls.get(2).toString()
        txtIntStat?.text = statRolls.get(3).toString()
        txtWisStat?.text = statRolls.get(4).toString()

    }

    fun getImage(){
        val pickPhoto : Intent = Intent(Intent.ACTION_PICK)
        val picDirectory : File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val path = picDirectory.path
        val data : Uri = Uri.parse(path)

        stringUri = data.toString()
        // I think this will save it right

        pickPhoto.setDataAndType(data, "image/*")
        //pickPhoto.getStringExtra("image")
        startActivityForResult(pickPhoto, 1)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            val image = data?.data
            imgIcon?.setImageURI(image)
        }
    }

    fun createCharacter() {

        val name = findViewById<EditText>(R.id.txtName).text.toString()
        val race = findViewById<EditText>(R.id.txtRace).text.toString()
        val jobClass = findViewById<EditText>(R.id.txtClass).text.toString()
        val background = findViewById<EditText>(R.id.txtBackground).text.toString()
        val langAndProf = findViewById<EditText>(R.id.txtLangAndProf).text.toString()
        val features = findViewById<EditText>(R.id.txtFeatures).text.toString()
        val bio = findViewById<EditText>(R.id.txtCharBio).text.toString()
        val statStr = findViewById<TextView>(R.id.txtStrStat).text.toString().toInt()
        val statDex = findViewById<TextView>(R.id.txtDexStat).text.toString().toInt()
        val statCon = findViewById<TextView>(R.id.txtConStat).text.toString().toInt()
        val statInt = findViewById<TextView>(R.id.txtIntStat).text.toString().toInt()
        val statWis = findViewById<TextView>(R.id.txtWisStat).text.toString().toInt()

        try{
            db.execSQL(
                """
                            INSERT INTO characters VALUES
                                ("${stringUri}", "${name}","${race}","${jobClass}","${background}","${langAndProf}","${features}","${bio}","${statStr}","${statDex}","${statCon}","${statInt}","${statWis}")
                        """)

        } catch (ex: Exception){

        }

    }

}