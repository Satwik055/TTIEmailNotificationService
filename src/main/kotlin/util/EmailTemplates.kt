package util

import model.EmailTemplate

fun getTransactionUpdateEmailTemplate(status:String, transactionCode:String): EmailTemplate {
    return EmailTemplate(
        templateName = "Transaction Update",
        subject = "Transaction Update",
        body = "Your transaction with id $transactionCode is $status"
    )
}

fun getAdminNotifyEmailTemplate(transactionCode:String): EmailTemplate {
    return EmailTemplate(
        templateName = "Admin Transaction Notification",
        subject = "New Transaction Initiated",
        body = "A new transaction with code $transactionCode has been initiated"
    )
}
