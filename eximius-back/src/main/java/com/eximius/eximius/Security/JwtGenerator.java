package com.eximius.eximius.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    //Method to create a token via authentication
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentTime = new Date();
        Date expiryToken = new Date(currentTime.getTime() + ConstantsSecurity.JWT_EXPIRATION_TOKEN);

        //Line to generate the token
        String token = Jwts.builder() //Construct a JWT token
                .setSubject(username) //Set the login user name
                .setIssuedAt(new Date()) //Set token issuance date
                .setExpiration(expiryToken) //Set the expiration date of the token
                .signWith(SignatureAlgorithm.HS512, ConstantsSecurity.JWT_FIRM)/*We use this method to sign
                our token and thus prevent tampering or modification*/
                .compact(); //This method finalizes the token construction and converts it into a compact string.

        return token;
    }

    //Method to extract a username from a token
    public String getUsernameToJwt(String token) {
        Claims claims = Jwts.parser() //Parser method is used in order to parse the token.
                .setSigningKey(ConstantsSecurity.JWT_FIRM) //Sets the signing key, which is used to verify the signature of the token.
                .parseClaimsJws(token) // Used to verify the signature of the token, from String “token”.
                .getBody(); //We obtain the verified claims(body) of the token which will contain the information of the user name, expiration date and token signature
        return claims.getSubject(); //Return username
    }

    //Method to validate the token
    public boolean validateToken(String token){
        try {
            //Validation of the token through the signature containing the String token
            Jwts.parser().setSigningKey(ConstantsSecurity.JWT_FIRM).parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT is expired or incorrect");
        }
    }
}