package com.online.students.service.API.Assistances;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assistances")
public class AssistanceController {

    public static final String URI_ASSISTANCES_ID = "/{id}";

    private final AssistanceService assistanceService;
    private final AssistanceDTOMapper assistanceDTOMapper;

    public AssistanceController(AssistanceService assistanceService, AssistanceDTOMapper assistanceDTOMapper) {
        this.assistanceService = assistanceService;
        this.assistanceDTOMapper = assistanceDTOMapper;
    }

    @GetMapping
    public List<AssistanceDTO> getAll() {
        return assistanceService.getAll()
                .stream()
                .map(assistanceDTOMapper::apply)
                .toList();
    }

    @GetMapping(URI_ASSISTANCES_ID)
    public AssistanceDTO getById(@PathVariable Long id) {
        return assistanceDTOMapper.apply(assistanceService.getById(id));
    }

    @PostMapping
    public AssistanceDTO create(@RequestBody AssistanceDTO assistanceDTO) {
        return assistanceDTOMapper.apply(assistanceService.create(assistanceDTO));
    }

    @DeleteMapping(URI_ASSISTANCES_ID)
    public boolean delete(@PathVariable Long id) {
        return assistanceService.delete(id);
    }
}
