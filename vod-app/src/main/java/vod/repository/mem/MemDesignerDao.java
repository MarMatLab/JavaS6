package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.repository.DesignerDao;
import vod.model.Designer;

import java.util.List;

//@Repository("designerDao")
public class MemDesignerDao implements DesignerDao {
    @Override
    public List<Designer> findAll() {
        return SampleData.designers;
    }

    @Override
    public Designer findById(int id) {
        return SampleData.designers.stream().filter(d -> d.getDesignerId() == id).findFirst().orElse(null);
    }

    @Override
    public Designer add(Designer d) {
        int max = SampleData.designers.stream().max((d1, d2) -> d1.getDesignerId() - d2.getDesignerId()).get().getDesignerId();
        d.setDesignerId(++max);
        SampleData.designers.add(d);
        return d;
    }
}
