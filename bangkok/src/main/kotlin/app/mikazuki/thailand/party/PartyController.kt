package app.mikazuki.thailand.party

import app.mikazuki.thailand.participants.ParticipantForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView


@Controller
class PartyController @Autowired constructor(private val service: PartyService) {

    @GetMapping("/parties/{hash}")
    fun show(form: ParticipantForm,
             @PathVariable("hash") hash: String): ModelAndView {
        val party = service.findByHash(hash)
        party ?: return ModelAndView("index")

        val mav = ModelAndView("party")
        mav.addObject("party", party)
        mav.addObject("participantForm", ParticipantForm())
        return mav
    }
}