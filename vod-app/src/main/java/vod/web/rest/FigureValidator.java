package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Designer;
import vod.model.Figure;
import vod.service.FigureService;
import vod.web.rest.dto.FigureDTO;

@Component
@RequiredArgsConstructor
public class FigureValidator implements Validator {

    private final FigureService figureService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FigureDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        FigureDTO figure = (FigureDTO) target;
        Designer designer = figureService.getDesignerById(figure.getDesignerId());

        if (designer == null)
        {
            errors.rejectValue("designerId", "figure.designer.missing");
        }
    }
}
