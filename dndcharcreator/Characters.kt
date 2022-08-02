package edu.msudenver.dndcharcreator

import android.net.Uri
import java.util.*

class Characters(
    var id: Int,
    var imgByteArray : ByteArray,
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
    var statWis: Int): Comparable<Characters> {
    override fun compareTo(other: Characters): Int {
        if(id == other.id)
            return other.id.compareTo(id)
        return other.id - id
    }

}
