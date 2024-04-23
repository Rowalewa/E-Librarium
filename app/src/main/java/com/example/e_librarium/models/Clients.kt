package com.example.e_librarium.models

class Clients {
    var email: String = ""
    var pass: String = ""
    var clientid: String = ""

    constructor(
        email: String,
        pass: String,
        clientid: String
    ){
        this.email = email
        this.pass = pass
        this.clientid = clientid
    }

    constructor()
}