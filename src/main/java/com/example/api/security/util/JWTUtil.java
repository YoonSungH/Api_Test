package com.example.api.security.util;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
  private String secretKey = "1234567890abcdefghijklmnopqrstuvwxyz";
  private long expire = 60 * 24 * 30;

  public String generateToken(String content) throws Exception {
    return Jwts.builder()
        .issuedAt(new Date())
        .expiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
        .claim("sub", content)
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
        .compact();
  }
  public String validateAndExtract(String tokenStr) throws Exception {
    log.info("Jwts getClass: " +
        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
            .build().parse(tokenStr));
    Claims claims = (Claims) Jwts.parser().verifyWith(
        Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))).build().parse(tokenStr).getPayload();
    return (String) claims.get("sub");
  }
}
