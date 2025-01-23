package com.xplug.tech.utils;

import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author nyabindenyasha
 * @created 30/04/2021
 * @project - procurement-system
 **/

@Component
public class PageBuilderImpl<T> implements PageBuilder<T> {

    @Override
    public Page<T> listToPage(List<T> listItems, Pageable pageable) {

        int start = (int) pageable.getOffset();

        int end = (start + pageable.getPageSize()) > listItems.size() ? listItems.size()
                : (start + pageable.getPageSize());

        Page<T> page
                = new PageImpl<T>(listItems.subList(start, end), pageable, listItems.size());

        return page;
    }

    @Override
    public Page<T> collectionToPage(Collection<T> collectionItems, Pageable pageable) {

        val listItems = (List<T>) collectionItems;

        int start = (int) pageable.getOffset();

        int end = (start + pageable.getPageSize()) > listItems.size() ? listItems.size()
                : (start + pageable.getPageSize());

        Page<T> page
                = new PageImpl<T>(listItems.subList(start, end), pageable, listItems.size());

        return page;
    }
}
