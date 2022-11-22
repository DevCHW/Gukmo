package com.gukmo.board.model;

public class AdVO {
	private String advertisement_num;		// 광고글 번호
	private String division;				// 구분(메인/게시판)
	private String client_name;				// 클라이언트 명
	private String client_phone;			// 클라이언트 번호
	private String file_name;				// 파일명
	private String url;						// 링크 주소
	private String start_date;				// 광고 시작일자(default sysdate)
	private String period;					// 광고 기간(단위 : 일수)
	private String status;					// 광고 상태(0: 광고중, 1: 노광고중)

	protected AdVO() {}
	
	public AdVO(String advertisement_num, String division, String client_name,
				String client_phone, String file_name, String url, String start_date,
				String period, String status) {
		this.advertisement_num = advertisement_num;
		this.division = division;
		this.client_name = client_name;
		this.client_phone = client_phone;
		this.file_name = file_name;
		this.url = url;
		this.start_date = start_date;
		this.period = period;
		this.status = status;
	}

	public String getAdvertisement_num() {
		return advertisement_num;
	}

	public String getDivision() {
		return division;
	}

	public String getClient_name() {
		return client_name;
	}

	public String getClient_phone() {
		return client_phone;
	}

	public String getFile_name() {
		return file_name;
	}

	public String getUrl() {
		return url;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getPeriod() {
		return period;
	}

	public String getStatus() {
		return status;
	}







}
