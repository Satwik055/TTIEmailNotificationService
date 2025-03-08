package util

import model.EmailTemplate

fun getTransactionUpdateEmailTemplate(status:String, transactionCode:String): EmailTemplate {
    return EmailTemplate(
        templateName = "Transaction Update",
        subject = "Transaction Update",
        body = "Your transaction with id $transactionCode is $status"
    )
}
