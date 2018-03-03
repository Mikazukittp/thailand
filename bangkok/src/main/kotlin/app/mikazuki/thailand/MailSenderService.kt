package app.mikazuki.thailand

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient
import com.amazonaws.services.simpleemail.model.*
import org.springframework.stereotype.Service


@Service
class MailSenderService {

    // TODO 変数は直す

    // Replace sender@example.com with your "From" address.
    // This address must be verified with Amazon SES.
    val FROM = "mikazuki.ttp@gmail.com"

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.
    val TO = "mikazuki.ttp@gmail.com"

    // The subject line for the email.
    val SUBJECT = "Amazon SES test (AWS SDK for Java)"

    // The HTML body for the email.
    val HTMLBODY = ("<h1>Amazon SES test (AWS SDK for Java)</h1>"
            + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
            + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
            + "AWS SDK for Java</a>")

    // The email body for recipients with non-HTML email clients.
    val TEXTBODY = "This email was sent through Amazon SES " + "using the AWS SDK for Java."

    private val ACCESS_KEY = "AKIAIZLAV3QSPGTJ7AGQ"
    private val SECRET_ACCESS_KEY = "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"

    fun send() {
        val client = AmazonSimpleEmailServiceClient(BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY))
                .withRegion<AmazonSimpleEmailServiceClient>(Regions.US_WEST_2)
        val request = SendEmailRequest()
                .withDestination(
                        Destination().withToAddresses(TO))
                .withMessage(Message()
                        .withBody(Body()
                                .withHtml(Content()
                                        .withCharset("UTF-8").withData(HTMLBODY))
                                .withText(Content()
                                        .withCharset("UTF-8").withData(TEXTBODY)))
                        .withSubject(Content()
                                .withCharset("UTF-8").withData(SUBJECT)))
                .withSource(FROM)

        println("Hi!")

        try {
            client.sendEmail(request)
        } catch (e: Exception) {
            print(e)
        }

        println("Email sent!")


    }
}