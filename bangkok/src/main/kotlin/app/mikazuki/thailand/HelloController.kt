package app.mikazuki.thailand

import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController  @Autowired constructor(private val mailSenderService: MailSenderService ){

    protected var logger = LogFactory.getLog(javaClass)

    @GetMapping("/")
    @ResponseBody
    fun hello(): String {

        // TODO 直す
        mailSenderService.send()

        return "Hello World!!"
    }
}