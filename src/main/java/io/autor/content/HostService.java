package io.autor.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Stephan Grundner
 */
@Service
public class HostService {

    @Autowired
    private HostRepository hostRepository;

    public Host findHostByName(String name) {
        return hostRepository.findByName(name);
    }

    public Host saveHost(Host host) {
        return hostRepository.save(host);
    }

    public Host findOrCreateHostByName(String name) {
        Host host = findHostByName(name);
        if (host == null) {
            host = new Host(name);
            host = saveHost(host);
        }

        return host;
    }
}
