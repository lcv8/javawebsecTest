package com.hui.entity;

public class Page {
    private Integer size;
    private Integer pageNumber;
    private Integer pageCount;
    private Integer index;
    private Integer count;

    public Integer getSize() {
        return size;
    }

    public Page setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Page setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Page setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Integer getIndex() {
        return index;
    }

    public Page setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Page setCount(Integer count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "Page{" +
                "size=" + size +
                ", pageNumber=" + pageNumber +
                ", pageCount=" + pageCount +
                ", index=" + index +
                ", count=" + count +
                '}';
    }
}
