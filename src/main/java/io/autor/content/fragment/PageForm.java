package io.autor.content.fragment;

import io.autor.content.Page;

/**
 * @author Stephan Grundner
 */
public class PageForm implements Renderable {

    private Page page;

    private FragmentForm contentForm;

    private void rebuild() {
//        contentForm = new FragmentForm();
        contentForm.setFragment(page.getContent());
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;

        rebuild();
    }

    public FragmentForm getContentForm() {
        return contentForm;
    }
}
