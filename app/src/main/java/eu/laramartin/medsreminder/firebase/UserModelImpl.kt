package eu.laramartin.medsreminder.firebase

import com.google.firebase.auth.FirebaseAuth
import eu.laramartin.medsreminder.UserModel
import eu.laramartin.medsreminder.datamodel.MedsUser

class UserModelImpl : UserModel {
    override fun getUser(): MedsUser? = FirebaseAuth.getInstance().currentUser?.let {
        MedsUser(it.uid)
    }
}