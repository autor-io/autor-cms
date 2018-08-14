package io.autor.content;

import io.autor.mvc.ObjectService;
import io.autor.scheme.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Stephan Grundner
 */
@Controller
@RequestMapping(path = "/admin/pages/")
@SessionAttributes(types = {
        PageController.PageListing.class,
        PageController.PageEditor.class})
public class PageController {

    public static class PageListing {

        private List<Page> pages = new ArrayList<>();

        public List<Page> getPages() {
            return pages;
        }

        public void setPages(List<Page> pages) {
            this.pages = pages;
        }
    }

    public static class PageEditor {

        private Page page;

        public Page getPage() {
            return page;
        }

        public void setPage(Page page) {
            this.page = page;
        }
    }

    @Autowired
    private StructureService structureService;

    @Autowired
    private PageService pageService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ObjectService objectService;

    @ModelAttribute("dateFormat")
    public String dateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String pattern = dateFormat.toPattern();
//        pattern = MomentsUtils.javaToMomentFormat(pattern);

        return pattern;
    }

    @InitBinder("pageEditor")
    protected void initEditorBinder(WebDataBinder dataBinder) { }

    @ModelAttribute
    protected PageListing listing() {
        return new PageListing();
    }

    @ModelAttribute
    protected PageEditor editor() {
        return new PageEditor();
    }

    @GetMapping
    protected String list(@ModelAttribute PageListing listing) {

        List<Page> pages = pageService.findAllPages();
        listing.setPages(pages);

        return "admin/page/list";
    }

    private String redirect(PageEditor editor) {
        Page page = editor.getPage();
        if (page.getId() == null) {
            String structureName = structureService.resolveStructureName(page);
            return "redirect:edit?new=" + structureName;
        } else {
            return "redirect:edit?id=" + page.getId();
        }
    }

    @GetMapping(path = "/edit")
    protected String edit(@ModelAttribute PageEditor editor,
                          @RequestParam(name = "id", required = false) Long pageId,
                          @RequestParam(name = "new", required = false) String structureName) {

        return "admin/page/editor";
    }

    @GetMapping(path = "/load")
    protected String load(@ModelAttribute PageEditor editor,
                          @RequestParam(name = "id", required = false) Long pageId) {

        Page page = pageService.findPageById(pageId);
        editor.setPage(page);

        return redirect(editor);
    }

    @GetMapping(path = "/create")
    protected String create(@ModelAttribute PageEditor editor,
                            @RequestParam(name = "structure") String structureName) {

        Page page = pageService.createPage(structureName);
        editor.setPage(page);

        return redirect(editor);
    }

    @Transactional
    @PostMapping(path = "/save")
    protected String save(@Valid @ModelAttribute PageEditor editor,
                          BindingResult editorErrors) {

        pageService.savePage(editor.getPage());

        return redirect(editor);
    }

    @PostMapping(path = "/append")
    protected String append(@Valid @ModelAttribute PageEditor editor,
                            BindingResult editorErrors,
                            @RequestParam(name = "fragment") String bindingPath,
                            @RequestParam(name = "item") String itemName) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(editor);
        Fragment fragment = (Fragment) beanWrapper.getPropertyValue(bindingPath);
        Structure structure = structureService.resolveStructure(fragment);
        Item item = structure.getItems().get(itemName);

        Payload payload = item.createPayload();
        fragment.appendPayload(itemName, payload);

        if (item instanceof StructureItem) {
            ((Fragment) payload).setStructureName(structure.getName());
        }

        return redirect(editor);
    }

    @PostMapping(path = "/move-up")
    protected String moveUp(@ModelAttribute PageEditor editor,
                            @RequestParam(name = "fragment") String bindingPath,
                            @RequestParam(name = "item") String itemName,
                            @RequestParam(name = "index") int ordinal) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(editor);
        Fragment fragment = (Fragment) beanWrapper.getPropertyValue(bindingPath);
        List<Payload> sequence = fragment
                .getSequence(itemName);
        Payload payload = sequence.get(ordinal);
        fragment.movePayload(payload, payload.ordinal - 1);

        return redirect(editor);
    }

    @PostMapping(path = "/move-down")
    protected String moveDown(@ModelAttribute PageEditor editor,
                              @RequestParam(name = "fragment") String bindingPath,
                              @RequestParam(name = "item") String itemName,
                              @RequestParam(name = "index") int ordinal) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(editor);
        Fragment fragment = (Fragment) beanWrapper.getPropertyValue(bindingPath);
        List<Payload> sequence = fragment.getSequence(itemName);
        Payload payload = sequence.get(ordinal);
        fragment.movePayload(payload, payload.ordinal + 1);

        return redirect(editor);
    }

    @PostMapping(path = "/remove")
    protected String remove(@ModelAttribute PageEditor editor,
                            @RequestParam(name = "fragment") String bindingPath,
                            @RequestParam(name = "item") String itemName,
                            @RequestParam(name = "index") int index) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(editor);
        Fragment fragment = (Fragment) beanWrapper.getPropertyValue(bindingPath);
        List<Payload> sequence = fragment.getSequence(itemName);
        Payload payload = sequence.get(index);
        fragment.removePayload(payload);

        return redirect(editor);
    }

    @Transactional
    @PostMapping(path = "/upload")
    protected String upload(@ModelAttribute PageEditor editor,
                            BindingResult editorErrors,
                            @RequestParam(name = "token") String token,
                            @RequestParam(name = "payload") String payloadObjectId,
                            HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getMultiFileMap().getFirst("file-" + token);

        Payload payload = (Payload) objectService.findObject(request, payloadObjectId);
        Upload upload = uploadService.receive(multipartFile);
        ((Asset) payload).setUpload(upload);

        return redirect(editor);
    }
}
