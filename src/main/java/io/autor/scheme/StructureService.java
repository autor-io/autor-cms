package io.autor.scheme;

import io.autor.content.Fragment;
import io.autor.content.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stephan Grundner
 */
@Service
public class StructureService {

    private final Map<String, Structure> structures = new HashMap<>();

    public Structure findStructureByName(String name) {
        return structures.get(name);
    }

    public String resolveStructureName(Fragment fragment) {
        return fragment.getStructureName();
    }

    public String resolveStructureName(Page page) {
        return resolveStructureName(page.getContent());
    }

    public Structure resolveStructure(Fragment fragment) {
        String structureName = resolveStructureName(fragment);
        return findStructureByName(structureName);
    }

    public Structure resolveStructure(Page page) {
        if (page != null) {
            return resolveStructure(page.getContent());
        }

        return null;
    }

    public StructureBuilder createStructure(String name, int revision) {
        return new StructureBuilder(this, name, revision);
    }

    void registerStructure(Structure structure) {
        structures.put(structure.getName(), structure);
    }
}
