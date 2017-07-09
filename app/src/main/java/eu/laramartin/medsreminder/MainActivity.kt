package eu.laramartin.medsreminder

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import eu.laramartin.medsreminder.firebase.UserModelImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 123

    val userViewModel: UserViewModel = UserViewModel(UserModelImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!userViewModel.isLoggedIn()) {
            startActivityForResult(userViewModel.getLoginIntent(), RC_SIGN_IN)
        }
        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_meds -> {
                    view_meds.visible(true)
                    view_friends.visible(false)
                    view_settings.visible(false)
                    true
                }
                R.id.action_friends ->  {
                    view_meds.visible(false)
                    view_friends.visible(true)
                    view_settings.visible(false)
                    true
                }
                R.id.action_settings ->  {
                    view_meds.visible(false)
                    view_friends.visible(false)
                    view_settings.visible(true)
                    true
                }
                else -> false
            }
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
