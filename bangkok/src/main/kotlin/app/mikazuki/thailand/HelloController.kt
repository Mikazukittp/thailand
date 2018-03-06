package app.mikazuki.thailand

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/")
    fun hello(): String {
        return "index"
    }
}