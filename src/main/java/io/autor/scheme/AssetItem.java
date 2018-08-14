package io.autor.scheme;

import io.autor.content.Asset;
import io.autor.content.Payload;

/**
 * @author Stephan Grundner
 */
public class AssetItem extends ValueItem {

    @Override
    public Payload createPayload() {
        return new Asset();
    }

    public AssetItem(int capacity, int required) {
        super(capacity, required);
    }
}
