package app.mikazuki.thailand.web.api

import app.mikazuki.thailand.application.participant.ParticipantService
import app.mikazuki.thailand.application.party.PartyService
import app.mikazuki.thailand.domain.Participant
import app.mikazuki.thailand.domain.User
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/csv")
@Controller
class CsvController(private val partyService: PartyService,
                    private val participantService: ParticipantService) {

    @GetMapping(value = ["/participants.csv"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=Shift_JIS; Content-Disposition: attachment"])
    @ResponseBody
    fun download(@AuthenticationPrincipal user: User?): String? {
        user ?: throw AccessDeniedException("")
        val parties = partyService.findAllByUserId(user.id)
        val participants = if (parties.isNotEmpty()) participantService.findAllByPartyId(parties.first().id).map { it.toCsvModel() } else emptyList()
        val mapper = CsvMapper().registerKotlinModule() as CsvMapper
        val schema = mapper.schemaFor(Participant.ParticipantForCsv::class.java).withHeader()
        return mapper.writer(schema).writeValueAsString(participants)
    }

}