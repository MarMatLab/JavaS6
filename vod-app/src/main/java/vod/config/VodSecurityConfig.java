package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class VodSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
//        UserDetails user1 = User.withUsername("user1").password("user1")
//                .roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("user2").password("user2")
//                .roles("REGULAR").build();
//
//        return new InMemoryUserDetailsManager(user1, user2);

        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager();
        detailsManager.setDataSource(dataSource);
        detailsManager.setUsersByUsernameQuery("select username, password, 'true' from user where username=?");
        detailsManager.setAuthoritiesByUsernameQuery("select username, role from role where username=?");
        return detailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/webapi/figures").hasAuthority("USER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/webapi/shops").hasAuthority("USER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/webapi/shops/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
