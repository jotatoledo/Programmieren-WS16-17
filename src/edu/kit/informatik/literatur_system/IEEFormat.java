package edu.kit.informatik.literatur_system;

import java.util.Collection;

public abstract class IEEFormat extends Bibliography {
    private final int order;
    
    protected IEEFormat(
            final Collection<AuthorNames> authors, final int order, 
            final short publicationYear, final String articleTitle) {
        super(authors, publicationYear, articleTitle);
        this.order = order;
    }
}
