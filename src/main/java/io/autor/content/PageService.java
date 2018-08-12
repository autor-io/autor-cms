package io.autor.content;

import io.autor.scheme.Structure;
import io.autor.scheme.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stephan Grundner
 */
@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private FragmentService fragmentService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private StructureService structureService;

    public List<Page> findAllPages() {
        return pageRepository.findAll();
    }

    public Page findPageById(Long id) {
        return pageRepository.findById(id).orElse(null);
    }

    public Page createPage(Structure structure, Bundle bundle) {
        Author author = authorService.currentAuthor();

        if (bundle == null) {
            bundle = new Bundle(author);
        }

        Fragment content = fragmentService.createFragment(structure, null);
        Page page = new Page(bundle, content);
        page.setModifiedBy(author);

        return page;
    }

    public Page createPage(Structure structure) {
        return createPage(structure, null);
    }

    public Page createPage(String structureName, Bundle bundle) {
        Structure structure = structureService.findStructureByName(structureName);
        return createPage(structure, bundle);
    }

    public Page createPage(String structureName) {
        return createPage(structureName, null);
    }

    public Page savePage(Page page) {
        return pageRepository.save(page);
    }
}
