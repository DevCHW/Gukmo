package com.gukmo.board.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class DataTableDTO {
	private int draw;
    private int recordsTotal;
    private int recordsFiltered;

    private List<?> data;

    public List<?> getData(){
        if(CollectionUtils.isEmpty(data)){
            data = new ArrayList<>();
        }
        return data;
    }

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataTableDTO [draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered=" + recordsFiltered
				+ ", data=" + data + "]";
	}
	
	
	
    
}
