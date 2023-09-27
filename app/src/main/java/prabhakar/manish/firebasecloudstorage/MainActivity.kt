package prabhakar.manish.firebasecloudstorage

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import prabhakar.manish.firebasecloudstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Add a new document with a generated ID
        binding.btnSave.setOnClickListener {

            val ename = binding.etName.text.toString()
            val elocation = binding.etLocation.text.toString()
            val eemail = binding.etEmail.text.toString()
            val epassword = binding.etPassword.text.toString()

            // Create a new user with a first and last name
            val user = hashMapOf(
                "name" to ename,
                "location" to elocation,
                "email" to eemail,
                "password" to epassword
            )
            Toast.makeText(this, ename, Toast.LENGTH_LONG).show()
            db.collection("users").add(user).addOnSuccessListener {

            }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        binding.btnFetchData.setOnClickListener {
            db.collection("users").get().addOnSuccessListener {
                    for (document in it) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }

    }
}