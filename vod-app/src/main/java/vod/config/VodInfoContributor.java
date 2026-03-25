package vod.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import vod.service.FigureService;

@Component
@RequiredArgsConstructor
public class VodInfoContributor implements InfoContributor {

    private final FigureService figureService;

    @Override
    public void contribute(Info.Builder builder)
    {
        builder.withDetail("figures", figureService.getAllFigures().size());
    }
}
