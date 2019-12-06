package apap.tugas.situ.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/tambah").hasAnyAuthority("Admin TU")
                .antMatchers("/user/addUser").hasAnyAuthority("Admin TU")
                .antMatchers("/user/profile").permitAll()
                .antMatchers("/formulirPeminjaman").hasAnyAuthority("Admin TU")
                .antMatchers("/jenisLowongan/**").hasAnyAuthority("Admin TU")
                .antMatchers("/jenisSurat/**").hasAnyAuthority("Admin TU")
                .antMatchers("/lowongan/**").hasAnyAuthority("Admin TU")
                .antMatchers("/surat/ubahStatus").hasAnyAuthority("Admin TU", "Kepala Sekolah")
                .anyRequest().authenticated()
                .antMatchers("/*").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder())
//                .withUser("nadiem").password(encoder().encode("makarim"))
//                .roles("Admin TU");
//    }

    @Qualifier("userDetailsServiceImpl")

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
