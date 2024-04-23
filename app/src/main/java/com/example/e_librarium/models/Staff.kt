package com.example.e_librarium.models

class Staff {
    var email: String = ""
    var pass: String = ""
    var staffid: String = ""

    constructor(
        email: String,
        pass: String,
        staffid: String
    ){
        this.email = email
        this.pass = pass
        this.staffid = staffid
    }

    constructor()
}