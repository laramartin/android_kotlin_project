package eu.laramartin.medsreminder

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.ResultCodes
import eu.laramartin.medsreminder.firebase.UserModelImpl

class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    val userViewModel: UserViewModel = UserViewModel(UserModelImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!userViewModel.isLoggedIn()) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    arrayListOf(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == ResultCodes.OK) {
                // TODO login complete
                Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show()
                return
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this, "back button", Toast.LENGTH_SHORT).show()
                    return
                }

                if (response.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "no network", Toast.LENGTH_SHORT).show()
                    return
                }

                if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this, "unknown error", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            Toast.makeText(this, "unknown sign in", Toast.LENGTH_SHORT).show()
        }
    }
}
