package com.example.e_librarium.models

class BorrowingBook {
    var clientId: String = ""
    var bookId: String = ""
    var borrowDate: String = ""
    var returnDate: String = ""

    constructor(
        clientId: String,
        bookId: String,
        borrowDate: String,
        returnDate: String
    ) {
        this.clientId = clientId
        this.bookId = bookId
        this.borrowDate = borrowDate
        this.returnDate = returnDate
    }

    constructor()
}