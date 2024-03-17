package com.online.students.service.API.AssistanceCategories;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories-of-assistance")
public class AssistanceCategoryController {

    private static final String ASSISTANCE_CATEGORIES_ID_URI = "/{id}";

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

    @GetMapping(ASSISTANCE_CATEGORIES_ID_URI)
    public AssistanceCategoryDTO getById(@PathVariable Long id) {
        return assistanceCategoryDTOMapper.apply(assistanceCategoryService.getById(id));
    }

    @PostMapping
    public AssistanceCategoryDTO create(@RequestBody AssistanceCategoryDTO assistanceCategoryDTO) {
        return assistanceCategoryDTOMapper.apply(assistanceCategoryService.create(assistanceCategoryDTO));
    }

    @DeleteMapping(ASSISTANCE_CATEGORIES_ID_URI)
    public boolean delete(@PathVariable Long id) {
        return assistanceCategoryService.delete(id);
    }
}
