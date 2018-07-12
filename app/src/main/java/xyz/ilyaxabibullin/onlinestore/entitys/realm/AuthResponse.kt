package xyz.ilyaxabibullin.onlinestore.entitys.realm

import io.realm.RealmObject

open class AuthResponse: RealmObject(){
    var token = ""
    var error = ""
}