package eu.laramartin.medsreminder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        FirebaseAuth.getInstance().currentUser?.let { user ->
            val database = FirebaseDatabase.getInstance().reference
            database.child(user.uid)
                    .child("meds")
                    .child("medTest")
                    .setValue(Med("medTest", "Mo-We", "22:00"))


        }
    }
}

data class Med(
        val name: String,
        val daysOfWeek: String,
        val time: String
)