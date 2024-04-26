package com.example.e_librarium.models

class Clients {
    var fullName: String = ""
    var gender: String = ""
    var maritalStatus: String = ""
    var phoneNumber: String = ""
    var dateOfBirth: String = ""
    var email: String = ""
    var pass: String = ""
    var clientProfilePictureUrl: String = ""
    var clientid: String = ""

    constructor(
        fullName: String,
        gender: String,
        maritalStatus: String,
        phoneNumber: String,
        dateOfBirth: String,
        email: String,
        pass: String,
        clientProfilePictureUrl: String,
        clientid: String
    ){
        this.fullName = fullName
        this.gender = gender
        this.maritalStatus = maritalStatus
        this.phoneNumber = phoneNumber
        this.dateOfBirth = dateOfBirth
        this.email = email
        this.pass = pass
        this.clientProfilePictureUrl = clientProfilePictureUrl
        this.clientid = clientid
    }

    constructor()
}