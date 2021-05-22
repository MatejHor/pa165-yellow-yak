package cz.fi.muni.pa165.yellow_yak.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.yellow_yak.facade.PlayerFacade;
import cz.fi.muni.pa165.yellow_yak.mixin.AdminDTOMixin;
import cz.fi.muni.pa165.yellow_yak.mixin.TokenDTOMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Matej Horniak
 */
@RestController
@RequestMapping("rest/")
public class AuthController {

    final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * Login
     * @return login token
     * @throws JsonProcessingException
     */
    @RequestMapping(value="login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TokenDTOMixin login(@RequestBody AdminDTOMixin auth) throws JsonProcessingException {
        logger.debug("rest AuthController()" + auth.getEmail());
        TokenDTOMixin token = new TokenDTOMixin();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] password = md.digest(auth.getPassword().getBytes(StandardCharsets.UTF_8));
            byte[] adminPassword = md.digest("admin".getBytes(StandardCharsets.UTF_8));
            boolean isAdmin = "admin@gaming.com".equals(auth.getEmail()) && Arrays.equals(adminPassword, password);
            if (isAdmin) {
                token.setToken("tokenADMIN");
            } else {
                token.setToken("tokenPLAYER");
            }
        } catch (Exception e) {
            throw new InvalidParameterException();
        }

        return token;
    }
}
