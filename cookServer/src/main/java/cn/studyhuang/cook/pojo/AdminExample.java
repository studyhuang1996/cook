package cn.studyhuang.cook.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdminExample {
    protected String orderByClause;

    protected boolean distinct;
    
    protected Integer page;
    
    protected Integer pageSize;

    protected List<Criteria> oredCriteria;

    public AdminExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

        public Criteria andAidIsNull() {
            addCriterion("aid is null");
            return (Criteria) this;
        }

        public Criteria andAidIsNotNull() {
            addCriterion("aid is not null");
            return (Criteria) this;
        }

        public Criteria andAidEqualTo(Long value) {
            addCriterion("aid =", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotEqualTo(Long value) {
            addCriterion("aid <>", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThan(Long value) {
            addCriterion("aid >", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThanOrEqualTo(Long value) {
            addCriterion("aid >=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThan(Long value) {
            addCriterion("aid <", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThanOrEqualTo(Long value) {
            addCriterion("aid <=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidIn(List<Long> values) {
            addCriterion("aid in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotIn(List<Long> values) {
            addCriterion("aid not in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidBetween(Long value1, Long value2) {
            addCriterion("aid between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotBetween(Long value1, Long value2) {
            addCriterion("aid not between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAnameIsNull() {
            addCriterion("aname is null");
            return (Criteria) this;
        }

        public Criteria andAnameIsNotNull() {
            addCriterion("aname is not null");
            return (Criteria) this;
        }

        public Criteria andAnameEqualTo(String value) {
            addCriterion("aname =", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotEqualTo(String value) {
            addCriterion("aname <>", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThan(String value) {
            addCriterion("aname >", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameGreaterThanOrEqualTo(String value) {
            addCriterion("aname >=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThan(String value) {
            addCriterion("aname <", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLessThanOrEqualTo(String value) {
            addCriterion("aname <=", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameLike(String value) {
            addCriterion("aname like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotLike(String value) {
            addCriterion("aname not like", value, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameIn(List<String> values) {
            addCriterion("aname in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotIn(List<String> values) {
            addCriterion("aname not in", values, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameBetween(String value1, String value2) {
            addCriterion("aname between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andAnameNotBetween(String value1, String value2) {
            addCriterion("aname not between", value1, value2, "aname");
            return (Criteria) this;
        }

        public Criteria andApasswordIsNull() {
            addCriterion("apassword is null");
            return (Criteria) this;
        }

        public Criteria andApasswordIsNotNull() {
            addCriterion("apassword is not null");
            return (Criteria) this;
        }

        public Criteria andApasswordEqualTo(String value) {
            addCriterion("apassword =", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordNotEqualTo(String value) {
            addCriterion("apassword <>", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordGreaterThan(String value) {
            addCriterion("apassword >", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordGreaterThanOrEqualTo(String value) {
            addCriterion("apassword >=", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordLessThan(String value) {
            addCriterion("apassword <", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordLessThanOrEqualTo(String value) {
            addCriterion("apassword <=", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordLike(String value) {
            addCriterion("apassword like", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordNotLike(String value) {
            addCriterion("apassword not like", value, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordIn(List<String> values) {
            addCriterion("apassword in", values, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordNotIn(List<String> values) {
            addCriterion("apassword not in", values, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordBetween(String value1, String value2) {
            addCriterion("apassword between", value1, value2, "apassword");
            return (Criteria) this;
        }

        public Criteria andApasswordNotBetween(String value1, String value2) {
            addCriterion("apassword not between", value1, value2, "apassword");
            return (Criteria) this;
        }

        public Criteria andAemailIsNull() {
            addCriterion("aemail is null");
            return (Criteria) this;
        }

        public Criteria andAemailIsNotNull() {
            addCriterion("aemail is not null");
            return (Criteria) this;
        }

        public Criteria andAemailEqualTo(String value) {
            addCriterion("aemail =", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailNotEqualTo(String value) {
            addCriterion("aemail <>", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailGreaterThan(String value) {
            addCriterion("aemail >", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailGreaterThanOrEqualTo(String value) {
            addCriterion("aemail >=", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailLessThan(String value) {
            addCriterion("aemail <", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailLessThanOrEqualTo(String value) {
            addCriterion("aemail <=", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailLike(String value) {
            addCriterion("aemail like", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailNotLike(String value) {
            addCriterion("aemail not like", value, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailIn(List<String> values) {
            addCriterion("aemail in", values, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailNotIn(List<String> values) {
            addCriterion("aemail not in", values, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailBetween(String value1, String value2) {
            addCriterion("aemail between", value1, value2, "aemail");
            return (Criteria) this;
        }

        public Criteria andAemailNotBetween(String value1, String value2) {
            addCriterion("aemail not between", value1, value2, "aemail");
            return (Criteria) this;
        }

        public Criteria andAtelphoneIsNull() {
            addCriterion("atelphone is null");
            return (Criteria) this;
        }

        public Criteria andAtelphoneIsNotNull() {
            addCriterion("atelphone is not null");
            return (Criteria) this;
        }

        public Criteria andAtelphoneEqualTo(String value) {
            addCriterion("atelphone =", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneNotEqualTo(String value) {
            addCriterion("atelphone <>", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneGreaterThan(String value) {
            addCriterion("atelphone >", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("atelphone >=", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneLessThan(String value) {
            addCriterion("atelphone <", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneLessThanOrEqualTo(String value) {
            addCriterion("atelphone <=", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneLike(String value) {
            addCriterion("atelphone like", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneNotLike(String value) {
            addCriterion("atelphone not like", value, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneIn(List<String> values) {
            addCriterion("atelphone in", values, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneNotIn(List<String> values) {
            addCriterion("atelphone not in", values, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneBetween(String value1, String value2) {
            addCriterion("atelphone between", value1, value2, "atelphone");
            return (Criteria) this;
        }

        public Criteria andAtelphoneNotBetween(String value1, String value2) {
            addCriterion("atelphone not between", value1, value2, "atelphone");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
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