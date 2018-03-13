package app.mikazuki.thailand.web.form

import app.mikazuki.thailand.domain.Participant
import org.apache.commons.lang3.RandomStringUtils
import javax.validation.constraints.*

data class ParticipantForm(@field:NotBlank(message = "必須入力です")
                           @field:Size(max = 40, message = "最大文字数を超えています")
                           var firstName: String = "",
                           @field:NotBlank(message = "必須入力です")
                           @field:Size(max = 40, message = "最大文字数を超えています")
                           var lastName: String = "",
                           @field:NotBlank(message = "必須入力です")
                           @field:Size(max = 200, message = "最大文字数を超えています")
                           @field:Email(message = "正しいEメールアドレスを入力してください")
                           var email: String = "",
                           @field:NotNull var gender: Boolean = true,
                           @field:NotNull var side: Boolean = true,
                           @field:NotNull var attendance: Boolean = true,
                           var message: String? = null,
                           @field:Pattern(regexp = "(?:[0-9]{3}-?[0-9]{4})?", message = "入力値が不正です")
                           var postalCode: String? = null,
                           var address: String? = null,
                           var phone: String? = null)

fun ParticipantForm.toParticipant(partyId: Long) = Participant(
        partyId = partyId,
        firstName = firstName,
        lastName = lastName,
        email = email,
        gender = if (gender) 1 else 0,
        side = side,
        attendance = attendance,
        postalCode = postalCode,
        message = message,
        address = address,
        phone = phone,
        hash = createUserHash()
)

fun createUserHash() = RandomStringUtils.randomAlphanumeric(16)
