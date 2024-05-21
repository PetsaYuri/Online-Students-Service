package com.online.students.service.API.AssistanceCategories;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories-of-assistances")
public class AssistanceCategoryController {

    public static final String URI_ASSISTANCES_CATEGORIES_ID = "/{id}";

    private final AssistanceCategoryService assistanceCategoryService;
    private final AssistanceCategoryDTOMapper assistanceCategoryDTOMapper;

    public AssistanceCategoryController(AssistanceCategoryService assistanceCategoryService, AssistanceCategoryDTOMapper assistanceCategoryDTOMapper) {
        this.assistanceCategoryService = assistanceCategoryService;
        this.assistanceCategoryDTOMapper = assistanceCategoryDTOMapper;
    }

    @GetMapping
    public List<AssistanceCategoryDTO> getAll() {
        return assistanceCategoryService.getAll()
                .stream()
                .map(assistanceCategoryDTOMapper::apply)
                .toList();
    }

    @GetMapping(URI_ASSISTANCES_CATEGORIES_ID)
    public AssistanceCategoryDTO getById(@PathVariable Long id) {
        return assistanceCategoryDTOMapper.apply(assistanceCategoryService.getById(id));
    }

    @PostMapping
    public AssistanceCategoryDTO create(@RequestBody @Valid AssistanceCategoryDTO assistanceCategoryDTO) {
        return assistanceCategoryDTOMapper.apply(assistanceCategoryService.create(assistanceCategoryDTO));
    }

    @PutMapping(URI_ASSISTANCES_CATEGORIES_ID)
    public AssistanceCategoryDTO update(@PathVariable Long id, @RequestBody @Valid AssistanceCategoryDTO assistanceCategoryDTO) {
        return assistanceCategoryDTOMapper.apply(assistanceCategoryService.update(id, assistanceCategoryDTO));
    }

    @DeleteMapping(URI_ASSISTANCES_CATEGORIES_ID)
    public boolean delete(@PathVariable Long id) {
        return assistanceCategoryService.delete(id);
    }
}
