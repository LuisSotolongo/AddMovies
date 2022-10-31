package com.curso.addmovies.views.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.curso.addmovies.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class UsersFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonLogout = view.findViewById<Button>(R.id.logout)
         buttonLogout.setOnClickListener {
             Firebase.auth.signOut()
             findNavController().navigate(R.id.action_containerFragment_to_splashFragment)
         }
    }


    /*   toolbar.setOnMenuItemClickListener {
          when (it.itemId) {
              R.id.LogOut -> {

                  findNavController().navigate(R.id.action_homeFragment_to_splashFragment)
              }

          }
          true
      }*/
}