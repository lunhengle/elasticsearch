package com.lhl.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhl.annotatioin.*;
import com.lhl.constants.Condition;
import com.lhl.constants.ConditionRow;
import com.lhl.constants.Match;
import com.lhl.constants.MatchRow;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * 解析es 对象.
 */
final class EsAnalysis {
    /**
     * 实例化.
     */
    private static EsAnalysis esAnalysis = new EsAnalysis();

    /**
     * 私有化构造函数.
     */
    private EsAnalysis() {

    }

    /**
     * 返回实例.
     *
     * @return 实例
     */
    static EsAnalysis getInit() {
        return esAnalysis;
    }

    /**
     * bool 类型.
     */
    private static final String BOOL = "bool";
    /**
     * filter 类型.
     */
    private static final String FILTER = "filter";
    /**
     * 查询类型.
     */
    private static final String QUERY = "query";
    /**
     * 分割类型.
     */
    private static final String PARTITION = "/";
    /**
     * 开始条数 label.
     */
    private static final String FROM_LABEL = "from";
    /**
     * 开始条数 value.
     */
    private static final int FROM_VALUE = 0;
    /**
     * 每页条数 label.
     */
    private static final String SIZE_LABEL = "size";
    /**
     * 每页条数 value.
     */
    private static final int SIZE_VALUE = 20;
    /**
     * 搜索后缀.
     */
    private static final String SEARCH_SUFFIX = "_search";
    /**
     * 区间.
     */
    private static final String RANGE = "range";
    /**
     * 分类.
     */
    private static final String SORT = "sort";
    /**
     * 排序.
     */
    private static final String ORDER = "order";

    /**
     * 解析.
     *
     * @param object 对象
     * @return 处理后json
     */
    final JSONObject analysis(Object object) {
        JSONObject jsonObject = new JSONObject();
        AnalysisIndex analysisIndex = this.analysisQueryAnnotation(object);
        String path = this.analysisPath(analysisIndex);
        jsonObject.put("path", path);
        JSONObject entry = this.analysisJSON(analysisIndex);
        jsonObject.put("entry", entry);
        return jsonObject;
    }

    /**
     * 解析 es 注解对象.
     *
     * @param object es 注解对象
     */
    private AnalysisIndex analysisQueryAnnotation(Object object) {
        AnalysisIndex analysisIndex = new AnalysisIndex();
        if (null == object) {
            return analysisIndex;
        }
        Class c = object.getClass();
        //解析 index
        this.analysisIndexAnnotation(analysisIndex, c);
        Field[] fields = c.getDeclaredFields();
        if (fields.length == 0) {
            return analysisIndex;
        }
        // row 行集合
        List<AnalysisRow> analysisRows = new ArrayList<>();
        //排序 集合
        List<AnalysisSort> analysisSorts = new ArrayList<>();
        for (Field field : fields) {
            Object value = null;
            try {
                //获取输入值
                field.setAccessible(true);
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //解析 sort 排序
            if (field.isAnnotationPresent(Sort.class)) {
                analysisSorts.add(this.analysisSortAnnotation(field));
            }
            //解析 page 分页
            if (field.isAnnotationPresent(PageSize.class)) {
                this.analysisPageAnnotation(field, value, analysisIndex);
            }
            //解析 row 行
            if (null != value && field.isAnnotationPresent(Row.class)) {
                if (value instanceof Object[]) { //判断是否是数组
                    Object[] objects = (Object[]) value;
                    for (Object o : objects) {
                        analysisRows.add(this.analysisRowAnnotation(field, o));
                    }
                } else {
                    analysisRows.add(this.analysisRowAnnotation(field, value));
                }
            }
        }
        analysisIndex.setAnalysisRows(analysisRows);
        analysisIndex.setAnalysisSorts(analysisSorts);
        return analysisIndex;
    }

    /**
     * 解析index.
     *
     * @param analysisIndex 解析对象
     * @param c             对象类
     */
    private void analysisIndexAnnotation(AnalysisIndex analysisIndex, Class c) {
        if (c.isAnnotationPresent(Index.class)) {
            Index index = (Index) c.getAnnotation(Index.class);
            analysisIndex.setIndex(index.index());
            analysisIndex.setType(index.type());
        }
    }

    /**
     * 解析 row.
     *
     * @param field 属性
     * @param value 属性值
     */
    private AnalysisRow analysisRowAnnotation(Field field, Object value) {
        AnalysisRow analysisRow = new AnalysisRow();
        if (field.isAnnotationPresent(Row.class)) {
            Row row = field.getAnnotation(Row.class);
            analysisRow.setName("".equals(row.name()) ? field.getName() : row.name());
            analysisRow.setCondition(row.condition());
            analysisRow.setMatch(row.match());
            analysisRow.setValue(value);
        }
        return analysisRow;
    }

    /**
     * 解析 page.
     *
     * @param field         属性
     * @param value         属性值
     * @param analysisIndex 解析对象
     */
    private void analysisPageAnnotation(Field field, Object value, AnalysisIndex analysisIndex) {
        //处理分页 开始
        if (field.isAnnotationPresent(PageFrom.class)) {
            analysisIndex.setFrom(value);
        }
        //处理分页 大小
        if (field.isAnnotationPresent(PageSize.class)) {
            analysisIndex.setSize(value);
        }
    }

    /**
     * 解析排序.
     *
     * @param field 属性
     * @return 解析对象
     */
    private AnalysisSort analysisSortAnnotation(Field field) {
        AnalysisSort analysisSort = new AnalysisSort();
        if (field.isAnnotationPresent(Sort.class)) {
            Sort sort = field.getAnnotation(Sort.class);
            analysisSort.setName("".equals(sort.name()) ? field.getName() : sort.name());
            analysisSort.setScore(sort.socre());
            analysisSort.setSort(sort.order());
        }
        return analysisSort;
    }

    /**
     * 解析路径.
     *
     * @param analysisIndex 注解实体
     * @return 路径
     */
    private String analysisPath(AnalysisIndex analysisIndex) {
        if (null == analysisIndex || "".equals(analysisIndex.getIndex()) || "".equals(analysisIndex.getType())) {
            return "";
        }
        return PARTITION + analysisIndex.getIndex() + PARTITION + analysisIndex.getType() + PARTITION + SEARCH_SUFFIX;
    }

    /**
     * 解析成 json.
     *
     * @param analysisIndex 注解实体
     * @return json
     */
    private JSONObject analysisJSON(AnalysisIndex analysisIndex) {
        JSONObject entryJson = new JSONObject();
        if (null == analysisIndex || "".equals(analysisIndex.getIndex()) || "".equals(analysisIndex.getType())) {
            entryJson.put(FROM_LABEL, FROM_VALUE);
            entryJson.put(SIZE_LABEL, SIZE_VALUE);
            return entryJson;
        }
        JSONObject queryJson = new JSONObject();
        JSONObject boolJson = new JSONObject();
        JSONObject filterJson = new JSONObject();
        JSONObject boolChildJson = new JSONObject();
        JSONArray mustJsonArray = new JSONArray();
        JSONArray mustNotJsonArray = new JSONArray();
        JSONArray shouldJsonArray = new JSONArray();
        //组织查询条件集合
        if (null != analysisIndex.getAnalysisRows()) {
            for (AnalysisRow analysisRow : analysisIndex.getAnalysisRows()) {
                if (null == analysisRow || "".equals(analysisRow.getName()) || "".equals(analysisRow.getValue()) || "".equals(Match.getValue(analysisRow.getMatch()))) {
                    continue;
                }
                JSONObject matchJson = new JSONObject();
                if (analysisRow.getMatch() == MatchRow.GT
                        || analysisRow.getMatch() == MatchRow.LT
                        || analysisRow.getMatch() == MatchRow.EQ
                        || analysisRow.getMatch() == MatchRow.GTE
                        || analysisRow.getMatch() == MatchRow.LTE) { //组织区间
                    JSONObject json = new JSONObject();
                    json.put(Match.getValue(analysisRow.getMatch()), analysisRow.getValue());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(analysisRow.getName(), json);
                    matchJson.put(RANGE, jsonObject);
                } else {
                    JSONObject json = new JSONObject();
                    json.put(analysisRow.getName(), analysisRow.getValue());
                    matchJson.put(Match.getValue(analysisRow.getMatch()), json);
                }

                if (analysisRow.getCondition() == ConditionRow.MUST) {
                    mustJsonArray.add(matchJson);
                } else if (analysisRow.getCondition() == ConditionRow.MUST_NOT) {
                    mustNotJsonArray.add(matchJson);
                } else if (analysisRow.getCondition() == ConditionRow.SHOULD) {
                    shouldJsonArray.add(matchJson);
                }
            }
        }
        JSONArray sortArray = new JSONArray();
        //组织排序
        if (null != analysisIndex.getAnalysisSorts()) {
            //按照评分排序
            analysisIndex.getAnalysisSorts().sort(new Comparator<AnalysisSort>() {
                @Override
                public int compare(AnalysisSort o1, AnalysisSort o2) {
                    return o2.getScore().compareTo(o1.getScore());
                }
            });
            for (AnalysisSort analysisSort : analysisIndex.getAnalysisSorts()) {
                JSONObject sortJson = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                if (null == analysisSort || "".equals(analysisSort.getName())) {
                    continue;
                }
                jsonObject.put(ORDER, com.lhl.constants.Sort.getValue(analysisSort.getSort()));
                sortJson.put(analysisSort.getName(), jsonObject);
                sortArray.add(sortJson);
            }
        }
        boolean addJson = true;
        if (mustJsonArray.size() == 0 && mustNotJsonArray.size() == 0 && shouldJsonArray.size() == 0) {
            addJson = false;
        } else {
            if (mustJsonArray.size() > 0) {
                boolChildJson.put(Condition.getValue(ConditionRow.MUST), mustJsonArray);
            }
            if (mustNotJsonArray.size() > 0) {
                boolChildJson.put(Condition.getValue(ConditionRow.MUST_NOT), mustNotJsonArray);
            }
            if (shouldJsonArray.size() > 0) {
                boolChildJson.put(Condition.getValue(ConditionRow.SHOULD), shouldJsonArray);
            }
        }
        if (addJson) {
            filterJson.put(BOOL, boolChildJson);
            boolJson.put(FILTER, filterJson);
            queryJson.put(BOOL, boolJson);
            entryJson.put(QUERY, queryJson);
        }
        //排序
        if (sortArray.size() != 0) {
            entryJson.put(SORT, sortArray);
        }
        //分页开始
        entryJson.put(FROM_LABEL, analysisIndex.getFrom() == null ? FROM_VALUE : analysisIndex.getFrom());
        //分页条数
        entryJson.put(SIZE_LABEL, analysisIndex.getSize() == null ? SIZE_VALUE : analysisIndex.getSize());
        return entryJson;
    }
}
/*
POST es_clue/t_clue/_search
{
  "size": "100",
  "query": {
    "bool": {
      "filter": {
        "bool": {
          "must_not": [
            {
              "match_phrase": {
                "city": "深圳市"
              }
            },
            {
              "match_phrase": {
                "legal_person": "马振豪"
              }
            },
            {
              "match_phrase": {
                "legal_person": "马振豪"
              }
            },
            {
              "match_phrase": {
                "legal_person": "张三"
              }
            }
          ],
          "should": [
            {
              "match_phrase": {
                "category": "电子商务"
              }
            },
            {
              "match_phrase": {
                "category": "电子商务"
              }
            },
            {
              "match_phrase": {
                "category": "互联网"
              }
            }
          ],
          "must": [
            {
              "match_phrase": {
                "company_name": "公司"
              }
            },
            {
              "match_phrase": {
                "province": "广东省"
              }
            },
            {
              "range": {
                "regist_time": {
                  "gte": "2016-07-06"
                }
              }
            },
            {
              "range": {
                "regist_time": {
                  "lte": "2016-07-15"
                }
              }
            }
          ]
        }
      }
    }
  },
  "from": 0,
  "sort": [

    {
      "regist_time": {
        "order": "desc"
      }
    }
  ]
}
 */