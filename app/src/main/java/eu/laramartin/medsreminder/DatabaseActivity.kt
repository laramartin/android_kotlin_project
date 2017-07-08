package eu.laramartin.medsreminder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseActivity : AppCompatActivity() {

    val LOG_TAG: String = DatabaseActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        FirebaseAuth.getInstance().currentUser?.let { user ->
            val database = FirebaseDatabase.getInstance().reference
            database.child(user.uid)
                    .child("meds")
                    .child("medTest")
                    .setValue(Med("medTest", "Mo-We", "22:00"))

            database.child(user.uid)
                    .child("meds")
                    .addListenerForSingleValueEvent(object: ValueEventListener {
                        override fun onCancelled(p0: DatabaseError?) {
                            Log.e(LOG_TAG, "error ${p0?.message}")
                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            p0?.children?.forEach {
                                val med = it.getValue(Med::class.java)
                                Log.i(LOG_TAG, med.toString())
                            }
                        }
                    })

        }
    }
}

data class Med(
        var name: String = "",
        var daysOfWeek: String = "",
        var time: String = ""
)