package com.example.e_librarium.models

import java.util.Date

class Books {
    var bookTitle: String = ""
    var bookAuthor: String = ""
    var bookYearOfPublication: String = ""
    var bookPrice: String = ""
    var bookISBNNumber: String = ""
    var bookPublisher: String = ""
    var bookPublicationDate: String = ""
    var bookGenre: String = ""
    var bookEdition: String = ""
    var bookLanguage: String = ""
    var bookNumberOfPages: String = ""
    var bookAcquisitionMethod: String = ""
    var bookCondition: String = ""
    var bookShelfNumber: String = ""
    var bookSynopsis: String = ""
    var bookImageUrl: String = ""
    var bookQuantity: Int = 0
    var bookId: String = ""

    constructor(
        bookTitle: String,
        bookAuthor: String,
        bookYearOfPublication: String,
        bookPrice: String,
        bookISBNNumber: String,
        bookPublisher: String,
        bookPublicationDate: String,
        bookGenre: String,
        bookEdition: String,
        bookLanguage: String,
        bookNumberOfPages: String,
        bookAcquisitionMethod: String,
        bookCondition: String,
        bookShelfNumber: String,
        bookSynopsis: String,
        bookImageUrl: String,
        bookQuantity: Int,
        bookId: String
        ){
        this.bookTitle = bookTitle
        this.bookAuthor = bookAuthor
        this.bookYearOfPublication = bookYearOfPublication
        this.bookPrice = bookPrice
        this.bookPublisher = bookPublisher
        this.bookPublicationDate = bookPublicationDate
        this.bookCondition = bookCondition
        this.bookShelfNumber = bookShelfNumber
        this.bookSynopsis = bookSynopsis
        this.bookEdition = bookEdition
        this.bookLanguage = bookLanguage
        this.bookNumberOfPages = bookNumberOfPages
        this.bookGenre = bookGenre
        this.bookAcquisitionMethod = bookAcquisitionMethod
        this.bookISBNNumber = bookISBNNumber
        this.bookImageUrl = bookImageUrl
        this.bookQuantity = bookQuantity
        this.bookId = bookId
    }

    constructor()
}