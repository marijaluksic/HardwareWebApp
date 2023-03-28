package hr.tvz.luksic.hardwareapp.security.service;

import hr.tvz.luksic.hardwareapp.security.command.LoginCommand;
import hr.tvz.luksic.hardwareapp.security.domain.Appuser;
import hr.tvz.luksic.hardwareapp.security.dto.LoginDTO;
import hr.tvz.luksic.hardwareapp.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(hr.tvz.luksic.hardwareapp.security.service.JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<Appuser> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }
    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        // TODO - implementirati provjeru odgovara li lozinka, koju je unio korisnik, enkriptiranoj lozinki u bazi
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if(b.matches(rawPassword, encryptedPassword)){
            return true;
        }else {

            throw new UnsupportedOperationException();
        }
    }
}
