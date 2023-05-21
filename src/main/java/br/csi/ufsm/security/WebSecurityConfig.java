package br.csi.ufsm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    /*public DaoAuthenticationProvider authProvider(){

        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;

    }*/

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder builder) throws Exception{
        System.out.println("**************");
        builder.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authProvider());
    }*/


    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{

        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().
       csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                //.authenticationProvider(this.authProvider())
                .authorizeRequests()
                //.antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.POST, "/funcionario/login").permitAll()


                // 1:ADMIN  2:USER
                .antMatchers(HttpMethod.GET,"/funcionario/ver/{email}").permitAll()
                .antMatchers(HttpMethod.GET, "/funcionario/funcionarios").hasAnyAuthority("gerente")
                .antMatchers(HttpMethod.POST, "/funcionario/save").hasAnyAuthority( "gerente")
                .antMatchers(HttpMethod.PUT, "/funcionario/editar").hasAnyAuthority("gerente")
                .antMatchers(HttpMethod.DELETE, "/funcionario/delete/{email}").hasAnyAuthority( "gerente")

                //CLIENTES
                .antMatchers(HttpMethod.GET, "/cliente/clientes").permitAll()
                .antMatchers(HttpMethod.POST, "/cliente/save").hasAnyAuthority("gerente","funcionario")
                .antMatchers(HttpMethod.PUT, "/cliente/editar").hasAnyAuthority("gerente","funcionario")
                .antMatchers(HttpMethod.DELETE, "/cliente/delete/{id_cli}").hasAnyAuthority("gerente","funcionario")

                //lIVROS
                .antMatchers(HttpMethod.GET, "/livro/livros").hasAnyAuthority("gerente","funcionario")
                .antMatchers(HttpMethod.POST, "/livro/save").hasAnyAuthority("gerente","funcionario")
                .antMatchers(HttpMethod.PUT, "/livro/editar").hasAnyAuthority("gerente","funcionario")
                .antMatchers(HttpMethod.DELETE, "/livro/delete/{id_liv}").hasAnyAuthority("gerente","funcionario");


             //   .anyRequest()
             //   .denyAll();
        //.and().formLogin();
        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        System.out.println("configurando cors ....");
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");


        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

}
