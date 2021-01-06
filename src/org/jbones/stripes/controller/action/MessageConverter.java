package org.jbones.stripes.controller.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.action.Message;

public class MessageConverter {
   public static List<String> convert(List<Message> messageList) {
      List stringList = new ArrayList<String>();
      Iterator iterator = messageList.iterator();
      Message message = null;
      while (iterator.hasNext()) {
        message = (Message)iterator.next();
        stringList.add(convert(message));
      }
      return stringList;
   }
   public static String convert(Message message) {
      return message.getMessage(Locale.US);
   }
}