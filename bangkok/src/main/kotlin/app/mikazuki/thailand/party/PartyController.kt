package app.mikazuki.thailand.party

import app.mikazuki.thailand.MailSenderService
import app.mikazuki.thailand.participants.ParticipantForm
import app.mikazuki.thailand.participants.ParticipantService
import app.mikazuki.thailand.participants.toParticipant
import app.mikazuki.thailand.place.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@RequestMapping("/parties/{hash}")
@Controller
class PartyController @Autowired constructor(private val partyService: PartyService,
                                             private val placeService: PlaceService,
                                             private val participantService: ParticipantService,
                                             private val mailService: MailSenderService) {

    @GetMapping("")
    fun input(@PathVariable("hash") hash: String,
              @ModelAttribute("form") form: ParticipantForm,
              mav: ModelAndView): ModelAndView {
        val party = partyService.findByHash(hash)
        party ?: return ModelAndView("index")

        mav.viewName = "party/input"
        mav.addObject("party", party)
        val place = placeService.findById(party.placeId).get()
        mav.addObject("place", place)
        mav.addObject("hash", hash)
        return mav
    }

    @PostMapping("")
    fun confirm(@PathVariable("hash") hash: String,
                @ModelAttribute("form") @Valid form: ParticipantForm,
                result: BindingResult,
                mav: ModelAndView): ModelAndView {
        if (result.hasErrors()) {
            mav.addObject("validationError", "不正な値が入力されました。")
            return input(hash, form, mav)
        }

        val party = partyService.findByHash(hash)
        party ?: return ModelAndView("index")

        mav.viewName = "party/confirm"
        mav.addObject("party", party)
        val place = placeService.findById(party.placeId).get()
        mav.addObject("place", place)
        mav.addObject("hash", hash)
        return mav
    }

    @PostMapping("/complete")
    fun complete(@PathVariable("hash") hash: String,
                 @Validated form: ParticipantForm): ModelAndView {
        val party = partyService.findByHash(hash)
        party ?: throw IllegalStateException("Invalid party")

        val participant = form.toParticipant(party.id)
        participantService.save(participant)
        mailService.send(party, participant)
        return ModelAndView("party/complete")
    }

}