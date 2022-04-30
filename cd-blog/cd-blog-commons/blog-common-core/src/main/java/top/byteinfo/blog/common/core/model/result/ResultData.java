package top.byteinfo.blog.common.core.model.result;

import java.util.HashMap;

public class ResultData {


 public static <T,V>  HashMap<T,V> put(T t,V v){
      HashMap<T,V> hashMap = new HashMap<T,V>();
     return (HashMap<T, V>) hashMap.put(t,v);
  }


}
