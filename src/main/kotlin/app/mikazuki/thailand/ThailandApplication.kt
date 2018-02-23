package app.mikazuki.thailand

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
class ThailandApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    SpringApplication.run(ThailandApplication::class.java, *args)
}
