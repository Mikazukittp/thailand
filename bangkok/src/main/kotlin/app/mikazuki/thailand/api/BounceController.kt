package app.mikazuki.thailand.api

import app.mikazuki.thailand.domain.EmailBounce
import app.mikazuki.thailand.application.bounce.BounceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("api/bounces")
@RestController
class BounceController @Autowired constructor(@Value("\${domain}") private val domain: String,
                                              private val bounceRepository: BounceRepository) {


    @Value("\${aws.lambda.accesskey:}")
    lateinit var ACCESS_KEY: String

    inner class BounceResponse(statusCode: Int, statusMessage: String?, email: String?) {

        var statusCode: Int = 200
        var statusMessage: String? = null
        var email: String? = null

        init {
            this.statusCode = statusCode
            this.statusMessage = statusMessage
            this.email = email
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestHeader("token") token: String,
             @Validated @RequestBody emailBounce: EmailBounce): BounceResponse {

        if (token != ACCESS_KEY) {
            return BounceResponse(503, "access denied", emailBounce.email)
        }

        bounceRepository.save(emailBounce)

        return BounceResponse(200, null, emailBounce.email)
    }
}