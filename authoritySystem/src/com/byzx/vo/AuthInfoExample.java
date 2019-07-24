package com.byzx.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuthInfoExample() {
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

        public Criteria andAuthIdIsNull() {
            addCriterion("auth_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNotNull() {
            addCriterion("auth_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthIdEqualTo(Integer value) {
            addCriterion("auth_id =", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotEqualTo(Integer value) {
            addCriterion("auth_id <>", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThan(Integer value) {
            addCriterion("auth_id >", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_id >=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThan(Integer value) {
            addCriterion("auth_id <", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThanOrEqualTo(Integer value) {
            addCriterion("auth_id <=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIn(List<Integer> values) {
            addCriterion("auth_id in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotIn(List<Integer> values) {
            addCriterion("auth_id not in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdBetween(Integer value1, Integer value2) {
            addCriterion("auth_id between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_id not between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andAuthNameIsNull() {
            addCriterion("auth_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthNameIsNotNull() {
            addCriterion("auth_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthNameEqualTo(String value) {
            addCriterion("auth_name =", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotEqualTo(String value) {
            addCriterion("auth_name <>", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameGreaterThan(String value) {
            addCriterion("auth_name >", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameGreaterThanOrEqualTo(String value) {
            addCriterion("auth_name >=", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameLessThan(String value) {
            addCriterion("auth_name <", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameLessThanOrEqualTo(String value) {
            addCriterion("auth_name <=", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameLike(String value) {
            addCriterion("auth_name like", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotLike(String value) {
            addCriterion("auth_name not like", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameIn(List<String> values) {
            addCriterion("auth_name in", values, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotIn(List<String> values) {
            addCriterion("auth_name not in", values, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameBetween(String value1, String value2) {
            addCriterion("auth_name between", value1, value2, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotBetween(String value1, String value2) {
            addCriterion("auth_name not between", value1, value2, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthDescIsNull() {
            addCriterion("auth_desc is null");
            return (Criteria) this;
        }

        public Criteria andAuthDescIsNotNull() {
            addCriterion("auth_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAuthDescEqualTo(String value) {
            addCriterion("auth_desc =", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescNotEqualTo(String value) {
            addCriterion("auth_desc <>", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescGreaterThan(String value) {
            addCriterion("auth_desc >", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescGreaterThanOrEqualTo(String value) {
            addCriterion("auth_desc >=", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescLessThan(String value) {
            addCriterion("auth_desc <", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescLessThanOrEqualTo(String value) {
            addCriterion("auth_desc <=", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescLike(String value) {
            addCriterion("auth_desc like", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescNotLike(String value) {
            addCriterion("auth_desc not like", value, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescIn(List<String> values) {
            addCriterion("auth_desc in", values, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescNotIn(List<String> values) {
            addCriterion("auth_desc not in", values, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescBetween(String value1, String value2) {
            addCriterion("auth_desc between", value1, value2, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthDescNotBetween(String value1, String value2) {
            addCriterion("auth_desc not between", value1, value2, "authDesc");
            return (Criteria) this;
        }

        public Criteria andAuthGradeIsNull() {
            addCriterion("auth_grade is null");
            return (Criteria) this;
        }

        public Criteria andAuthGradeIsNotNull() {
            addCriterion("auth_grade is not null");
            return (Criteria) this;
        }

        public Criteria andAuthGradeEqualTo(Integer value) {
            addCriterion("auth_grade =", value, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeNotEqualTo(Integer value) {
            addCriterion("auth_grade <>", value, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeGreaterThan(Integer value) {
            addCriterion("auth_grade >", value, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_grade >=", value, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeLessThan(Integer value) {
            addCriterion("auth_grade <", value, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeLessThanOrEqualTo(Integer value) {
            addCriterion("auth_grade <=", value, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeIn(List<Integer> values) {
            addCriterion("auth_grade in", values, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeNotIn(List<Integer> values) {
            addCriterion("auth_grade not in", values, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeBetween(Integer value1, Integer value2) {
            addCriterion("auth_grade between", value1, value2, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_grade not between", value1, value2, "authGrade");
            return (Criteria) this;
        }

        public Criteria andAuthTypeIsNull() {
            addCriterion("auth_type is null");
            return (Criteria) this;
        }

        public Criteria andAuthTypeIsNotNull() {
            addCriterion("auth_type is not null");
            return (Criteria) this;
        }

        public Criteria andAuthTypeEqualTo(String value) {
            addCriterion("auth_type =", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeNotEqualTo(String value) {
            addCriterion("auth_type <>", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeGreaterThan(String value) {
            addCriterion("auth_type >", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeGreaterThanOrEqualTo(String value) {
            addCriterion("auth_type >=", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeLessThan(String value) {
            addCriterion("auth_type <", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeLessThanOrEqualTo(String value) {
            addCriterion("auth_type <=", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeLike(String value) {
            addCriterion("auth_type like", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeNotLike(String value) {
            addCriterion("auth_type not like", value, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeIn(List<String> values) {
            addCriterion("auth_type in", values, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeNotIn(List<String> values) {
            addCriterion("auth_type not in", values, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeBetween(String value1, String value2) {
            addCriterion("auth_type between", value1, value2, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthTypeNotBetween(String value1, String value2) {
            addCriterion("auth_type not between", value1, value2, "authType");
            return (Criteria) this;
        }

        public Criteria andAuthUrlIsNull() {
            addCriterion("auth_url is null");
            return (Criteria) this;
        }

        public Criteria andAuthUrlIsNotNull() {
            addCriterion("auth_url is not null");
            return (Criteria) this;
        }

        public Criteria andAuthUrlEqualTo(String value) {
            addCriterion("auth_url =", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotEqualTo(String value) {
            addCriterion("auth_url <>", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlGreaterThan(String value) {
            addCriterion("auth_url >", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlGreaterThanOrEqualTo(String value) {
            addCriterion("auth_url >=", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlLessThan(String value) {
            addCriterion("auth_url <", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlLessThanOrEqualTo(String value) {
            addCriterion("auth_url <=", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlLike(String value) {
            addCriterion("auth_url like", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotLike(String value) {
            addCriterion("auth_url not like", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlIn(List<String> values) {
            addCriterion("auth_url in", values, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotIn(List<String> values) {
            addCriterion("auth_url not in", values, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlBetween(String value1, String value2) {
            addCriterion("auth_url between", value1, value2, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotBetween(String value1, String value2) {
            addCriterion("auth_url not between", value1, value2, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNull() {
            addCriterion("auth_code is null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNotNull() {
            addCriterion("auth_code is not null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeEqualTo(String value) {
            addCriterion("auth_code =", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotEqualTo(String value) {
            addCriterion("auth_code <>", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThan(String value) {
            addCriterion("auth_code >", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThanOrEqualTo(String value) {
            addCriterion("auth_code >=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThan(String value) {
            addCriterion("auth_code <", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThanOrEqualTo(String value) {
            addCriterion("auth_code <=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLike(String value) {
            addCriterion("auth_code like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotLike(String value) {
            addCriterion("auth_code not like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIn(List<String> values) {
            addCriterion("auth_code in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotIn(List<String> values) {
            addCriterion("auth_code not in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeBetween(String value1, String value2) {
            addCriterion("auth_code between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotBetween(String value1, String value2) {
            addCriterion("auth_code not between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthOrderIsNull() {
            addCriterion("auth_order is null");
            return (Criteria) this;
        }

        public Criteria andAuthOrderIsNotNull() {
            addCriterion("auth_order is not null");
            return (Criteria) this;
        }

        public Criteria andAuthOrderEqualTo(Integer value) {
            addCriterion("auth_order =", value, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderNotEqualTo(Integer value) {
            addCriterion("auth_order <>", value, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderGreaterThan(Integer value) {
            addCriterion("auth_order >", value, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_order >=", value, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderLessThan(Integer value) {
            addCriterion("auth_order <", value, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderLessThanOrEqualTo(Integer value) {
            addCriterion("auth_order <=", value, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderIn(List<Integer> values) {
            addCriterion("auth_order in", values, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderNotIn(List<Integer> values) {
            addCriterion("auth_order not in", values, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderBetween(Integer value1, Integer value2) {
            addCriterion("auth_order between", value1, value2, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_order not between", value1, value2, "authOrder");
            return (Criteria) this;
        }

        public Criteria andAuthStateIsNull() {
            addCriterion("auth_state is null");
            return (Criteria) this;
        }

        public Criteria andAuthStateIsNotNull() {
            addCriterion("auth_state is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStateEqualTo(String value) {
            addCriterion("auth_state =", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateNotEqualTo(String value) {
            addCriterion("auth_state <>", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateGreaterThan(String value) {
            addCriterion("auth_state >", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateGreaterThanOrEqualTo(String value) {
            addCriterion("auth_state >=", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateLessThan(String value) {
            addCriterion("auth_state <", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateLessThanOrEqualTo(String value) {
            addCriterion("auth_state <=", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateLike(String value) {
            addCriterion("auth_state like", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateNotLike(String value) {
            addCriterion("auth_state not like", value, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateIn(List<String> values) {
            addCriterion("auth_state in", values, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateNotIn(List<String> values) {
            addCriterion("auth_state not in", values, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateBetween(String value1, String value2) {
            addCriterion("auth_state between", value1, value2, "authState");
            return (Criteria) this;
        }

        public Criteria andAuthStateNotBetween(String value1, String value2) {
            addCriterion("auth_state not between", value1, value2, "authState");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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