package eu.laramartin.medsreminder

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import eu.laramartin.medsreminder.firebase.UserModelImpl


class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    val userViewModel: UserViewModel = UserViewModel(UserModelImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!userViewModel.isLoggedIn()) {
            startActivityForResult(userViewModel.getLoginIntent(), RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            userViewModel.onLoginResult(data, resultCode) {
                AlertDialog.Builder(this)
                        .setMessage(R.string.dialog_login_error)
                        .setPositiveButton(R.string.ok, { _, _ -> finish() })
                        .setCancelable(false)
                        .create().show()
            }
        }
    }
}
