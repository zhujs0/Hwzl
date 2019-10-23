package com.springboot.hwzl.tools;

import com.thoughtworks.xstream.XStream;

import java.io.InputStream;

public class XmlUtils {

    private static  <T>  XStream setXStream(Class<T> clazz){
        XStream xstream=new XStream();
        xstream.ignoreUnknownElements();
        xstream.alias("xml",clazz);
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xstream.setClassLoader(clazz.getClassLoader());
        return  xstream;
    }


    public static <T> T toBean(InputStream inputStream, Class<T> clazz) throws Exception {
        XStream xstream=setXStream(clazz);
        return  (T) xstream.fromXML(inputStream);
    }

    public static <T> T toBean(String xml,Class<T> clazz) throws Exception {
        XStream xstream=setXStream(clazz);
        return  (T) xstream.fromXML(xml);
    }


    public static <T> String BeanToXml(T bean) throws Exception {
        XStream xstream=setXStream(bean.getClass());
        return  xstream.toXML(bean);
    }
}
