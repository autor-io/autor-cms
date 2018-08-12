package io.autor.content;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Stephan Grundner
 */
@Entity
public class Fragment extends Payload {

    private String structureName;

    @OneToMany(mappedBy = "parent",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private final Set<Payload> payloads = new HashSet<>();

    public String getStructureName() {
        return structureName;
    }

    public Set<Payload> getPayloads() {
        return Collections.unmodifiableSet(payloads);
    }

    public Map<String, List<Payload>> getSequences() {
        Map<String, List<Payload>> map = payloads.stream()
                .collect(Collectors.groupingBy(Payload::getItemName));
        map.forEach((itemName, payloads) -> {
            payloads.sort((a, b) -> Integer.compare(a.getOrdinal(), b.getOrdinal()));
        });

        return Collections.unmodifiableMap(map);
    }

    public List<Payload> getSequence(String schemeQualifier) {
        List<Payload> result = payloads.stream()
                .filter(it -> Objects.equals(it.getItemName(), schemeQualifier))
                .sorted(Comparator.comparingInt(Payload::getOrdinal))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(result);
    }

    public void appendPayload(String itemName, Payload payload) {
        Payload last = payloads.stream()
                .filter(it -> Objects.equals(it.getItemName(), itemName))
                .max(Comparator.comparingInt(Payload::getOrdinal))
                .orElse(null);

        if (payloads.add(payload)) {
            if (last == null) {
                payload.ordinal = 0;
            } else {
                payload.ordinal = last.ordinal + 1;
            }

            payload.itemName = itemName;
            payload.parent = this;
        }
    }

    public void removePayload(Payload payload) {
        int ordinal = payload.ordinal;
        String itemName = payload.getItemName();

        if (payloads.remove(payload)) {
            payload.ordinal = -1;
            payload.itemName = null;
            payload.parent = null;
        }

        List<Payload> sequence = getSequence(itemName);
        ListIterator<Payload> payloadItr = sequence.listIterator(ordinal);
        while (payloadItr.hasNext()) {
            payload = payloadItr.next();
            payload.ordinal = payloadItr.previousIndex();
        }
    }

    public void movePayload(Payload payload, int ordinal) {
        String itemName = payload.getItemName();
        List<Payload> sequence = getSequence(itemName);
        Payload other = sequence.get(ordinal);
        other.ordinal = payload.ordinal;
        payload.ordinal = ordinal;
    }

    public Fragment(String structureName) {
        this.structureName = structureName;
    }

    public Fragment() { }
}
