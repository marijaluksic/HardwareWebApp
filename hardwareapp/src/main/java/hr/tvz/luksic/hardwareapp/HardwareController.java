package hr.tvz.luksic.hardwareapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService){
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    /*@GetMapping(params = "code")
    public HardwareDTO getHardwareByCode(@RequestParam final String code){
        return hardwareService.findByCode(code);
    }*/

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code){
        return hardwareService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/{up}/{down}")
    public List<HardwareDTO> findByLimits(@PathVariable final Integer up, @PathVariable final Integer down){
        return hardwareService.findByLimit(up, down).get();

    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand updatedHardwareCommand){
        return hardwareService.update(code, updatedHardwareCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }


    @Secured({"ROLE_ADMIN", "ROLE_DELETER"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }
}
