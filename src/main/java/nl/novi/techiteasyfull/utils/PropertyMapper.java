package nl.novi.techiteasyfull.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class PropertyMapper {

    public static <T, U> void copyProperties(T source, U destination) {
        BeanUtils.copyProperties(source, destination);
    }
}


