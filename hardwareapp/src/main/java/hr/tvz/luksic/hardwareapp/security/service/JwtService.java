package hr.tvz.luksic.hardwareapp.security.service;

import hr.tvz.luksic.hardwareapp.security.domain.Appuser;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(Appuser user);

}
