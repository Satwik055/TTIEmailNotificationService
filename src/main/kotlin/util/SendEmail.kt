package util

import com.resend.Resend
import com.resend.core.exception.ResendException
import com.resend.services.emails.model.CreateEmailOptions
import model.EmailTemplate
import java.util.logging.Logger


private val logger: Logger = Logger.getLogger("Email Logger")

fun sendEmail(recipientAddress:String, template: EmailTemplate){
    try {
        val resend = Resend("re_VhondLjs_ChgY4Ks6ApyLifWu8sCNJshG")
        val params = CreateEmailOptions.builder()
            .from("TransferToInr <noreply@transfertoinr.com>")
            .to(recipientAddress)
            .subject(template.subject)
            .html(template.body)
            .build()
        resend.emails().send(params)
        logger.info("Email with template *${template.templateName}* successfully sent to $recipientAddress")
    }
    catch (e: ResendException) {
        println(e.message)
    }
    catch (e:Exception){
        println(e.message)
    }
}

