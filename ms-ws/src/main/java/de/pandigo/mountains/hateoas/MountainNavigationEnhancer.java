package de.pandigo.mountains.hateoas;

import de.pandigo.mountains.controller.MountainController;
import de.pandigo.mountains.dto.Mountain;
import de.pandigo.commons.hateoas.HateoasAction;
import de.pandigo.commons.hateoas.ItemEnhancer;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

// TODO commons?

@Component
public class MountainNavigationEnhancer extends ItemEnhancer<Mountain> {

    public Mountain enhanceWithNavigation(Mountain mountain, List<Mountain> allMountains, Object selfAction) {
        if (allMountains != null) {
            int index = allMountains.indexOf(getMountainOfList(allMountains, mountain));
            if (index > 0) {
                mountain.add(linkTo(methodOn(MountainController.class)
                        .getMountain(allMountains.get(index-1).getMountainId())).withRel(ActionType.prev.toString()));
            }
            if (index < allMountains.size()-1) {
                mountain.add(linkTo(methodOn(MountainController.class)
                        .getMountain(allMountains.get(index+1).getMountainId())).withRel(ActionType.next.toString()));
            }
        }
        return super.enhance(mountain, selfAction,
                new HateoasAction(ActionType.showall.toString(), methodOn(MountainController.class).getAllMountains()));
    }

    private Mountain getMountainOfList(List<Mountain> mountains, Mountain mountain) {
        for (Mountain m : mountains) {
            if (m.getMountainId() == mountain.getMountainId()) {
                return m;
            }
        }
        return mountain;
    }

}