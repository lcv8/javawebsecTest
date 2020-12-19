package com.hui.entity;

/**
 * @author lcv8
 */
public class BookType {
    private Integer bookTypeId;
    private String bookTypeName;

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public BookType setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
        return this;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public BookType setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
        return this;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "bookTypeId=" + bookTypeId +
                ", bookTypeName='" + bookTypeName + '\'' +
                '}';
    }
}
