package app.mikazuki.thailand.domain

import app.mikazuki.thailand.domain.participants.Participant
import app.mikazuki.thailand.domain.party.Party
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient
import com.amazonaws.services.simpleemail.model.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.util.*
import javax.mail.internet.InternetAddress

@Service
class MailSenderService {

    val LOG = LoggerFactory.getLogger(MailSenderService::class.java)

    @Autowired
    lateinit var messageSource: MessageSource

    @Value("\${aws.ses.accesskey:}")
    lateinit var ACCESS_KEY: String

    @Value("\${aws.ses.secretkey:}")
    lateinit var SECRET_ACCESS_KEY: String

    private val FROM_ADDRESS = "eo.wedding.beta@gmail.com"

    fun send(party: Party, participant: Participant) {

        val subject = messageSource.getMessage("confirmed.message.title", null, Locale.JAPAN)
        val url = "http://eo-wedding.com/parties/" + party.hash
        val userName = participant.lastName + " " + participant.firstName
        val attendance = if (participant.attendance) "出席" else "欠席"

        val messageProperties = arrayOf(userName, party.name, url, participant.postalCode, participant.address, participant.email, attendance)
        val textBody = messageSource.getMessage("confirmed.message.body.detail", messageProperties, Locale.JAPAN) + messageSource.getMessage("confirmed.message.footer", null, Locale.JAPAN)
        val client = AmazonSimpleEmailServiceClient(BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY))
                .withRegion<AmazonSimpleEmailServiceClient>(Regions.US_WEST_2)
        val request = SendEmailRequest()
                .withDestination(
                        Destination().withToAddresses(participant.email))
                .withMessage(Message()
                        .withBody(Body()
                                .withText(Content()
                                        .withCharset("UTF-8").withData(textBody)))
                        .withSubject(Content()
                                .withCharset("UTF-8").withData(subject)))
                .withSource(senderAddressBuilder(FROM_ADDRESS, messageSource.getMessage("confirmed.message.sender", null, Locale.JAPAN)))

        try {
            client.sendEmail(request)
        } catch (e: Exception) {
            LOG.error("Failed to send mail to ${participant.email}", e)
        }

    }

    private fun senderAddressBuilder(fromAddress: String, senderName: String): String {
        try {
            val address = InternetAddress(fromAddress, senderName, "ISO-2022-JP")
            return address.toString()
        } catch (e: UnsupportedEncodingException) {
            println("Internet Address Constructor throws UnsupportedEncoding")
        }

        return fromAddress
    }

}
