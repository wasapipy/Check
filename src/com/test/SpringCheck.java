package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.bean.Bean;
import com.bean.SpringBean;
import com.utils.XmlUtil;

public class SpringCheck
{
    private static String project = "CMU";
    
    private static final String SUFFIX = "\\src\\main\\resources\\config\\";
    
    private static final String JAVA_SUFFIX = "\\src\\main\\java\\";
    
    private static final String MANAGEMENTNAME = "applicationContext-management-impl.xml";
    
    private static final String SERVICENAME = "applicationContext-service-impl.xml";
    
    private static final String ACTIONNAME = "applicationContext-action-impl.xml";
    
    private static SpringBean manageBeans;
    
    private static SpringBean serviceBeans;
    
    private static SpringBean actionBeans;
    
    static
    {
        switch (project)
        {
            case "CTU":
                project = "D:\\eclipse-workspace\\ctu_trunk";
                break;
            case "CSU":
                project = "D:\\eclipse-workspace\\csu_trunk";
                break;
            case "CPU":
                project = "D:\\eclipse-workspace\\cpu_trunk";
                break;
            case "CMU":
                project = "D:\\eclipse-workspace\\cmu_trunk";
                break;
            case "CSNU":
                project = "D:\\eclipse-workspace\\csnu_trunk";
                break;
            
            default:
                System.err.println("project name error!");
                System.exit(0);
        }
        
        manageBeans = XmlUtil.XML2Bean(new File(project + SUFFIX + MANAGEMENTNAME));
        serviceBeans = XmlUtil.XML2Bean(new File(project + SUFFIX + SERVICENAME));
        actionBeans = XmlUtil.XML2Bean(new File(project + SUFFIX + ACTIONNAME));
    }
    
    public static void doCheck()
    {
        int count = manageBeans.getIdList().size() + serviceBeans.getIdList().size() + actionBeans.getIdList().size();
        List<String> beanPool = manageBeans.getIdList();
        beanPool.addAll(serviceBeans.getIdList());
        beanPool.addAll(actionBeans.getIdList());
        if (count != beanPool.size())
        {
            System.out.println(count + "|" + beanPool.size());
        }
        try
        {
            beanListCheck(manageBeans, beanPool);
            beanListCheck(serviceBeans, beanPool);
            beanListCheck(actionBeans, beanPool);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private static void beanListCheck(SpringBean beans, List<String> beanPool)
        throws IOException
    {
        List<Bean> beanList = beans.getBeanList();
        for (Bean bean : beanList)
        {
            String path = bean.getClassPath();
            Map<String, String> propertys = bean.getPropertys();
            path = project + JAVA_SUFFIX + path.replace(".", "\\").trim() + ".java";
            File javaFile = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(javaFile)));
            String line;
            while ((line = br.readLine()) != null)
            {
                if (((line.contains("Impl") || line.contains("Service") || line.contains("Manager")
                    || line.contains("Action")) && line.contains("private")) && !line.contains("("))
                {
                    line = line.substring(line.indexOf("private"));
                    String[] params = line.split(" ");
                    String name = params[params.length - 1].trim().replace(";", "");
                    String ref = params[params.length - 2].trim();
                    if (propertys.containsKey(name))
                    {
                        if (ref.equals(propertys.get(name)) || beanPool.contains(propertys.get(name))
                            || propertys.get(name).contains("dsf"))
                        {
                            propertys.remove(name);
                        }
                        else
                        {
                            System.err.println(bean.getId() + " is not match " + ref + " " + name);
                        }
                    }
                    else
                    {
                        System.err.println(bean.getId() + "缺少注入:" + ref + " " + name);
                    }
                }
            }
            br.close();
            if (propertys.containsKey("publisher"))
            {
                propertys.remove("publisher");
            }
            
            if (propertys.size() != 0)
            {
                System.err.println(bean.getId() + " has more service:" + propertys);
            }
        }
    }
    
    public static void main(String[] args)
    {
        SpringCheck.doCheck();
    }
}
