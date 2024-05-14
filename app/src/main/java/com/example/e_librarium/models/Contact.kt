package com.example.e_librarium.models

class Contact {
    private var guestName: String = ""
    private var guestEmail: String = ""
    private var guestPhoneNumber: String = ""
    private var guestComment: String = ""
    private var guestCallback: String = ""
    private var guestId: String = ""

    constructor(
        guestName: String,
        guestEmail: String,
        guestPhoneNumber: String,
        guestComment: String,
        guestCallback: String,
        guestId: String
    ){
        this.guestName = guestName
        this.guestEmail = guestEmail
        this.guestPhoneNumber = guestPhoneNumber
        this.guestComment = guestComment
        this.guestCallback = guestCallback
        this.guestId = guestId
    }
    constructor()
}