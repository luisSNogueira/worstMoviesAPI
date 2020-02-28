package br.com.goldenraspberry.application.model;

public class Producer implements DTO {
	
	private Long id;
	
	private String name;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
