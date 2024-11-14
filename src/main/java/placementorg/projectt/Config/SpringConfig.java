package placementorg.projectt.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import placementorg.projectt.Service.MyUerDetailService;

@Configuration
public class SpringConfig  {
    @Autowired
    private MyUerDetailService myUerDetailService;

      @Bean
     public PasswordEncoder passwordEncoder()
      {
          return new BCryptPasswordEncoder() ;
      }
      @Bean
      public UserDetailsService userDetailsService()
      {
          return myUerDetailService ;
      }
      @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity security) throws Exception {
          return security

                  .csrf(csrf->csrf.disable())

                  .authorizeHttpRequests(auth-> auth
                          .requestMatchers("/home","/register/**").permitAll()
                          .requestMatchers("/admin/**").hasRole("ADMIN")
                          .requestMatchers("/user/**").hasRole("USER")
                          .anyRequest()
                          .authenticated())
                          .formLogin(form ->form
                                  .loginPage("/login")
                                  .permitAll())
                  .build() ;

      }
      @Bean
      public AuthenticationProvider authenticationProvider()
      {
          DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
          provider.setUserDetailsService(myUerDetailService);
          provider.setPasswordEncoder(passwordEncoder());

          return provider ;
      }

}
