package edu.msudenver.dndcharactercreator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import edu.msudenver.dndcharactercreator.R

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




        /*btnChangeIcon?.setOnClickListener{

            println("OH YEA!!!")
            // this works!!!!!

        }*/



    }

}