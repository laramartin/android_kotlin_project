package eu.laramartin.medsreminder

class UserViewModel(val userModel: UserModel) {
    fun isLoggedIn(): Boolean = userModel.getUser() != null
}