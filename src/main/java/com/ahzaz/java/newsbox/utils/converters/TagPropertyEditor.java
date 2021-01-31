package com.ahzaz.java.newsbox.utils.converters;

import com.ahzaz.java.newsbox.model.Tag;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Ahzaz
 */
final public class TagPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Arrays.stream(text.split(",")).map(Tag::new).collect(Collectors.toSet()));
    }
}