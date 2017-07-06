package com.wangtao.blog.common.util;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @ClassName:XmlUtils
 * @author: KarlWang
 * @Description: TODO(XML工具类) 
 * @date:2017年7月5日 下午4:46:36
 * @see com.wangtao.blog.common.util.XmlUtils
 */
public class XmlUtils {
	
	public final static XStream XSTREAM = new XStream();
	
	static{
		XSTREAM.autodetectAnnotations(true);
	}
	
	private XmlUtils(){}
	
	/**
     * Java对象转Xml字符串（序列化）
     * @param object
     * @return
     */
    public static String beanToXml(Object object) {
        XStream stream = new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    public void startNode(String name) {
                        // 去掉包名
                        if (name.indexOf(".") > -1) {
                            name = name.substring(name.lastIndexOf(".") + 1);
                        }
                        super.startNode(name);
                    }
                };
            }
        });
        stream.processAnnotations(object.getClass());//应用类注解
        stream.autodetectAnnotations(true);//自动检测注解
        return stream.toXML(object);
    }

    /**
     * Xml字符串转Java对象（反序列化）
     * @param xml
     * @param clazz
     * @return
     */
    public static Object xmlToBean(String xml, Class<? extends Object> clazz) {
        XStream xstream = new XStream(new DomDriver(null,new XmlFriendlyNameCoder("_-", "_")));
        xstream.processAnnotations(clazz);
        return xstream.fromXML(xml);
    }
}
