package org.jbones.stripes.controller.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

import org.jbones.stripes.request.RequestUtil;

import org.jbones.core.*;
import org.jbones.core.util.*;
import org.jbones.core.dao.*;
import org.jbones.core.log.*;

import java.util.List;
import java.util.Properties;

public abstract class BaseActionBean implements ActionBean {
   private ActionBeanContext context;
   private String DAOName = getDAOName();
   private static Properties pCore = null;
   private static Properties pIo = null;
   private static String APP_NAME = "APP_NAME not initialized";
   private static String APP_SAVE_UPLOAD_PATH = "APP_SAVE_UPLOAD_PATH not initialized";
   private static String APP_SAVE_DOWNLOAD_PATH = "APP_SAVE_DOWNLOAD_PATH not initialized";

   static {

      try {
          pCore = PropertiesUtil.loadPropertiesFileFromClassPath("/org/jbones/core/core.properties");
          pIo = PropertiesUtil.loadPropertiesFileFromClassPath("/org/jbones/core/io.properties");
          setAppName(pCore.getProperty("core.appname"));
          setAppSaveUploadPath(pIo.getProperty("path.save.upload"));
          setAppSaveDownloadPath(pIo.getProperty("path.save.download"));
      } catch (Exception e4) {
        System.out.println("logger failed to load its resources");
      }

   }
   private static void setAppName(String appName) {
      APP_NAME = appName;
   }
   public static String getAppName() {
      return APP_NAME;
   }
   private static void setAppSaveUploadPath(String arg) {
      APP_SAVE_UPLOAD_PATH = arg;
   }
   public static String getAppSaveUploadPath() {
      return APP_SAVE_UPLOAD_PATH;
   }
   private static void setAppSaveDownloadPath(String arg) {
      APP_SAVE_DOWNLOAD_PATH = arg;
   }
   public static String getAppSaveDownloadPath() {
      return APP_SAVE_DOWNLOAD_PATH;
   }

   /**
   subclass should provide the return value for its class
   to construct the name
   */
   protected abstract String getDAOName();
   public ActionBeanContext getContext() {
      return context;
   }
   public void setContext(ActionBeanContext context) {
      this.context = context;
   }
   protected DAO getDAO() {
      try {
         return (DAO)ServiceLocator.getDAO(getDAOName());
      } catch(ServiceLocatorException sle){
         Log.getLog(Log.ERR).log("problem looking up DAO:" + getDAOName());
         Log.getLog(Log.ERR).log(sle.getMessage());
         Log.getLog(Log.ERR).log(CoreException.getStackTrace(sle));
         return null;
      }
   }
   protected DAO getDAO(String arg) {
      try {
         return (DAO)ServiceLocator.getDAO(arg);
      } catch(ServiceLocatorException sle){
         Log.getLog(Log.ERR).log("problem looking up DAO:" + arg);
         Log.getLog(Log.ERR).log(sle.getMessage());
         Log.getLog(Log.ERR).log(CoreException.getStackTrace(sle));
         return null;
      }
   }
   protected void setMessagesOnActionBean(String messageListName) {
      RequestUtil.setMessagesOnActionBean((List)getContext().getRequest().getAttribute(messageListName), this);
   }
   protected Object[] getMessageParams(DTO arg) {
      if (null == arg) {
         return new Object[] {"[No Identifier]"};
      } else {
         return new Object[] {arg.getIdentifier(), arg.getUniqueFields()};
      }
   }
}
