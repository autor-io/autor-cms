package io.autor.content;

import io.autor.scheme.Item;
import io.autor.scheme.Structure;
import io.autor.scheme.StructureService;
import io.autor.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Stephan Grundner
 */
@Service
public class FragmentService {

    @Autowired
    private FragmentRepository fragmentRepository;

    @Autowired
    private StructureService structureService;

    private void applyPayloads(Fragment fragment) {
        String schemeQualifier = fragment.getItemName();
        String structureName = fragment.getStructureName();
        Structure structure = structureService.findStructureByName(structureName);

//        structure.getItems().forEach((itemName, item) -> {
//            List<Payload> sequence = fragment.getSequence(itemName);
//            int remaining = item.getRequired() - sequence.size();
//            while (remaining-- > 0) {
//
//            }
//        });
    }

    public Fragment createFragment(Structure structure, String itemName) {
        Fragment fragment = new Fragment(structure.getName());
        applyPayloads(fragment);

        return fragment;
    }
}
