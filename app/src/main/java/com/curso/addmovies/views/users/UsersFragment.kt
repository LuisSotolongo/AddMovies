package com.curso.addmovies.views.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curso.addmovies.R


class UsersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    /*   toolbar.setOnMenuItemClickListener {
          when (it.itemId) {
              R.id.LogOut -> {
                  Firebase.auth.signOut()
                  findNavController().navigate(R.id.action_homeFragment_to_splashFragment)
              }

          }
          true
      }*/
}