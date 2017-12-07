package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bean.Bean;
import com.bean.SpringBean;

public class XmlUtil
{
    @SuppressWarnings("unchecked")
    public static SpringBean XML2Bean(File xml)
    {
        SpringBean springBean = new SpringBean();
        List<Bean> beanList = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        SAXReader sax = new SAXReader();
        Document doc = null;
        try
        {
            doc = sax.read(xml);
        }
        catch (DocumentException e)
        {
            System.err.println("file parse fail! file = " + xml);
        }
        
        Element root = doc.getRootElement();
        List<Element> beans = root.elements();
        for (Element bean : beans)
        {
            Bean tempBean = new Bean();
            tempBean.setId(bean.attributeValue("id"));
            tempBean.setClassPath(bean.attributeValue("class"));
            idList.add(bean.attributeValue("id"));
            Map<String, String> propertyMap = new HashMap<>();
            List<Element> propertys = bean.elements();
            for (Element property : propertys)
            {
                String name = property.attributeValue("name").trim();
                String ref = property.attributeValue("ref").trim();
                if (propertyMap.containsKey(name))
                {
                    System.err.println(xml + "." + bean.attributeValue("id") + " already has key :" + name);
                }
                else
                {
                    propertyMap.put(name, ref);
                }
            }
            tempBean.setPropertys(propertyMap);
            beanList.add(tempBean);
        }
        springBean.setBeanList(beanList);
        springBean.setIdList(idList);
        return springBean;
    }
    
    @SuppressWarnings("unchecked")
    public static List<String> getDispList(File xml)
    {
        List<String> result = new ArrayList<>();
        SAXReader sax = new SAXReader();
        Document doc = null;
        try
        {
            doc = sax.read(xml);
        }
        catch (DocumentException e)
        {
            System.err.println("file parse fail! file = " + xml);
        }
        
        Element root = doc.getRootElement();
        Element actions = (Element)root.elements().get(0);
        List<Element> acts = actions.elements();
        for (Element action : acts)
        {
            result.add(action.attributeValue("path"));
        }
        return result;
    }
}
