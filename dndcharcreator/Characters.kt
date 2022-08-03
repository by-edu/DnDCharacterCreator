package edu.msudenver.dndcharcreator

import android.net.Uri
import java.util.*

/*
* CS3013 - Mobile App Dev. - Summer 2022
* Instructor: Thyago Mota
* Student(s): Brandon Young, Anna Watson, Kathryn Werner
* Description: Character Class
*/

class Characters(
    var id: Int,
    var uri : Uri,
    var name : String,
    var race : String,
    var jobClass: String,
    var background: String,
    var langAndProf: String,
    var features: String,
    var bio: String,
    var statStr: Int,
    var statDex: Int,
    var statCon: Int,
    var statInt: Int,
    var statWis: Int,
    var statCha: Int): Comparable<Characters> {
    override fun compareTo(other: Characters): Int {
        if(id == other.id)
            return other.id.compareTo(id)
        return other.id - id
    }

}
