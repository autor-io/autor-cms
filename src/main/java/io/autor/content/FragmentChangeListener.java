package io.autor.content;

import java.util.EventObject;

/**
 * @author Stephan Grundner
 */
public interface FragmentChangeListener {

    abstract class FragmentChange extends EventObject {

        @Override
        public Fragment getSource() {
            return (Fragment) super.getSource();
        }

        public FragmentChange(Fragment source) {
            super(source);
        }
    }

    class PayloadAdded extends FragmentChange {

        private final Payload payload;

        public Payload getPayload() {
            return payload;
        }

        public PayloadAdded(Fragment source, Payload payload) {
            super(source);
            this.payload = payload;
        }
    }

    class PayloadRemoved extends FragmentChange {

        private final Payload payload;

        public Payload getPayload() {
            return payload;
        }

        public PayloadRemoved(Fragment source, Payload payload) {
            super(source);
            this.payload = payload;
        }
    }

//    class PayloadMoved extends FragmentChange {
//
//    }

    void fragmentChanged(FragmentChange change);
}
