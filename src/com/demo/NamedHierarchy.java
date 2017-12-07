package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NamedHierarchy
{
    
    private static final Logger logger = LogManager.getLogger(NamedHierarchy.class);
    
    public static void main(String[] args)
    {
        String nh = "Named Hierarchy";
        logger.getLevel();
        logger.trace("TRACE: " + nh + " " + logger.getLevel());
        NamedHierarchy n = new NamedHierarchy();
        n.run();
        logger.error("ERROR: " + nh + " " + logger.getLevel());
        
    }
    
    public void run()
    {
        String nh = "NamedHierarchy.run";
        logger.debug("DEBUG: " + nh + " " + logger.getLevel());
    }
}
