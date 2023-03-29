package com.enigma.livecodeecommerce.configuration;



//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//    private final UserRepository repository;
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return username-> (UserDetails) repository.findByEmail(username)
//                .orElseThrow(()-> new NotFoundException("user not found"));
//    }
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
