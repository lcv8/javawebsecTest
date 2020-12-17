package com.hui.entity;

import java.util.Date;

/**
 * @author lcv8
 */
public class BookInfo {
    private Integer bookId;
    private String bookCode;
    private String bookName;
    private Integer bookTypeId;
    private String bookAuthor;
    private String publishPress;
    private Date publishDate;
    private Integer isBorrow;
    private Integer createBy;
    private Date creationTime;
    private Date lastUpdateTime;

    private BookType bookType;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public BookInfo setUsers(Users users) {
        this.users = users;
        return this;
    }

    public BookType getBookType() {
        return bookType;
    }

    public BookInfo setBookType(BookType bookType) {
        this.bookType = bookType;
        return this;
    }

    public Integer getBookId() {
        return bookId;
    }

    public BookInfo setBookId(Integer bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookCode() {
        return bookCode;
    }

    public BookInfo setBookCode(String bookCode) {
        this.bookCode = bookCode;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public BookInfo setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public BookInfo setBookTypeId(Integer bookType) {
        this.bookTypeId = bookTypeId;
        return this;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public BookInfo setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
        return this;
    }

    public String getPublishPress() {
        return publishPress;
    }

    public BookInfo setPublishPress(String publishPress) {
        this.publishPress = publishPress;
        return this;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public BookInfo setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getIsBorrow() {
        return isBorrow;
    }

    public BookInfo setIsBorrow(Integer isBorrow) {
        this.isBorrow = isBorrow;
        return this;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public BookInfo setCreateBy(Integer createBy) {
        this.createBy = createBy;
        return this;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public BookInfo setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public BookInfo setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookCode='" + bookCode + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookTypeId=" + bookTypeId +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publishPress='" + publishPress + '\'' +
                ", publishDate=" + publishDate +
                ", isBorrow=" + isBorrow +
                ", createBy=" + createBy +
                ", creationTime=" + creationTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", bookType=" + bookType +
                ", users=" + users +
                '}';
    }
}
