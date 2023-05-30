package com.app.service;

public class ExcelExportService {
	private String fileName;
	private String mode;
	
	public void setup() {
		//lot of code...
		System.out.println("FROM INIT METHOD");
	}
	
	public void clean() {
		//some code..
		System.out.println("FROM DESTORY METHOD");
	}

	public ExcelExportService() {
		super();
		System.out.println("FROM CONSTRUCTOR");
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		System.out.println("FROM SETTER METHOD");
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return "ExcelExportService [fileName=" + fileName + ", mode=" + mode + "]";
	}
	
	
}
