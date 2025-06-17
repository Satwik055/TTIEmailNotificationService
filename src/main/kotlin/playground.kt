import model.CurrencyType
import model.EmailTemplate
import model.TransactionStatus
import service.EmailService
import service.UserService
import util.formatTimestamp
import util.getNewTransactionEmailTemplate


fun main(){
    val newTransactionEmailTemplate = getNewTransactionEmailTemplate(
        transactionCode = "9",
        amount = "9",
        time = "9",
        currency = CurrencyType.USD,
        status = TransactionStatus.FAILED,
        username = "9"
    )
    EmailService.sendEmail("satwikkumar055@gmail.com", newTransactionEmailTemplate)

}
