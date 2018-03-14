package app.mikazuki.thailand.web.config

import app.mikazuki.thailand.application.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional

@EnableWebSecurity
@Configuration
class SecurityConfig @Autowired constructor(private val userDetailsService: UserService) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        super.configure(web)
        web?.ignoring()?.antMatchers("/css/**", "/js/**", "/img/**", "/font/**")
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            // @formatter:off
            it
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/parties/**").permitAll()
                .antMatchers("/login").anonymous()
                .antMatchers("/logout").authenticated()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .defaultSuccessUrl("/user")
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            // @formatter:on
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(CustomUserDetailsService(userDetailsService))
                ?.passwordEncoder(BCryptPasswordEncoder())
    }

    open inner class CustomUserDetailsService(private val service: UserService) : UserDetailsService {
        @Transactional
        override fun loadUserByUsername(username: String?): UserDetails {
            if (username?.isEmpty() != false) throw UsernameNotFoundException("")
            return service.findByNameOrEmail(username, username) ?: throw UsernameNotFoundException("")
        }
    }
}
