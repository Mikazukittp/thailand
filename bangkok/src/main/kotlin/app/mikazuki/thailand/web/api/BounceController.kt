package app.mikazuki.thailand.web.api

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
                                              @Value("\${aws.lambda.accesskey:}") private val accessKey: String,
                                              private val bounceRepository: BounceRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestHeader("token") token: String,
             @Validated @RequestBody emailBounce: EmailBounce): BounceResponse {

        if (token != accessKey) {
            return BounceResponse(503, "access denied", emailBounce.email)
        }

        bounceRepository.save(emailBounce)

        return BounceResponse(200, null, emailBounce.email)
    }

    data class BounceResponse(val statusCode: Int = 200,
                              val statusMessage: String?,
                              val email: String?)

}

