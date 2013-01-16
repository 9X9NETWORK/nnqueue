package com.nncloudtv.queue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

import com.nncloudtv.lib.NnNetUtil;
import com.nncloudtv.lib.QueueFactory;

public class Task {

	protected final static Logger log = Logger.getLogger(Task.class.getName());
	
	public static Object toObject (byte[] bytes)
	{
	    Object obj = null;
	    try {
	       ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
	       ObjectInputStream ois = new ObjectInputStream (bis);
	       obj = ois.readObject();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	    return obj;
	}	
	
	public static void doWork(byte[] message) {		
        Object[] obj = (Object[])Task.toObject(message);        
        String url = (String) obj[0];
        String method = (String) obj[1];
        String contentType = (String) obj[2];
        log.info("[x] url:" + url);
        if (method.equals(QueueFactory.METHOD_GET)) {
            log.info("[x] http get:" + url);
            NnNetUtil.urlGet(url);
        } else {        
            if (contentType.equals(QueueFactory.CONTENTTYPE_JSON)) {
                Object json = (Object) obj[3];
                log.info("[x] json:" + json);
                NnNetUtil.urlPostWithJson(url, json);            
            } else {
                String data = (String) obj[3];
                log.info("url:" + url);
                log.info("data:" + data);
                NnNetUtil.urlPost(url, data);
            }
        }
        /*
        if (json == null) {
        	NnNetUtil.urlGet(url);
        } else {
        	log.info("[x] json:" + json);
    		NnNetUtil.urlPostWithJson(url, json);
        }
		*/
	    log.info("[x] Done" );
	}
	
}
