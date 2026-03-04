package vod.service;

import vod.model.Designer;
import vod.model.Figure;

import java.util.List;

public interface FigureService {


    List<Figure> getAllFigures();

    List<Figure> getFiguresByDesigner(Designer d);

    Figure getFigureById(int id);

    Figure addFigure(Figure m);


    List<Designer> getAllDesigners();

    Designer getDesignerById(int id);

    Designer addDesigner(Designer d);
}
