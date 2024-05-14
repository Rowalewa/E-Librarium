package com.example.e_librarium.models

class Feedback {
    private var feedbackName: String = ""
    private var feedbackEmailAddress: String = ""
    private var feedbackText: String = ""

    constructor(
        feedBackName: String,
        feedBackEmailAddress: String,
        feedbackText: String
    ){
        this.feedbackName = feedBackName
        this.feedbackEmailAddress = feedBackEmailAddress
        this.feedbackText = feedbackText
    }

    constructor()
}