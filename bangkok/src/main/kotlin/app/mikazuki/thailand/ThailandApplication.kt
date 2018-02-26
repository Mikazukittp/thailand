package app.mikazuki.thailand

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class ThailandApplication

fun main(args: Array<String>) {
    SpringApplication.run(ThailandApplication::class.java, *args)
}
