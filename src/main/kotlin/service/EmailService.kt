package service

import com.resend.Resend
import com.resend.core.exception.ResendException
import com.resend.services.emails.model.CreateEmailOptions
import model.EmailTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory


private val logger: Logger = LoggerFactory.getLogger("Email Logger")

object EmailService{
    fun sendEmail(recipientAddress: String, template: EmailTemplate) {
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
        } catch (e: ResendException) {
            logger.error(e.message)
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }
}
