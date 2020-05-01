package com.taotao.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TbSysStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbSysStatisticsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Byte value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Byte value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Byte value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Byte value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Byte value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Byte> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Byte> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Byte value1, Byte value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Byte value1, Byte value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNull() {
            addCriterion("item_num is null");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNotNull() {
            addCriterion("item_num is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumEqualTo(Long value) {
            addCriterion("item_num =", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotEqualTo(Long value) {
            addCriterion("item_num <>", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThan(Long value) {
            addCriterion("item_num >", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThanOrEqualTo(Long value) {
            addCriterion("item_num >=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThan(Long value) {
            addCriterion("item_num <", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThanOrEqualTo(Long value) {
            addCriterion("item_num <=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumIn(List<Long> values) {
            addCriterion("item_num in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotIn(List<Long> values) {
            addCriterion("item_num not in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumBetween(Long value1, Long value2) {
            addCriterion("item_num between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotBetween(Long value1, Long value2) {
            addCriterion("item_num not between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumIsNull() {
            addCriterion("item_up_num is null");
            return (Criteria) this;
        }

        public Criteria andItemUpNumIsNotNull() {
            addCriterion("item_up_num is not null");
            return (Criteria) this;
        }

        public Criteria andItemUpNumEqualTo(Long value) {
            addCriterion("item_up_num =", value, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumNotEqualTo(Long value) {
            addCriterion("item_up_num <>", value, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumGreaterThan(Long value) {
            addCriterion("item_up_num >", value, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumGreaterThanOrEqualTo(Long value) {
            addCriterion("item_up_num >=", value, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumLessThan(Long value) {
            addCriterion("item_up_num <", value, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumLessThanOrEqualTo(Long value) {
            addCriterion("item_up_num <=", value, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumIn(List<Long> values) {
            addCriterion("item_up_num in", values, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumNotIn(List<Long> values) {
            addCriterion("item_up_num not in", values, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumBetween(Long value1, Long value2) {
            addCriterion("item_up_num between", value1, value2, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemUpNumNotBetween(Long value1, Long value2) {
            addCriterion("item_up_num not between", value1, value2, "itemUpNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumIsNull() {
            addCriterion("item_down_num is null");
            return (Criteria) this;
        }

        public Criteria andItemDownNumIsNotNull() {
            addCriterion("item_down_num is not null");
            return (Criteria) this;
        }

        public Criteria andItemDownNumEqualTo(Integer value) {
            addCriterion("item_down_num =", value, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumNotEqualTo(Integer value) {
            addCriterion("item_down_num <>", value, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumGreaterThan(Integer value) {
            addCriterion("item_down_num >", value, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_down_num >=", value, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumLessThan(Integer value) {
            addCriterion("item_down_num <", value, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumLessThanOrEqualTo(Integer value) {
            addCriterion("item_down_num <=", value, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumIn(List<Integer> values) {
            addCriterion("item_down_num in", values, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumNotIn(List<Integer> values) {
            addCriterion("item_down_num not in", values, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumBetween(Integer value1, Integer value2) {
            addCriterion("item_down_num between", value1, value2, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemDownNumNotBetween(Integer value1, Integer value2) {
            addCriterion("item_down_num not between", value1, value2, "itemDownNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumIsNull() {
            addCriterion("item_param_num is null");
            return (Criteria) this;
        }

        public Criteria andItemParamNumIsNotNull() {
            addCriterion("item_param_num is not null");
            return (Criteria) this;
        }

        public Criteria andItemParamNumEqualTo(Long value) {
            addCriterion("item_param_num =", value, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumNotEqualTo(Long value) {
            addCriterion("item_param_num <>", value, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumGreaterThan(Long value) {
            addCriterion("item_param_num >", value, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumGreaterThanOrEqualTo(Long value) {
            addCriterion("item_param_num >=", value, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumLessThan(Long value) {
            addCriterion("item_param_num <", value, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumLessThanOrEqualTo(Long value) {
            addCriterion("item_param_num <=", value, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumIn(List<Long> values) {
            addCriterion("item_param_num in", values, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumNotIn(List<Long> values) {
            addCriterion("item_param_num not in", values, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumBetween(Long value1, Long value2) {
            addCriterion("item_param_num between", value1, value2, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andItemParamNumNotBetween(Long value1, Long value2) {
            addCriterion("item_param_num not between", value1, value2, "itemParamNum");
            return (Criteria) this;
        }

        public Criteria andJobTimeIsNull() {
            addCriterion("job_time is null");
            return (Criteria) this;
        }

        public Criteria andJobTimeIsNotNull() {
            addCriterion("job_time is not null");
            return (Criteria) this;
        }

        public Criteria andJobTimeEqualTo(Date value) {
            addCriterion("job_time =", value, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeNotEqualTo(Date value) {
            addCriterion("job_time <>", value, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeGreaterThan(Date value) {
            addCriterion("job_time >", value, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("job_time >=", value, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeLessThan(Date value) {
            addCriterion("job_time <", value, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeLessThanOrEqualTo(Date value) {
            addCriterion("job_time <=", value, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeIn(List<Date> values) {
            addCriterion("job_time in", values, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeNotIn(List<Date> values) {
            addCriterion("job_time not in", values, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeBetween(Date value1, Date value2) {
            addCriterion("job_time between", value1, value2, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobTimeNotBetween(Date value1, Date value2) {
            addCriterion("job_time not between", value1, value2, "jobTime");
            return (Criteria) this;
        }

        public Criteria andJobDateIsNull() {
            addCriterion("job_date is null");
            return (Criteria) this;
        }

        public Criteria andJobDateIsNotNull() {
            addCriterion("job_date is not null");
            return (Criteria) this;
        }

        public Criteria andJobDateEqualTo(Date value) {
            addCriterionForJDBCDate("job_date =", value, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("job_date <>", value, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateGreaterThan(Date value) {
            addCriterionForJDBCDate("job_date >", value, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("job_date >=", value, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateLessThan(Date value) {
            addCriterionForJDBCDate("job_date <", value, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("job_date <=", value, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateIn(List<Date> values) {
            addCriterionForJDBCDate("job_date in", values, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("job_date not in", values, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("job_date between", value1, value2, "jobDate");
            return (Criteria) this;
        }

        public Criteria andJobDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("job_date not between", value1, value2, "jobDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}