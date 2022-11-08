package com.curso.addmovies.views.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.curso.addmovies.R
import com.curso.addmovies.views.movies.MoviesFragment
import com.curso.addmovies.views.tv.TvFragment
import com.curso.addmovies.views.users.UsersFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ContainerFragment : Fragment() {
    private lateinit var auth: Firebase
    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigation(view)
        val user = Firebase.auth.currentUser


    }


    private fun bottomNavigation(view: View) {
        val bottom_navigation_view =
            view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottom_navigation_view.setOnItemSelectedListener { menuItem ->
            Log.i("BOTON", "${menuItem.itemId}")
            when (menuItem.itemId) {

                R.id.action_home -> {
                    goToFragment(HomeFragment())
                    true
                }
                R.id.action_movies -> {
                    goToFragment(MoviesFragment())
                    true
                }
                R.id.action_tv -> {
                    goToFragment(TvFragment())
                    true
                }
                R.id.action_users -> {
                    goToFragment(UsersFragment())
                    true
                }
                else -> false
            }
        }
        bottom_navigation_view.selectedItemId = R.id.action_home

    }

    private fun goToFragment(fragment: Fragment) {

        //supportFragmentManager.beginTransaction().replace(R.id.frameHome, fragment).commit()
        childFragmentManager.beginTransaction().replace(R.id.frameHome, fragment).commit()
    }
}