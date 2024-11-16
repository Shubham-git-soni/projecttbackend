package placementorg.projectt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import placementorg.projectt.Entity.PlacementLink;
import placementorg.projectt.Repository.PlacementLinkRepository;

import java.util.List;

@Service
public class PlacementLinkService {

    @Autowired
    private PlacementLinkRepository placementLinkRepository ;


    public PlacementLink addPlacementLink ( @RequestBody  PlacementLink placementLink)
    {
        return placementLinkRepository.save(placementLink);
    }

    public List<PlacementLink> getAllPlacementLinks() {
        return placementLinkRepository.findAll();
    }




}
