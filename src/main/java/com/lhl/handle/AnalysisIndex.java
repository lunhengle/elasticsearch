package com.lhl.handle;

import java.util.List;

/**
 * 索引.
 */
public final class AnalysisIndex {
    /**
     * 索引.
     */
    private String index;
    /**
     * 类型.
     */
    private String type;
    /**
     * 分页开始.
     */
    private Object from;
    /**
     * 分页默认条数.
     */
    private Object size;
    /**
     * 条件集合.
     */
    private List<AnalysisRow> analysisRows = null;

    /**
     * 排序集合.
     */
    private List<AnalysisSort> analysisSorts = null;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnalysisRow> getAnalysisRows() {
        return analysisRows;
    }

    public void setAnalysisRows(List<AnalysisRow> analysisRows) {
        this.analysisRows = analysisRows;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public List<AnalysisSort> getAnalysisSorts() {
        return analysisSorts;
    }

    public void setAnalysisSorts(List<AnalysisSort> analysisSorts) {
        this.analysisSorts = analysisSorts;
    }
}
