package app.mikazuki.thailand

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {

    @GetMapping("/")
    @ResponseBody
    fun hello(): String {
        return "Hello World!!"
    }
}