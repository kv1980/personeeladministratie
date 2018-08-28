package be.vdab.personeeladministratie.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableGlobalMethodSecurity(prePostEnabled = true) 
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String USERS_BY_EMAIL =
			"select email as username, paswoord as password, 1 "+
			"from werknemers where email=?";
	private static final String AUTHORITIES_BY_EMAIL =
			"select email as username, 'gebruiker' "+
			"from werknemers where email=?";

	@Bean
	JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
		JdbcDaoImpl impl = new JdbcDaoImpl();
		impl.setDataSource(dataSource);
		//hieronder enkel methods om bestaande tabellen naar de vereiste tabellen om te zetten
		impl.setUsersByUsernameQuery(USERS_BY_EMAIL);
		impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_EMAIL);;
		return impl;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		   .mvcMatchers("/images/**")
		   .mvcMatchers("/css/**")
		   .mvcMatchers("/scripts/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login")
						.and().authorizeRequests()
						.mvcMatchers("/", "/login").permitAll()
						.mvcMatchers("/**").authenticated();
		//http.httpBasic(); // om ook niet-browser REST-clients basis authentication mogelijk te maken
	}
}