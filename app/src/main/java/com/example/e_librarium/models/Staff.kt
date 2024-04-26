package com.example.e_librarium.models

class Staff {
    var fullName: String = ""
    var gender: String = ""
    var marriageStatus: String = ""
    var phoneNumber: String = ""
    var email: String = ""
    var pass: String = ""
    var staffid: String = ""

    constructor(
        fullName: String,
        gender: String,
        marriageStatus: String,
        phoneNumber: String,
        email: String,
        pass: String,
        staffid: String
    ){
        this.fullName = fullName
        this.gender = gender
        this.marriageStatus = marriageStatus
        this.phoneNumber = phoneNumber
        this.email = email
        this.pass = pass
        this.staffid = staffid
    }

    constructor()
}