package org.jbones.stripes.controller.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.FileBean;

import org.jbones.stripes.request.RequestUtil;

import org.jbones.core.*;
import org.jbones.core.util.*;
import org.jbones.core.dao.*;
import org.jbones.core.log.*;

import java.io.File;
import java.util.List;

public abstract class FileActionBean extends BaseActionBean implements ActionBean {
   private FileBean attachment;
   /**
   subclass should provide the return value for its class
   to construct the name
   */
   protected abstract String getDAOName();

   public FileBean getAttachment() {
       return attachment;
   }
   public void setAttachment(FileBean attachment) {
       this.attachment = attachment;
   }
   public String saveAttachment() {
       long timeInMillis = 0L;
       String savePath = "not initialized";
       try {
          timeInMillis = System.currentTimeMillis();
          savePath = getAppSaveUploadPath() + System.getProperty("file.separator") + timeInMillis + "_" + trimFileName(attachment.getFileName());
          attachment.save(new File(savePath));
          return savePath;
       } catch (Exception e) {
         Log.getLog(Log.ERR).log("problem saving attachment using : " + savePath);
         Log.getLog(Log.ERR).log(e.getMessage());
         Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
       } return null;
   }
   private String trimFileName(String fileName) {
      return fileName;
      // for now just return the filename, but lets
      // look to see how long the name can be and then trim 
      // it using the long leader values as well.
   }
}
