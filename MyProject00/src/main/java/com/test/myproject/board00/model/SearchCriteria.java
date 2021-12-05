package com.test.myproject.board00.model;

public class SearchCriteria extends Criteria {

	 private String searchKey ="";
	 private String searchWord = "";
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	@Override
	public String toString() {
		return super.toString() + "SearchCriteria [searchKey=" + searchKey + ", searchWord=" + searchWord + "]";
	}	 
	
} // end class
