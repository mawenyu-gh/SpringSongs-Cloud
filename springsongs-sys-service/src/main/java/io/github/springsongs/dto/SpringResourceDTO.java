package io.github.springsongs.dto;

import java.util.ArrayList;
import java.util.List;

import io.github.springsongs.domain.SpringResource;

public class SpringResourceDTO extends SpringResource {
	
	private String text;
	
	public String getText() {
		return this.getTitle();
	}

	private List<SpringResourceDTO> children=new ArrayList<>();

	public List<SpringResourceDTO> getChildren() {
		return children;
	}

	public void setChildren(List<SpringResourceDTO> children) {
		this.children = children;
	}


}
