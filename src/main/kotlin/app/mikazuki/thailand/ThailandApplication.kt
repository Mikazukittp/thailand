package app.mikazuki.thailand

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class ThailandApplication : SpringBootServletInitializer() {

//    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder {
//        // Servletコンテナ起動時の設定クラス認識
//        // warファイル生成のため必要
//        return builder!!.sources(ThailandApplication::class.java)
//    }
}

fun main(args: Array<String>) {
    runApplication<ThailandApplication>(*args)
}
