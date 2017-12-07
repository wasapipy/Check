package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.utils.XmlUtil;

public class DispatchCheck
{
    private static String projects[] = {"csnu_trunk", "cmu_trunk", "ctu_trunk", "csu_trunk", "cpu_trunk"};
    
    private static final String PREFIX = "D:\\eclipse-workspace\\";
    
    private static final String SUFFIX = "\\src\\main\\resources\\config\\dispatcher-config.xml";
    
    public static void check()
    {
        List<String> sum = new ArrayList<>();
        for (String project : projects)
        {
            File file = new File(PREFIX + project + SUFFIX);
            List<String> tempList = XmlUtil.getDispList(file);
            sum.addAll(tempList);
        }
        
        File file = new File("D:\\eclipse-workspace\\aserver_trunk\\conf\\aserver\\common\\dispatcher-config.xml");
        List<String> list = XmlUtil.getDispList(file);
        
        for (String aserver : list)
        {
            if (!sum.contains(aserver))
            {
                if (!sum.contains(aserver.trim()))
                {
                    System.out.println(aserver);
                }
                else
                {
                    System.out.println(aserver + "书写不规范!");
                }
            }
        }
    }
    
    public static void find(String xml)
    {
        for (String project : projects)
        {
            File file = new File(PREFIX + project + SUFFIX);
            List<String> tempList = XmlUtil.getDispList(file);
            if (tempList.contains(xml))
            {
                System.out.println(project + ":" + xml);
            }
        }
    }
    
    public static void main(String[] args)
    {
        check();
        // find("/getProductInfo");
    }
}
