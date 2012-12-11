package fr.obsmip.sedoo.client.domain;


public class ExtendedSummaryDTO extends SummaryDTO implements HasId {
	
	private static final int MAX_ABSTRACT_LENGTH = 1000;
	private String state;
	private String LastModificationDate;
	private Long id;
	
	public ExtendedSummaryDTO() {
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
