package app.mikazuki.thailand.user

import app.mikazuki.thailand.party.PartyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class UserController @Autowired constructor(private val partyService: PartyService) {

    @GetMapping("/user")
    fun show(@AuthenticationPrincipal user: User?): ModelAndView {
        user ?: throw AccessDeniedException("")
        val mav = ModelAndView("user")
        mav.addObject("user", user)

        val parties = partyService.findAllByUserId(user.id)
        mav.addObject("parties", parties)
        return mav
    }
}