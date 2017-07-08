package eu.laramartin.medsreminder.firebase

import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import eu.laramartin.medsreminder.UserModel
import eu.laramartin.medsreminder.datamodel.MedsUser

class UserModelImpl : UserModel {
    override fun getUser(): MedsUser? = FirebaseAuth.getInstance().currentUser?.let {
        MedsUser(it.uid)
    }

    override fun getLoginIntent(): Intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                        arrayListOf(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build()
}