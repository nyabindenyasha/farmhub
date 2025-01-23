package com.xplug.tech.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * @author nyabindenyasha
 * @created 30/04/2021
 * @project - procurement-system
 **/

public interface PageBuilder<T> {

    Page<T> listToPage(List<T> listItems, Pageable pageable);

    Page<T> collectionToPage(Collection<T> collectionItems, Pageable pageable);

}
