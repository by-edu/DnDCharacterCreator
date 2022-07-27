package edu.msudenver.dndcharactercreator.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import edu.msudenver.dndcharactercreator.R
import java.io.File
import kotlin.random.Random

class CreateView : Fragment() {

    var imgIcon: ImageView? = null
    var btnChangeIcon: Button? = null
    var btnGenerateStats: Button? = null

    var btnSaveChar : Button? = null
    // this button needs to be created first

    var txtName : EditText? = null
    var spnRace : Spinner? = null // a spinner maybe
    var spnClass : Spinner? = null // a spinner maybe
    var spnBackground : Spinner? = null // a spinner maybe
    var txtLangAndProf : EditText? = null
    var txtFeatures : EditText? = null
    var txtCharBio : EditText? = null

    var txtStrStat : TextView? = null
    var txtDexStat : TextView? = null
    var txtConStat : TextView? = null
    var txtIntStat : TextView? = null
    var txtWisStat : TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View =  inflater.inflate(R.layout.create_view, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ImageView
        imgIcon = view.findViewById(R.id.imgIcon)

        // Initialize Buttons and ImageView
        btnChangeIcon = view.findViewById(R.id.btnChangeIcon)
        btnGenerateStats = view.findViewById(R.id.btnGenerateStats)
        //btnSaveChar = view.findViewById(R.id.btnSaveChar)

        // Initialize Spinners and EditTexts
        txtName = view.findViewById(R.id.txtName)
        spnRace = view.findViewById(R.id.spnRace)
        spnClass = view.findViewById(R.id.spnClass)
        spnBackground = view.findViewById(R.id.spnBackground)
        txtLangAndProf = view.findViewById(R.id.txtLangAndProf)
        txtFeatures = view.findViewById(R.id.txtFeatures)
        txtCharBio = view.findViewById(R.id.txtCharBio)

        // Initialize TextViews
        txtStrStat = view.findViewById(R.id.txtStrStat)
        txtDexStat = view.findViewById(R.id.txtDexStat)
        txtConStat = view.findViewById(R.id.txtConStat)
        txtIntStat = view.findViewById(R.id.txtIntStat)
        txtWisStat = view.findViewById(R.id.txtWisStat)




        btnChangeIcon?.setOnClickListener{
            getImage()
        }

        btnGenerateStats?.setOnClickListener{
            generateStats()
        }

    }

    fun generateStats () {

        var statRolls = List(5) { Random.nextInt(3, 19)}

        txtStrStat?.text = statRolls.get(0).toString()
        txtDexStat?.text = statRolls.get(1).toString()
        txtConStat?.text = statRolls.get(2).toString()
        txtIntStat?.text = statRolls.get(3).toString()
        txtWisStat?.text = statRolls.get(4).toString()
        //println(statRolls)

    }

    fun getImage(){

       val pickPhoto : Intent = Intent(Intent.ACTION_PICK)
        val picDirectory : File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val path = picDirectory.path
        val data : Uri = Uri.parse(path)

        pickPhoto.setDataAndType(data, "image/*")
        //pickPhoto.getStringExtra("image")
        startActivityForResult(pickPhoto, 1)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
            val image = data?.data
            imgIcon?.setImageURI(image)
        }

    }

}