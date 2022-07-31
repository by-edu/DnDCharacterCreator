package edu.msudenver.dndcharactercreator.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.dndcharactercreator.R

class ListView : Fragment() {

    var extras : Bundle? = null
    lateinit var recyclerView: RecyclerView
    //lateinit var dbHelper: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.list_view, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        extras = activity?.intent?.extras

        if (extras != null){

        var name = extras?.getString("name")
        var race = extras?.getString("race")
        var jobClass = extras?.getString("jobclass")
        var background = extras?.getString("background")
        var statStr = extras?.getString("statStr")
        var statDex = extras?.getString("statDex")
        var statCon = extras?.getString("statCon")
        var statInt = extras?.getString("statInt")
        var statWis = extras?.getString("statWis")
        var langAndProf = extras?.getString("langAndProf")
        var features = extras?.getString("features")
        var bio = extras?.getString("bio")
        var uri : Uri = extras!!.get("uri") as Uri
        }





    }
}