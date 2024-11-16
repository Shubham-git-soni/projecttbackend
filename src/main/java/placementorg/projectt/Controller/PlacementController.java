package placementorg.projectt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import placementorg.projectt.Entity.PlacementLink;
import placementorg.projectt.Service.PlacementLinkService;

import java.util.List;
@RequestMapping("/placement-links")
@RestController
public class PlacementController {


    @Autowired
    private PlacementLinkService placementLinkService ;

    @GetMapping("/getlink")
    public List<PlacementLink>  getAllLinks() {
    return  placementLinkService.getAllPlacementLinks();
    }
//    @PostMapping
//    public PlacementLink addLink (@RequestBody PlacementLink placementLink )
//    {
//        return  placementLinkService.addPlacementLink(placementLink);
//    }

    @PostMapping
    public PlacementLink addLink(@RequestBody PlacementLink placementLink) {
        try {
            return placementLinkService.addPlacementLink(placementLink);
        } catch (Exception e) {
            e.printStackTrace();  // Log or handle the exception as needed
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving placement link", e);
        }
    }

}
