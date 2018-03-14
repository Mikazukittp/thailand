package app.mikazuki.thailand.web

import app.mikazuki.thailand.application.participant.ParticipantService
import app.mikazuki.thailand.application.party.PartyService
import app.mikazuki.thailand.application.place.PlaceService
import app.mikazuki.thailand.domain.Participant
import app.mikazuki.thailand.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class UserController @Autowired constructor(@Value("\${domain}") private val domain: String,
                                            private val partyService: PartyService,
                                            private val placeService: PlaceService,
                                            private val participantService: ParticipantService) {


    @GetMapping("/user")
    fun show(@AuthenticationPrincipal user: User?): ModelAndView {
        user ?: throw AccessDeniedException("")
        val mav = ModelAndView("user")
        val parties = partyService.findAllByUserId(user.id)
        if (parties.isNotEmpty()) {
            val party = parties.first()
            mav.addObject("party", party)
            val place = placeService.findById(party.placeId).get()
            mav.addObject("place", place)
            val participants = participantService.findAllByPartyId(party.id)
            mav.addObject("participants", participants)
            mav.addObject("participants_size", participants.size)
            mav.addObject("participants_attendance", participants.filter(Participant::attendance).size)
            mav.addObject("domain", domain)
        }
        return mav
    }
}