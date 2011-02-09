package com.android.astra2.jb;

import java.io.Serializable;

import com.android.astra2.util.Utility;

public class CompanyJB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comp_title;
	private String comp_desc;

    public String getCompanyTitle()
    {
    	comp_title = Utility.setSomethingWhenNull(comp_title);
        return this.comp_title;
    }
    
    public void setCompanyTitle(String comp_title)
    {
        this.comp_title = comp_title;
    }	
    
    public String getCompanyDesc()
    {
    	comp_desc = Utility.setSomethingWhenNull(comp_desc);
        return this.comp_desc;
    }
    
    public void setCompanyDesc(String comp_desc)
    {
        this.comp_desc = comp_desc;
    }	    
}