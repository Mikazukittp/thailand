package app.mikazuki.thailand

import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {

    protected var logger = LogFactory.getLog(javaClass)

    @GetMapping("/")
    @ResponseBody
    fun hello(): String {
        return "Hello World!!"
    }
}