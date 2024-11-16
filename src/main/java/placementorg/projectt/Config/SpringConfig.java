package placementorg.projectt.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import placementorg.projectt.Service.MyUserDetailService;
import placementorg.projectt.Service.PlacementLinkService;

@Configuration
public class SpringConfig  {
    @Autowired
    private MyUserDetailService myUerDetailService;
    @Autowired
    private PlacementLinkService placementLinkService;



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
                          .requestMatchers(HttpMethod.GET, "/placement-links/**").hasAnyRole("USER", "ADMIN")
                          .requestMatchers(HttpMethod.POST,"/placement-links").hasRole("ADMIN")
                          .anyRequest()
                          .authenticated())
                          .formLogin(form -> form.permitAll())
                          .logout(logout -> logout.permitAll())

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
