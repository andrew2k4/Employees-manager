package com.Andrew.service.booking.user;

import com.Andrew.service.booking.user.dto.AuthentificationRequest;
import com.Andrew.service.booking.user.dto.SignupRequestDTO;
import com.Andrew.service.booking.user.dto.UserDto;
import com.Andrew.service.booking.security.jwt.UserDetailsServiceImpl;
import com.Andrew.service.booking.user.services.AuthService;
import com.Andrew.service.booking.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING =  "Authorization";

    @PostMapping("/employee/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO){
        System.out.println("andrew");
        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return  new ResponseEntity<>("Client already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createdUser  = authService.signupClient(signupRequestDTO);

        return  new ResponseEntity<>(createdUser, HttpStatus.OK);
    }


    @PostMapping("/manager/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO){

        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return  new ResponseEntity<>("Company already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createdUser  = authService.signupCompany(signupRequestDTO);

        return  new ResponseEntity<>(createdUser, HttpStatus.OK);
    }


    @PostMapping({"/authenticate"})
    public void createAuthenticationToken(@RequestBody AuthentificationRequest authentificationRequest, HttpServletResponse response) throws JSONException, IOException {
        System.out.println(authentificationRequest);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authentificationRequest.getUsername(), authentificationRequest.getPassword()
            ));
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }
System.out.println("2");
        final UserDetails userDetails =  userDetailsService.loadUserByUsername(authentificationRequest.getUsername());
        System.out.println("4");

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println("3");

        User user = userRepository.findFirstByEmail(authentificationRequest.getUsername());
        System.out.println("5");

        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                .toString()
        );



        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization" +
                "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");


        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

        // Important: Flush and close the writer
        response.getWriter().flush();
        response.getWriter().close();
    }
}
