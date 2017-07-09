package eu.laramartin.medsreminder

import android.content.Intent
import eu.laramartin.medsreminder.datamodel.MedsUser


interface UserModel {
    fun getUser(): MedsUser?
    fun getLoginIntent(): Intent
    fun isLoginSuccessful(data: Intent?): Boolean
}

