package com.lin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.filter.JwtAuthenticationFilter;
import com.lin.filter.LoginFilter;
import com.lin.service.MyUserDetailService;
import com.lin.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManagerBean());
    }

    //配置数据库密码加密方式
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    final JwtUtils jwtUtils;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //自定义filter交给工厂管理
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setUsernameParameter("username");//指定接收json 用户名 key  可以更改
        loginFilter.setPasswordParameter("password");//指定接收 json 密码 key
        loginFilter.setKaptchaParameter("kaptcha");//指定接收 json 验证码 key
        //指定认证管理器
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        //认证成功处理
        loginFilter.setAuthenticationSuccessHandler((req, resp, authentication) -> {
            Response result = new Response();
            String jwt = jwtUtils.generateToken(authentication.getName());
            resp.setHeader(jwtUtils.getHeader(), jwt);
            result.setMsg("登陆成功");
            log.info("进入filter登录成功");
            result.setStatusCode(ResponseCode.ADMIN_LOGIN_SUCCESS);
            result.setData(authentication.getPrincipal());
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(HttpStatus.OK.value());
            String s = new ObjectMapper().writeValueAsString(result);
            resp.getWriter().println(s);
        });
        //认证失败处理
        loginFilter.setAuthenticationFailureHandler((req, resp, ex) -> {
            Response result = new Response();
            log.info("进入filter登录失败");
            result.setStatusCode(ResponseCode.API_LOGIN_FAILURE);
            //result.setMsg("登录失败: " + ex.getMessage());
            result.setMsg("登录失败:"+ex.getMessage());
            resp.setContentType("application/json;charset=UTF-8");
            String s = new ObjectMapper().writeValueAsString(result);
            resp.getWriter().println(s);
        });
        return loginFilter;
    }
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

       http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/kaptcha").permitAll()    //放行验证码
                .antMatchers("/getPassword").permitAll()    //放行验证码
                .antMatchers("/favicon.ico").permitAll()    //放行验证码
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/download").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3")
                .anyRequest().authenticated()//其余的都受保护  要写在permitAll()之后
                .and()

                //没有权限默认转跳登录页面，需要开启登录的页面
                .formLogin()
               .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /*.passwordParameter("pwd")
                .usernameParameter("user")//注意前端传的参数是什么，默认的是password和username

                .loginPage("/login")//指定默认登录页面   一旦指定了登录页面之后必须指定登录url

                .loginProcessingUrl("/toLogin")//指定登录请求url  前端发起这个请求（toLogin）就会被捕获,认证才回成功
                .successHandler(new MyAuthenticationSuccessHandler())//认证成功时的处理   前后端分离时的处理
                .failureHandler(new MyAuthenticationFailureHandler())//认证失败时的处理   前后端分离时的处理*/
        //toLogin是登录页面，但是login是登录认证

        //注销
                .and()
               .exceptionHandling()
               .authenticationEntryPoint((req, resp, ex) -> {
                   resp.setContentType("application/json;charset=UTF-8");
                   resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                   resp.getWriter().println("请认证之后再去处理!");
               })
               .accessDeniedHandler((req, resp, e) ->{
                   resp.setContentType("application/json;charset=UTF-8");
                   resp.setStatus(HttpStatus.FORBIDDEN.value());
                   resp.getWriter().println("权限不足!");
               })
                .and()
                .logout()
               /*.logoutRequestMatcher(new OrRequestMatcher(
                       new AntPathRequestMatcher("/logout", HttpMethod.DELETE.name()),
                       new AntPathRequestMatcher("/logout", HttpMethod.GET.name())
               ))*/
               .logoutSuccessHandler((req, resp, auth) -> {
                   if (auth != null){
                       new SecurityContextLogoutHandler().logout(req,resp,auth);
                   }
                   Response result = new Response();
                   result.setMsg("注销成功");
                   result.setStatusCode(ResponseCode.ADMIN_LOGOUT_SUCCESS);
                   //result.setData(auth.getPrincipal());
                   resp.setContentType("application/json;charset=UTF-8");
                   resp.setHeader(jwtUtils.getHeader(), " ");
                   String s = new ObjectMapper().writeValueAsString(result);
                   resp.getWriter().println(s);
               })
                .logoutSuccessUrl("/index");


        // at: 用来某个 filter 替换过滤器链中哪个 filter
        // before: 放在过滤器链中哪个 filter 之前
        // after: 放在过滤器链中那个 filter 之后
       http
               .addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class)
               .addFilter(jwtAuthenticationFilter());
        //防止网站攻击   get不安全，参数暴露   post更安全
        http.cors() //跨域
                .and()
                .csrf().disable();//关闭csrf 跨站请求保护 注销失败可能的原因

        //开启记住我功能  cookie,默认保存两周
        http.rememberMe()
                .rememberMeParameter("remember");//
    }

    private final MyUserDetailService myUserDetailService;

    @Autowired
    public SecurityConfig(MyUserDetailService myUserDetailService, JwtUtils jwtUtils) {
        this.myUserDetailService = myUserDetailService;
        this.jwtUtils = jwtUtils;
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(myUserDetailService);

    }
}
