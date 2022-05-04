package top.byteinfo.blog.x.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BeanUtils {

    /**
     *
     * @param source
     * @param target
     * @return
     * @param <T>
     */
    public static <T> T copyObject(Object source,Class<T> target){
        T temp = null;
        try {

            temp = target.newInstance();
            if (source != null) {
                org.springframework.beans.BeanUtils.copyProperties(source, temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp;
    }

    /**
     *
     * @param source
     * @param target
     * @return
     * @param <T>
     * @param <S>
     */
    public static <T,S> List<T> copyList(List<S> source,Class<T> target){
        List<T> list = new ArrayList<>();

        if (source!=null&&source.size()>0){
            for (S s : source) {
                list.add(BeanUtils.copyObject(s,target));
            }
        }
        return list;
    }
}
