package app.mikazuki.thailand.web

import app.mikazuki.thailand.application.MailSenderService
import app.mikazuki.thailand.application.participant.ParticipantService
import app.mikazuki.thailand.application.party.PartyService
import app.mikazuki.thailand.application.place.PlaceService
import app.mikazuki.thailand.web.form.ParticipantForm
import app.mikazuki.thailand.web.form.toParticipant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
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
              model: ModelMap): String {
        val party = partyService.findByHash(hash)
        party ?: return "index"
        model.addAttribute("party", party)
        val place = placeService.findById(party.placeId).get()
        model.addAttribute("place", place)
        model.addAttribute("hash", hash)
        return "party/input"
    }

    @PostMapping("")
    fun confirm(@PathVariable("hash") hash: String,
                @ModelAttribute("form") @Valid form: ParticipantForm,
                result: BindingResult,
                model: ModelMap): String {
        if (result.hasErrors()) {
            model.addAttribute("message", Message("danger", "入力エラーです"))
            return input(hash, form, model)
        }

        val party = partyService.findByHash(hash)
        party ?: return "index"

        model.addAttribute("party", party)
        val place = placeService.findById(party.placeId).get()
        model.addAttribute("place", place)
        model.addAttribute("hash", hash)
        return "party/confirm"
    }

    @PostMapping("/complete")
    fun complete(@PathVariable("hash") hash: String,
                 @Validated form: ParticipantForm,
                 attributes: RedirectAttributes): String {
        val party = partyService.findByHash(hash)
        party ?: throw IllegalStateException("Invalid party")

        val participant = form.toParticipant(party.id)
        participantService.save(participant)
        mailService.send(party, participant)
        attributes.addFlashAttribute("message", Message("success", "登録が完了いたしました<br/>登録内容をメールに送信したのでご確認ください"))
        return "redirect:/parties/$hash"
    }

    data class Message(val type: String,
                       val body: String)
}