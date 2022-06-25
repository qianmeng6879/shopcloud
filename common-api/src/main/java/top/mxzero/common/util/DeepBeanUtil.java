package top.mxzero.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DeepBeanUtil extends BeanUtils { // 扩充已有的Bean工具类
    private DeepBeanUtil() {}

    /**
     * 实现List集合对象的拷贝处理
     * @param sources 原始对象集合
     * @param target 目标对象集合
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     * @return 拷贝后的List集合
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List<T> list = new ArrayList<>(sources.size()); // 开辟一个新的List集合
        for (S source : sources) { // 集合迭代
            T obj = target.get(); // 获取数据
            copyProperties(source, obj); // 由父类所提供的拷贝方法
            list.add(obj); // 增加集合数据
        }
        return list; // 返回集合
    }
}
