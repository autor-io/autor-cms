package io.autor.mvc;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Stephan Grundner
 */
@Service
public class ObjectService {

    private ObjectStore getOrCreateObjectStore(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        ObjectStore objectStore = (ObjectStore) session.getAttribute(ObjectStore.class.getName());
        if (objectStore == null) {
            objectStore = new ObjectStore();
            session.setAttribute(ObjectStore.class.getName(), objectStore);
        }

        return objectStore;
    }

    public String toId(HttpServletRequest request, Object object) {
        ObjectStore objectStore = getOrCreateObjectStore(request);

        return objectStore.getOrCreateId(object);
    }

    public Object findObject(HttpServletRequest request, String id) {
        ObjectStore objectStore = getOrCreateObjectStore(request);

        return objectStore.findObject(id);
    }
}
