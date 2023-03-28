package hr.tvz.luksic.hardwareapp.security.service;

import hr.tvz.luksic.hardwareapp.security.command.LoginCommand;
import hr.tvz.luksic.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
