package org.example;

public interface Shape {
    default String toSvg(String tag){
        return String.format("<polygon points=\"%s\" %s />", tag);
    };
}
