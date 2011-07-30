package models;

import java.util.ArrayList;
import java.util.List;

import siena.Generator;
import siena.Id;
import siena.Model;
import siena.embed.Embedded;

import com.google.appengine.api.datastore.Blob;

public class AttachedFile extends Model {
	private static final List<String> ENABLE_IMAGE_TYPE = new ArrayList<String>(){{
		add("image/bmp");
		add("image/gif");
		add("image/jpeg");
		add("image/png");
		add("image/tiff");
	}};
	
	@Id(Generator.AUTO_INCREMENT)
	private Long id;
	private String name;
	private Long size;
	private String contentsType;
	private Blob attachFile;
	@Embedded
	private UploaderUser uploaderUser;

	public AttachedFile() {
	}

	public AttachedFile(Long id) {
		this.id = id;
	}

	public AttachedFile(String name, Long size, String contentsType, Blob attachFile, UploaderUser uploaderUser) {
		this.name = name;
		this.size = size;
		this.contentsType = contentsType; 
		this.attachFile = attachFile;
		this.uploaderUser = uploaderUser;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getSize() {
		return size;
	}
	
	public String getContentsType() {
		return contentsType;
	}

	public Blob getAttachFile() {
		return attachFile;
	}
	
	public UploaderUser getUploaderUser() {
		return uploaderUser;
	}

	public boolean isEmpty() {
		if (getAttachFile() == null) {
			return true;
		}

		return false;
	}
	
	public boolean isImage() {
		return ENABLE_IMAGE_TYPE.contains(getContentsType());
	}
}
