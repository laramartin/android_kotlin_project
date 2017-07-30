package eu.laramartin.medsreminder.firebase

import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import eu.laramartin.medsreminder.UserModel
import eu.laramartin.medsreminder.datamodel.MedsUser

class UserModelImpl : UserModel {
    override fun isLoginSuccessful(data: Intent?): Boolean {
        val response = IdpResponse.fromResultIntent(data)
        if (response == null) {
            return false
        }
        if (response.errorCode == ErrorCodes.NO_NETWORK) {
            return false
        }
        if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
            return false
        }
        return true
    }

    override fun getUser(): MedsUser? = FirebaseAuth.getInstance().currentUser?.let {
        MedsUser(it.uid)
    }

    override fun getLoginIntent(): Intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(
                        arrayListOf(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build()
}