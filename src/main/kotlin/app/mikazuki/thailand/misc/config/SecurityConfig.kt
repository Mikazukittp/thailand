package app.mikazuki.thailand.misc.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.authorizeRequests()
                .antMatchers("/view/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
        }
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)
        auth?.let {
            it.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // TODO: テスト用にハッシュ化していない
        return NoOpPasswordEncoder.getInstance()
    }
}