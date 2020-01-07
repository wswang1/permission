package com.niii.file.pojo;

import java.util.ArrayList;
import java.util.List;

public class FileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameIsNull() {
            addCriterion("file_type_name is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameIsNotNull() {
            addCriterion("file_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameEqualTo(String value) {
            addCriterion("file_type_name =", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameNotEqualTo(String value) {
            addCriterion("file_type_name <>", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameGreaterThan(String value) {
            addCriterion("file_type_name >", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_type_name >=", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameLessThan(String value) {
            addCriterion("file_type_name <", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameLessThanOrEqualTo(String value) {
            addCriterion("file_type_name <=", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameLike(String value) {
            addCriterion("file_type_name like", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameNotLike(String value) {
            addCriterion("file_type_name not like", value, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameIn(List<String> values) {
            addCriterion("file_type_name in", values, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameNotIn(List<String> values) {
            addCriterion("file_type_name not in", values, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameBetween(String value1, String value2) {
            addCriterion("file_type_name between", value1, value2, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeNameNotBetween(String value1, String value2) {
            addCriterion("file_type_name not between", value1, value2, "fileTypeName");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeIsNull() {
            addCriterion("file_type_code is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeIsNotNull() {
            addCriterion("file_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeEqualTo(String value) {
            addCriterion("file_type_code =", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeNotEqualTo(String value) {
            addCriterion("file_type_code <>", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeGreaterThan(String value) {
            addCriterion("file_type_code >", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type_code >=", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeLessThan(String value) {
            addCriterion("file_type_code <", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("file_type_code <=", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeLike(String value) {
            addCriterion("file_type_code like", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeNotLike(String value) {
            addCriterion("file_type_code not like", value, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeIn(List<String> values) {
            addCriterion("file_type_code in", values, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeNotIn(List<String> values) {
            addCriterion("file_type_code not in", values, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeBetween(String value1, String value2) {
            addCriterion("file_type_code between", value1, value2, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileTypeCodeNotBetween(String value1, String value2) {
            addCriterion("file_type_code not between", value1, value2, "fileTypeCode");
            return (Criteria) this;
        }

        public Criteria andFileNoteIsNull() {
            addCriterion("file_note is null");
            return (Criteria) this;
        }

        public Criteria andFileNoteIsNotNull() {
            addCriterion("file_note is not null");
            return (Criteria) this;
        }

        public Criteria andFileNoteEqualTo(String value) {
            addCriterion("file_note =", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteNotEqualTo(String value) {
            addCriterion("file_note <>", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteGreaterThan(String value) {
            addCriterion("file_note >", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteGreaterThanOrEqualTo(String value) {
            addCriterion("file_note >=", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteLessThan(String value) {
            addCriterion("file_note <", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteLessThanOrEqualTo(String value) {
            addCriterion("file_note <=", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteLike(String value) {
            addCriterion("file_note like", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteNotLike(String value) {
            addCriterion("file_note not like", value, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteIn(List<String> values) {
            addCriterion("file_note in", values, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteNotIn(List<String> values) {
            addCriterion("file_note not in", values, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteBetween(String value1, String value2) {
            addCriterion("file_note between", value1, value2, "fileNote");
            return (Criteria) this;
        }

        public Criteria andFileNoteNotBetween(String value1, String value2) {
            addCriterion("file_note not between", value1, value2, "fileNote");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
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