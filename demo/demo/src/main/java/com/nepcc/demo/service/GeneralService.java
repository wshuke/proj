package com.nepcc.demo.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 该类专用于处理查询时的page和pageSize，避免出现代码的冗余
 */
@Service
public class GeneralService {

    public Integer processQueryPage(Map<String, Object> map){
        if(map.containsKey("page") && map.containsKey("pageSize")){
            try {
                return (Integer) map.get("page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //对分页做默认处理，默认输出第一页
        return 0;
    }
    public Integer processQueryPageSize(Map<String, Object> map){
        if(map.containsKey("page") && map.containsKey("pageSize")){
            try {
                return (Integer) map.get("pageSize");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //对分页做默认处理，默认输出前10条
        return 10;
    }

    public Predicate getAllAndPredicates(Root root, CriteriaBuilder criteriaBuilder, Map<String, Object> equalBy, String search, Map<String, Object> betweenBy){

        List<Predicate> predicates = new ArrayList<>();

        try {
            if(search != null && search != "") {            //如果有检索字符串，则仅针对name属性检索
                String wrapSearch = "%" + search + "%";
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class),wrapSearch)); //添加该筛选条件
            }

            if(betweenBy != null) {                         //遍历所有的区间筛选，betweenBy{ betweenProperty : [lowerLimit, upperLimit] }
                for (String betweenProperty : betweenBy.keySet()) {
                    if(betweenProperty == null || betweenProperty.equals(""))        //参数不能为空，否则会报错
                        continue;



                    Path path = root;
                    String[] splitName = betweenProperty.split("\\.|_");    //属性名解析
                    for(String part : splitName){                                   //由属性名逐层获取属性
                        path = path.get(part);
                    }
                    //获取区间上下限值，为List
                    List<Comparable> interval = (List<Comparable>) betweenBy.get(betweenProperty);

                    if(interval == null || interval.isEmpty())
                        continue;

                    try {
                        //先看能不能解析出Date，如果抛出ParseException，则不是Date;如果抛出ClassCastException则不是String
                        //区间参数为String形式的js的DateTime字符串时
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        predicates.add(criteriaBuilder.between(path, sdf.parse((String) interval.get(0)), sdf.parse((String) interval.get(1))));

                    } catch (ParseException | ClassCastException e) { //如果不是js的DateTime字符串，则应该还是其他Comparable，包括其他String
                        //e.printStackTrace();
                        //System.out.println("interval not Date");//调试用
                        //区间参数为Comparable时
                        predicates.add(criteriaBuilder.between(path, interval.get(0), interval.get(1)));    //添加该筛选条件
                    }
                }
            }

            if(equalBy != null){
                for(String name : equalBy.keySet()){        //遍历所有等值筛选条件，property{name : value}
                    if(name == null || name.equals(""))     //参数不能为空，否则会报错
                        continue;
                    Object value = equalBy.get(name);
                    if(value == null || value.equals(""))
                        continue;
                    Path path = root;
                    String[] splitName = name.split("\\.|_");   //属性名解析
                    for(String part : splitName){                       //由属性名逐层获取属性
                        path = path.get(part);
                    }

                    predicates.add(criteriaBuilder.equal(path,value));  //添加该筛选条件
                }
            }
        } catch (Exception e) {     //一旦出现异常，应将predicates清空
            e.printStackTrace();
            predicates.clear();
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public List<Sort.Order> getAllSortOrders(List<Map<String, Object>> sortBy){
        List<Sort.Order> orders=new ArrayList<>();

        try {
            if(sortBy != null) {
                Boolean containId = false;      //标记是否包含"id"字段
                for(Map<String, Object> property : sortBy){             //按顺序遍历所有要求排序的属性，前者先排，后者后排

                    for(String name : property.keySet()){               //加入排序规则

                        if(name != null && !name.equals("")) {          //参数不能为空，否则会报错
                            String od = (String) property.get(name);
                            if(od.equals("DESC") || od.equals("ASC")) { //排序规则的字符串必须时固定的这两个
                                /*order的参数名不需要像criteriaBuilder.equal的path那样一级一级地get，直接在字符串中用级联获得，如"device.user.name"*/
                                orders.add(new Sort.Order(Sort.Direction.valueOf(od), name));
                            }
                        }
                        if(name.equals("id"))   //包含"id"字段
                            containId = true;

                    }

                }
                if(!containId)
                    orders.add(new Sort.Order(Sort.Direction.ASC, "id"));   //没有指定id时的默认id排序规则--按id排升序
            }
            else{
                orders.add(new Sort.Order(Sort.Direction.ASC, "id"));       //默认排序规则--按id排升序
            }
        } catch (Exception e) {     //可能前端传入的排序参数不对，这时会有IllegalArgumentException异常抛出，为保证程序依然能够运行，需要捕获异常
            e.printStackTrace();
            orders.clear();         //一旦出现异常，应将orders清空
        }

        return orders;
    }
}
