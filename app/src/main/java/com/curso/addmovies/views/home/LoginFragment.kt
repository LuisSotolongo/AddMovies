package com.curso.addmovies.views.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.curso.addmovies.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val btnLogin = view.findViewById<Button>(R.id.btnLoginRegister)
        val btnLoginHome =  view.findViewById<Button>(R.id.btnLoginHome)
        val email = view.findViewById<EditText>(R.id.correo)
        val password = view.findViewById<EditText>(R.id.passwd)
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }



        btnLoginHome.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
            loginUsuario(email.text.toString(),password.text.toString())


    }else{
            Toast.makeText(context, "Debes Introducir datos validos para entrar",
                Toast.LENGTH_SHORT).show()
        }
    }
    }
    fun loginUsuario(email: String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser
                    findNavController().navigate(R.id.action_loginFragment_to_containerFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }


    }

}