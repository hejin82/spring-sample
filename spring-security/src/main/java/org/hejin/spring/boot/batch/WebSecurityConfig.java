package org.hejin.spring.boot.batch;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("test")
                .addScripts("classpath:/schema.sql").build();
    }

//     @Bean
//     @Override
//     public UserDetailsService userDetailsService() {
//     UserDetails user =
//     User.withDefaultPasswordEncoder()
//     .username("user")
//     .password("password")
//     .roles("USER")
//     .build();
//    
//     return new InMemoryUserDetailsManager(user);
//     }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication()
//         .withUser("admin@ya2do.io").password("secret").authorities("ADMIN","USER").and()
//         .withUser("marten@@ya2do.io").password("user").authorities("USER").and()
//         .withUser("jdoe@does.net").password("unknown").disabled(true).
//         authorities("USER");
        auth.jdbcAuthentication().dataSource(dataSource());
    }
}
