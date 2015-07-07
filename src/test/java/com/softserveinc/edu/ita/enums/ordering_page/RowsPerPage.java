package com.softserveinc.edu.ita.enums.ordering_page;

public enum RowsPerPage {
    ROWS_10(10),
    ROWS_25(25);

    private int rowsCount;

    RowsPerPage(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }
}
