package pl.pja.frontend.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("makeBooking").hasAnyRole("USER","ADMIN")
                .antMatchers("post/make/booking").hasAnyRole("USER","ADMIN")
                .antMatchers("get/bookings").hasAnyRole("USER","ADMIN")
                .antMatchers("updateUser").hasAnyRole("USER","ADMIN")
                .antMatchers("flights").hasAnyRole("USER","ADMIN")
                .antMatchers("userInfo").hasRole("ADMIN")
                .antMatchers("updateFlight").hasRole("ADMIN")
                .antMatchers("deleteFlight").hasRole("ADMIN")
                .antMatchers("deleteUser").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/homepage", true)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
