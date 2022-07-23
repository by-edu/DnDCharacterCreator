package edu.msudenver.dndcharactercreator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.msudenver.dndcharactercreator.fragments.CreateView
import edu.msudenver.dndcharactercreator.fragments.ListView


class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createFragment : Fragment = CreateView()
        val listFragment: Fragment = ListView()
        setFragment(createFragment)
        bottomNav = findViewById(R.id.naviMenu) as BottomNavigationView













        bottomNav.setOnNavigationItemSelectedListener() {
            when(it.getItemId()){
                R.id.navHome -> setFragment(createFragment)
                R.id.navList -> setFragment(listFragment)
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment) {
       if(fragment != null){
           val transaction = supportFragmentManager.beginTransaction()
           transaction.replace(R.id.fragmentContainer, fragment)
           transaction.commit()
        }
    }
}



