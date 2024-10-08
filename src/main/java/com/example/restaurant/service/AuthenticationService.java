package com.example.restaurant.service;

import com.example.restaurant.dto.request.AuthenticationRequest;
import com.example.restaurant.dto.request.IntrospectRequest;
import com.example.restaurant.dto.response.AuthenticationResponse;
import com.example.restaurant.dto.response.IntrospectResponse;
import com.example.restaurant.entities.Users;
import com.example.restaurant.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.StringJoiner;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Value("${jwt.signerKey}")
    private String signerKey;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        // Tìm user theo username
        var users = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() ->
                new RuntimeException("Not found username!"));

        // Mã hóa password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // So sánh password nhập vào với password hash trong database
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPasswordHash(), users.getPasswordHash());

        if (!authenticated)
            throw new RuntimeException("Not authenticated!");

        var token = generateToken(users);

        // Trả về token và trạng thái xác thực
        return new AuthenticationResponse(token, authenticated);
    }

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();

        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        var verified = signedJWT.verify(verifier);

        Date expityRime = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .isValid(verified && expityRime.after(new Date()))
                .build();

    }

    private String generateToken(Users users) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(users.getUsername())
                .claim("idUser", users.getUserID())
                .issuer("Restaurant")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(30, ChronoUnit.DAYS)))
                .claim("scope", builtScope(users))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String builtScope(Users user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getUserType() != null && !user.getUserType().isEmpty())
            Arrays.stream(user.getUserType().split(" ")).forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
