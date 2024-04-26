package com.example.e_librarium.models

class Staff {
    var fullName: String = ""
    var gender: String = ""
    var maritalStatus: String = ""
    var phoneNumber: String = ""
    var dateOfBirth: String = ""
    var email: String = ""
    var pass: String = ""
    var staffProfilePictureUrl: String = ""
    var staffid: String = ""

    constructor(
        fullName: String,
        gender: String,
        maritalStatus: String,
        phoneNumber: String,
        dateOfBirth: String,
        email: String,
        pass: String,
        staffProfilePictureUrl: String,
        staffid: String
    ){
        this.fullName = fullName
        this.gender = gender
        this.maritalStatus = maritalStatus
        this.phoneNumber = phoneNumber
        this.dateOfBirth = dateOfBirth
        this.email = email
        this.pass = pass
        this.staffProfilePictureUrl = staffProfilePictureUrl
        this.staffid = staffid
    }

    constructor()
}