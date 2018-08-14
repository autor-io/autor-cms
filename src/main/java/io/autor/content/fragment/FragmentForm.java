package io.autor.content.fragment;

import io.autor.content.Fragment;
import io.autor.content.FragmentChangeListener;
import io.autor.content.PageService;
import io.autor.content.Payload;
import io.autor.scheme.Structure;
import io.autor.scheme.StructureService;
import org.aspectj.apache.bcel.generic.FieldGen;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Stephan Grundner
 */
public class FragmentForm implements Renderable, FragmentChangeListener {

    private final StructureService structureService;
    private final PageService pageService;

    private Fragment fragment;

    private final Map<String, PayloadFieldGroup> fieldGroups = new HashMap<>();

    private void payloadAdded(Payload payload) {
        String itemName = payload.getItemName();
        PayloadFieldGroup fieldGroup = fieldGroups.get(itemName);
        fieldGroup.getFields().add(new TextField());
    }

    private void rebuild() {
        fieldGroups.clear();

        Structure structure = structureService.findStructureByName(
                fragment.getStructureName());

        structure.getItems().values().forEach(item -> {
            PayloadFieldGroup fieldGroup = new PayloadFieldGroup();
            List<Payload> sequence = fragment.getSequence(item.getName());
            sequence.forEach(this::payloadAdded);

            fieldGroups.put(item.getName(), fieldGroup);
        });
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;

        rebuild();
    }

    public Map<String, PayloadFieldGroup> getFieldGroups() {
        return Collections.unmodifiableMap(fieldGroups);
    }

    @Override
    public void fragmentChanged(FragmentChange change) {
        if (change.getSource() != fragment) {
            return;
        }

        if (change instanceof PayloadAdded) {
            Payload payload = ((PayloadAdded) change).getPayload();
            String itemName = payload.getItemName();

        }
    }

    public FragmentForm(StructureService structureService, PageService pageService) {
        this.structureService = structureService;
        this.pageService = pageService;
    }
}
