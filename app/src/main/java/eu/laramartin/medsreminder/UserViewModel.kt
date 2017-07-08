package eu.laramartin.medsreminder

import android.content.Intent

class UserViewModel(val userModel: UserModel) {
    fun isLoggedIn(): Boolean = userModel.getUser() != null
    fun getLoginIntent(): Intent = userModel.getLoginIntent()
}