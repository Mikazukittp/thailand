package app.mikazuki.thailand.party

import app.mikazuki.thailand.MailSenderService
import app.mikazuki.thailand.participants.ParticipantForm
import app.mikazuki.thailand.participants.ParticipantService
import app.mikazuki.thailand.participants.toParticipant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class PartyController @Autowired constructor(private val partyService: PartyService,
                                             private val participantService: ParticipantService,
                                             private val mailSenderService: MailSenderService) {

    @ModelAttribute
    fun setupForm(): ParticipantForm {
        return ParticipantForm()
    }

    @GetMapping("/parties/{hash}")
    fun input(@PathVariable("hash") hash: String,
              form: ParticipantForm,
              mav: ModelAndView): ModelAndView {
        val party = partyService.findByHash(hash)
        party ?: return ModelAndView("index")

        mav.viewName = "party/input"
        mav.addObject("party", party)
        mav.addObject("form", form)
        mav.addObject("hash", hash)
        return mav
    }

    @PostMapping("/parties/{hash}/confirm")
    fun confirm(@PathVariable("hash") hash: String,
                @Validated form: ParticipantForm,
                result: BindingResult,
                mav: ModelAndView): ModelAndView {
        if (result.hasErrors()) {
            mav.addObject("validationError", "不正な値が入力されました。");
            return input(hash, form, mav);
        }

        val party = partyService.findByHash(hash)
        party ?: return ModelAndView("index")

        mav.viewName = "party/confirm"
        mav.addObject("party", party)
        mav.addObject("form", form)
        mav.addObject("hash", hash)
        return mav
    }

    @PostMapping("/parties/{hash}/complete")
    fun complete(@PathVariable("hash") hash: String,
                 @Validated form: ParticipantForm): ModelAndView {
        val party = partyService.findByHash(hash)
        party ?: throw IllegalStateException("Invalid party")

        val participant = form.toParticipant(party.id)
        participantService.save(participant)
        mailSenderService.send(party, participant)
        return ModelAndView("party/complete")
    }

}