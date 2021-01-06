package org.jbones.stripes.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Message;
import net.sourceforge.stripes.action.SimpleMessage;

public class RequestUtil {
   public static void setMessagesOnActionBean(List<Message> messages, ActionBean actionBean) {
      List<Message> messageCopies = null;
      if (null != messages && null != actionBean && null != actionBean.getContext().getMessages()) {
         messageCopies = new ArrayList<Message>(messages);
         actionBean.getContext().getMessages().clear();
         Iterator iterator = messageCopies.iterator();
         while (iterator.hasNext()) {
            actionBean.getContext().getMessages().add((SimpleMessage)iterator.next());
         }
      }
   }
}