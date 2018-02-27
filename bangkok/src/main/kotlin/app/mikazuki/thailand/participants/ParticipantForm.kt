package app.mikazuki.thailand.participants

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ParticipantForm(@field:NotNull @field:Size(min = 1, max = 40) var firstName: String = "",
                           @field:NotNull @field:Size(min = 1, max = 40) var lastName: String = "",
                           @field:NotNull @field:Size(min = 1, max = 200) var email: String = "",
                           @field:NotNull var gender: Boolean = true,
                           @field:NotNull var side: Boolean = true,
                           @field:NotNull var attendance: Boolean = true,
                           var message: String? = null,
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
        phone = phone
)