package app.mikazuki.thailand

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient
import com.amazonaws.services.simpleemail.model.*
import org.springframework.stereotype.Service



@Service
class MailSenderService {

    private val ACCESS_KEY = "AKIAIZLAV3QSPGTJ7AGQ"
    private val SECRET_ACCESS_KEY = "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"
    private val FROM_ADDRESS = "mikazuki.ttp@gmail.com"
    
    fun send() {

        // TODO API連結時に修正する
        val toAddress = "mikazuki.ttp@gmail.com"
        val subject = "【テスト】出欠確認メール"
        val htmlBody = ("<h1>Amazon SES test (AWS SDK for Java)</h1>"
                + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
                + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
                + "AWS SDK for Java</a>")
        // The email body for recipients with non-HTML email clients.
        val textBody = "This email was sent through Amazon SES " + "using the AWS SDK for Java."

        val client = AmazonSimpleEmailServiceClient(BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY))
                .withRegion<AmazonSimpleEmailServiceClient>(Regions.US_WEST_2)
        val request = SendEmailRequest()
                .withDestination(
                        Destination().withToAddresses(toAddress))
                .withMessage(Message()
                        .withBody(Body()
                                .withHtml(Content()
                                        .withCharset("UTF-8").withData(htmlBody))
                                .withText(Content()
                                        .withCharset("UTF-8").withData(textBody)))
                        .withSubject(Content()
                                .withCharset("UTF-8").withData(subject)))
                .withSource(FROM_ADDRESS)


        try {
            client.sendEmail(request)
        } catch (e: Exception) {
            print(e)
        }

    }
}