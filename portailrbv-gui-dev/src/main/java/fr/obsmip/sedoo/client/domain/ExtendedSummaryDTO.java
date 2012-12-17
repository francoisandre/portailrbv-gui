package fr.obsmip.sedoo.client.domain;


public class ExtendedSummaryDTO extends SummaryDTO implements HasId {
	
	private static final int MAX_ABSTRACT_LENGTH = 1000;
	private String state;
	private String LastModificationDate;
	private Long id;
	
	public ExtendedSummaryDTO() {
	}
	
	public ExtendedSummaryDTO(SummaryDTO summary)
	{
		setResourceAbstract(summary.getResourceAbstract());
		setResourceTitle(summary.getResourceTitle());
		setUuid(summary.getUuid());
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastModificationDate() {
		return LastModificationDate;
	}

	public void setLastModificationDate(String lastModificationDate) {
		LastModificationDate = lastModificationDate;
	}

}
