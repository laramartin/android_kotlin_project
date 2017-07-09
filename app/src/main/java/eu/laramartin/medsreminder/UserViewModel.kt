package eu.laramartin.medsreminder

import android.app.Activity
import android.content.Intent

class UserViewModel(val userModel: UserModel) {
    fun isLoggedIn(): Boolean = userModel.getUser() != null

    fun getLoginIntent(): Intent = userModel.getLoginIntent()

    fun onLoginResult(data: Intent?, resultCode: Int, onError: () -> Unit) {
        if (resultCode == Activity.RESULT_OK) {
            return
        } else {
            if (!userModel.isLoginSuccessful(data)) {
                onError()
            }
        }
        onError()
    }
}