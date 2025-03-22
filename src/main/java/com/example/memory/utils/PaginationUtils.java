package com.example.memory.utils;


import java.sql.Timestamp;

public class PaginationUtils {
    public record Pagination(String anchorId, Timestamp fromTs, Timestamp toTs, boolean isForward, int limit) {
        @Override
        public String toString() {
            return "Pagination{" +
                "anchorId='" + anchorId + '\'' +
                ", fromTs=" + fromTs +
                ", toTs=" + toTs +
                ", isForward=" + isForward +
                ", limit=" + limit +
                '}';
        }
    }
}
