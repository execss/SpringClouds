package top.byteinfo.blog.common.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final Logger log = LoggerFactory.getLogger(TokenUtil.class);


    public static class JwtTokenBuilder {
        String secret;
        String issuer;
        Date ExpiresAt;
        String subject;
        String[] audience;
        Date NotBefore;
        Date IssuedAt;
        String JWTId;
        String claimK;
        String claimV;
        Map payloadClaims;

        JwtTokenBuilder() {
        }

        public JwtTokenBuilder secret(final String secret) {
            this.secret = secret;
            return this;
        }

        public JwtTokenBuilder issuer(final String issuer) {
            this.issuer = issuer;
            return this;
        }

        public JwtTokenBuilder ExpiresAt(final Date ExpiresAt) {
            this.ExpiresAt = ExpiresAt;
            return this;
        }

        public JwtTokenBuilder subject(final String subject) {
            this.subject = subject;
            return this;
        }

        public JwtTokenBuilder audience(final String... audience) {
            this.audience = audience;
            return this;
        }

        public JwtTokenBuilder NotBefore(final Date NotBefore) {
            this.NotBefore = NotBefore;
            return this;
        }

        public JwtTokenBuilder IssuedAt(final Date IssuedAt) {
            this.IssuedAt = IssuedAt;
            return this;
        }

        public JwtTokenBuilder JWTId(final String JWTId) {
            this.JWTId = JWTId;
            return this;
        }

        public JwtTokenBuilder claim(final String claimK,final String claimV) {
            this.claimK = claimK;
            this.claimV=claimV;
            return this;
        }

        public JwtTokenBuilder payloadClaims(final Map payloadClaims) {
            this.payloadClaims = payloadClaims;
            return this;
        }


        public String build() {
//            return generateToken(this.issuer,this.ExpiresAt,this.subject,this.audience,this.NotBefore,this.IssuedAt,this.JWTId,this.claim,this.payloadClaims
//            );
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)//签发者
                    .withExpiresAt(ExpiresAt)//过期时间
                    .withSubject(subject) //主题 签发对象
                    .withAudience(audience)// String 数组 compareTo withPayload()
                    .withNotBefore(NotBefore)
                    .withIssuedAt(IssuedAt)
                    .withJWTId(JWTId)// token id
                    .withClaim(claimK, claimV)//assertNonNull(name) TODO refer redis or mongodb
                    .withClaim("admin", 123456)
                    .withPayload(payloadClaims)
                    .sign(algorithm);
        }
    }

public static class JwtVerifierBuilder{
    String secret;
    String issuer;
    Date ExpiresAt;
    String subject;
    String[] audience;
    Date NotBefore;
    Date IssuedAt;
    String JWTId;
    String claimK;
    String claimV;
    Long number;
    Map payloadClaims;
    JwtVerifierBuilder(){

    }
    public JwtVerifierBuilder secret(final String secret) {
        this.secret = secret;
        return this;
    }

    public JwtVerifierBuilder issuer(final String issuer) {
        this.issuer = issuer;
        return this;
    }



    public JwtVerifierBuilder subject(final String subject) {
        this.subject = subject;
        return this;
    }

    public JwtVerifierBuilder audience(final String... audience) {
        this.audience = audience;
        return this;
    }



    public JwtVerifierBuilder JWTId(final String JWTId) {
        this.JWTId = JWTId;
        return this;
    }

    public JwtVerifierBuilder claim(final String claimK,final String claimV) {
        this.claimK = claimK;
        this.claimV= claimV;
        return this;
    }

    public JwtVerifierBuilder payloadClaims(final Map payloadClaims) {
        this.payloadClaims = payloadClaims;
        return this;
    }

    public JWTVerifier build() {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withSubject(subject)
                .withAudience(audience)
                .withJWTId(JWTId)
                //TODO advanced application
//                .acceptExpiresAt(number)
//                .acceptIssuedAt(number)
//                .acceptLeeway(number)
//                .acceptNotBefore(number)
//                .ignoreIssuedAt()
                // TODO
                .withClaim(claimK, claimV)
                .withClaim("admin", 123456)
                .build(); //Reusable verifier instance
        return verifier;
    }
}
    public static JwtVerifierBuilder verifierBuilder() {
        return new JwtVerifierBuilder();
    }

    public static JwtTokenBuilder tokenBuilder() {
        return new JwtTokenBuilder();
    }



//    public static String generateToken(
//            String issuer,
//            Date ExpiresAt,
//            String subject,
//            String audience,
//            Date NotBefore,
//            Date IssuedAt,
//            String JWTId,
//            String claim,
//            Map payloadClaims
//
//
//    ) {
//        Algorithm algorithm = Algorithm.HMAC256("secret");
//        String token = JWT.create()
//                .withIssuer(issuer)
//                .withExpiresAt(ExpiresAt)
//                .withSubject(subject)
//                .withAudience(audience)
//                .withNotBefore(NotBefore)
//                .withIssuedAt(IssuedAt)
//                .withJWTId(JWTId)
//                .withClaim("admin", 123456)
//                .withPayload(payloadClaims)
//                .sign(algorithm);
//        return  token;
//    }


    /**
     * ============================================================================================
     */
    public static String generateToken(String ApiMethod) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        /** secret complex processing */

        //        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        //        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Date date = new Date();
        String token = JWT.create()
                .withIssuer("auth0")
                .withExpiresAt(new Date(2022, 04, 19, 00, 00))
                .withSubject("spring security")
                .withAudience("admin")
                .withNotBefore(date)
                .withIssuedAt(date)
                .withJWTId(ApiMethod)
                .withClaim("name", 123)
                .sign(algorithm);
        return token;
    }


    public static Boolean verifyToken(String token,String ApiMethod) {
        Boolean b = null;
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .withClaim("name", 123)
                    .withJWTId(ApiMethod)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            log.info(" DecodedJWT jwt = verifier.verify(token); end");
            if (jwt == null) {
                log.error("DecodedJWT jwt = verifier.verify(token); error");
            } else {
                b = true;
                log.info("DecodedJWT jwt = verifier.verify(token); ok");
            }


        return b;
    }

    public static String generateToken(String ApiMethod, Boolean privateClaims) {
        // you can specify a custom Claim by calling withClaim() and passing
        // calling withPayload() and passing a map of claim names to values:
        Map<String, Object> payloadClaims = new HashMap<String, Object>();
        payloadClaims.put("@context", "https://auth0.com/");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withClaim("name", 123)
                .withArrayClaim("array", new Integer[]{1, 2, 3})
                .withPayload(payloadClaims)
                .sign(algorithm);

        return token;
    }

    public static void verifyToken(String token, Boolean privateClaims) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .withArrayClaim("array", 1, 2, 3)
                .withClaim("name", 123)
                .build();
        verifier.verify(token);

    }

//    public static String generateToken(String ApiMethod, Boolean privateClaims, Boolean key) {
//        ECPrivateKey privateKey = null;
//        Algorithm algorithm = null;
//        try {
//            algorithm = Algorithm.ECDSA512
//                    ((ECPublicKey) PemUtils.readPublicKeyFromFile("src/main/resources/id_ed25519_pub.pem", "EC")
//                            , (ECPrivateKey) PemUtils.readPrivateKeyFromFile("src/main/resources/id_ed25519.pem", "EC"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        String token = JWT.create()
//                .sign(algorithm);
//
//
//        return token;
//    }
//
//    public static void verifyToken(String token, Boolean privateClaims, Boolean key) {
//
////        Algorithm algorithm = Algorithm.HMAC256("secret");
//        ECKey eckey = null;
//        try {
//            eckey = (ECKey) PemUtils.readPublicKeyFromFile("src/main/resources/id_ed25519.pem", "EC");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        try {
//            Algorithm algorithm = Algorithm.ECDSA512(eckey);
//
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);
//        } catch (JWTVerificationException jwtVerificationException) {
//            // Invalid signature/claims
//            log.error("", jwtVerificationException);
//        }
//    }


}
