package com.android.astra2;

public class Vehicle {
	
	private String rating;
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String name;

    private String group;
    
    private String desc;

	private String resourceVehicleGallery;
    
    public String getResourceVehicleGallery() {
		return resourceVehicleGallery;
	}

	public void setResourceVehicleGallery(String resourceVehicleGallery) {
		this.resourceVehicleGallery = resourceVehicleGallery;
	}

	private String resourceModelGroup;
    
    public String getResourceModelGroup() {
		return resourceModelGroup;
	}

	public void setResourceModelGroup(String resourceModelGroup) {
		this.resourceModelGroup = resourceModelGroup;
	}

	private String resource360Group;
    
    public String getResource360Group() {
		return resource360Group;
	}

	public void setResource360Group(String resource360Group) {
		this.resource360Group = resource360Group;
	}

	private String resourceThumbName;
    public String getResourceThumbName() {
		return resourceThumbName;
	}

	public void setResourceThumbName(String resourceThumbName) {
		this.resourceThumbName = resourceThumbName;
	}

	private String resourceLogoName;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Vehicle(){
    	
    }
    
    public Vehicle(String name) {
        this.name = name;
    }
    
    public Vehicle(String name, String desc){
    	this.name = name;
    	this.desc = desc;
    }
    
    public Vehicle(String name, String desc, String resourceLogoName){
    	setName(name);
    	setDesc(desc);
    	setResourceLogoName(resourceLogoName);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDesc(){
    	return desc;
    }
    
    public void setDesc(String desc){
    	this.desc = desc;
    }
    
    public String getResourceLogoName(){
    	return resourceLogoName;
    }
    
    public void setResourceLogoName(String resourceLogoName){
    	this.resourceLogoName = resourceLogoName;
    }
}
