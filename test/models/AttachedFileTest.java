package models;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.appengine.api.datastore.Blob;

public class AttachedFileTest extends AbstractDomainTest {
	private AttachedFile dut;
	
	@Test
	public void insert() throws Exception {
		File file = new File("test/file/Orange.gif");
		UploaderUser uploaderUser = new UploaderUser("javajigi@gmail.com", "javajigi", "127.0.0.1");
		dut = new AttachedFile
			(file.getName(), file.length(), "image/gif", new Blob(FileUtils.readFileToByteArray(file)), uploaderUser);
		dut.insert();
		assertNotNull(dut.getId());
	}
	
	@Test
	public void isImage() throws Exception {
		AttachedFile file = createForContentType("image/gif");
		assertThat(file.isImage(), is(true));
		file = createForContentType("text/xml");
		assertThat(file.isImage(), is(false));
	}
	
	private AttachedFile createForContentType(String contentType) {
		return new AttachedFile("name", 100L, contentType, null, null);
	}
}
