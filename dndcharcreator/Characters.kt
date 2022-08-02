package edu.msudenver.dndcharcreator

import android.net.Uri
import java.util.*

class Characters(
    var id: Int,
    var imgUri : Uri,
    var name : String,
    var race : String,
    var jobClass: String,
    var background: String ): Comparable<Characters> {
    override fun compareTo(other: Characters): Int {
        if(id == other.id)
            return other.id.compareTo(id)
        return other.id - id
    }

}
