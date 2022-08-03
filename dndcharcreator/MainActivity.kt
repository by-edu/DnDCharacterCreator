package edu.msudenver.dndcharcreator

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
* CS3013 - Mobile App Dev. - Summer 2022
* Instructor: Thyago Mota
* Student(s): Brandon Young, Anna Watson, Kathryn Werner
* Description: Main Activity
*/

class MainActivity : AppCompatActivity(), View.OnLongClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var dbHelper: DBHelper

    private inner class CharacterHolder(view: View): RecyclerView.ViewHolder(view) {

        val txtId : TextView = view.findViewById(R.id.txtId)
        val imgPane : ImageView = view.findViewById(R.id.imgPane)
        val txtName : TextView = view.findViewById(R.id.txtName)
        val txtRace : TextView = view.findViewById(R.id.txtRace)
        val txtJobClass : TextView = view.findViewById(R.id.txtJobClass)
        val txtBackground : TextView = view.findViewById(R.id.txtBackground)

    }

    private inner class CharacterAdapter(var characters: List<Characters>, var onLongClickListener: View.OnLongClickListener): RecyclerView.Adapter<CharacterHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list, parent, false)
            view.setOnLongClickListener(onLongClickListener)
            return CharacterHolder(view)
        }

        override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
            val entry = characters[position]

            holder.txtId.text = (entry.id.toString().toInt()).toString()
            holder.imgPane.setImageURI(Uri.parse(entry.uri.toString()))
            holder.txtName.text = "Name: ${entry.name.toString()}"
            holder.txtRace.text = "Race: ${entry.race.toString()}"
            holder.txtJobClass.text = "Class: ${entry.jobClass.toString()}"
            holder.txtBackground.text = "Background: ${entry.background.toString()}"

            holder.itemView.setOnLongClickListener(onLongClickListener)
        }

        override fun getItemCount(): Int {
            return characters.size
        }
    }

    fun populateRecyclerView() {

        val db = dbHelper.readableDatabase
        val characters = mutableListOf<Characters>()


        /*db.execSQL(
            """
            DROP TABLE IF EXISTS characters
        """)
        dbHelper.onCreate(db)*/
        // To clear pesky tables!

        val cursor = db.query(
            "characters",
            arrayOf("rowid, uri, name, race, jobclass, background, langandprof, features, bio, statstr, statdex, statcon, statint, statwis, statcha"),
            null,
            null,
            null,
            null,
            null
        )
        with (cursor) {
            while (moveToNext()) {


                val id = getInt(0)
                val uri = Uri.parse(getString(1))
                val name = getString(2).toString()
                val race = getString(3).toString()
                val jobClass = getString(4).toString()
                val background = getString(5).toString()
                val langAndProf = getString(6).toString()
                val features = getString(7).toString()
                val bio = getString(8).toString()
                val statStr = getString(9).toString().toInt()
                val statDex = getString(10).toString().toInt()
                val statCon = getString(11).toString().toInt()
                val statInt = getString(12).toString().toInt()
                val statWis = getString(13).toString().toInt()
                val statCha = getString(14).toString().toInt()

                val entry = Characters(id, uri, name, race, jobClass, background,
                    langAndProf, features, bio, statStr, statDex, statCon, statInt, statWis, statCha)
                characters.add(entry)
            }
        }
        recyclerView.adapter = CharacterAdapter(characters, this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        populateRecyclerView()

        val btnCreate : Button = findViewById(R.id.btnCreate)
        btnCreate.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        populateRecyclerView()
    }

    override fun onLongClick(view: View?): Boolean {

        class MyDialogInterfaceListener(val id: Int): DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    try {
                        val db = dbHelper.writableDatabase
                        db.execSQL(
                            """
                                DELETE FROM characters
                                WHERE rowid = "${id}"
                            """
                        )
                        onResume()

                    } catch (ex: Exception) {

                    }
                }
            }
        }

        if (view != null) {
            val id = view.findViewById<TextView>(R.id.txtId).text.toString().toInt()
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Delete this character permanently?")
            alertDialogBuilder.setPositiveButton("Yes", MyDialogInterfaceListener(id))
            alertDialogBuilder.setNegativeButton("No", MyDialogInterfaceListener(id))
            alertDialogBuilder.show()
            return true
        }
        return false
    }
}