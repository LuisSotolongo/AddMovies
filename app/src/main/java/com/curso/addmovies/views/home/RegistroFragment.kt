package com.curso.addmovies.views.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.curso.addmovies.R
import com.curso.addmovies.models.Users
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistroFragment : Fragment() {

    val db = Firebase.firestore


    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarRegistro)


        val navHostFragment = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(toolbar, navHostFragment)
        toolbar.title = "Registrate"


        val btnRegistro = view.findViewById<Button>(R.id.btnRegister)
        auth = Firebase.auth
        val email = view.findViewById<EditText>(R.id.mail)
        val password = view.findViewById<EditText>(R.id.passwdregistro)
        val name = view.findViewById<EditText>(R.id.nameRegistro)


        btnRegistro.setOnClickListener {

            if (name.text.isNotEmpty() && email.text.isNotEmpty() && password.text.isNotEmpty() && password.text.length >= 6) {
                crearCuenta(email.text.toString(), password.text.toString(), name.text.toString())
            } else {
                Toast.makeText(context, "Los campos son obligatorios, el password minimo 6 caracteres",
                    Toast.LENGTH_LONG).show()

            }

        }


    }

    fun crearCuenta(email: String, password: String, nameUser: String) {
        Log.i("dale", "$email --- $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    addUser(nameUser, auth.currentUser!!.uid)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("CREAR CUENTA ERROR", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Usuario ya registrado introduce otro Email.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }


    }

    fun addUser(nameUser: String, UID: String) {

        Log.i("USER NAME", "$nameUser")
        val user = mutableListOf<Users>(
            Users("$nameUser")
        )
        for (users in user) {

            Log.i("USUARIO", "$user")
            db.collection("users").document(UID)
                .set(users)
                .addOnSuccessListener { documentReference ->
                    findNavController().navigate(R.id.action_registroFragment_to_containerFragment)

                }
                .addOnFailureListener { e ->
                    Log.w("ADD USER", "Error adding document", e)
                    Toast.makeText(
                        context, "Error al crear un usuario.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    }


}