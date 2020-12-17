package com.hui.entity;

/**
 * @author lcv8
 */
public class BookType {
    private Integer bookTypeId;
    private String typeName;

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public BookType setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public BookType setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "bookTypeId=" + bookTypeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
