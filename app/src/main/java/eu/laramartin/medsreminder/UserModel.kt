package eu.laramartin.medsreminder

import eu.laramartin.medsreminder.datamodel.MedsUser


interface UserModel {
    fun getUser(): MedsUser?
}

