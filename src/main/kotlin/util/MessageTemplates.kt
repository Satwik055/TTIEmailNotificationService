package util

import model.MessageTemplate

fun getTransactionUpdateMessageTemplate(status:String, transactionCode:String): MessageTemplate {
    return MessageTemplate(
        templateName = "Transaction Update",
        title = "Transaction Update",
        body = "Your transaction with id $transactionCode is $status"
    )
}
