package controllers;

import java.io.IOException;

import models.AttachedFile;
import models.UploaderUser;
import play.data.Upload;
import play.mvc.Controller;
import play.mvc.With;
import siena.SienaException;
import supports.web.Auth;
import supports.web.Check;
import supports.web.GAESecure;
import supports.web.Role;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.users.User;

@With(GAESecure.class)
public class AttachedFiles extends Controller {
	private static final int LIMIT_UPLOAD_SIZE = 1048576;

	@Check(Role.ROLE_USER)
	public static void uploadForm(Long id) {
		render();
	}

	@Check(Role.ROLE_USER)
	public static void upload(Upload file) {
		AttachedFile attachedFile;
		if (file.getSize() > LIMIT_UPLOAD_SIZE) {
			attachedFile = new AttachedFile();
			renderArgs.put("hasError", true);
			renderArgs.put("errorMessage", "1MB 이상의 파일을 업로드할 수 없습니다.");
			render(attachedFile);
		}

		User user = Auth.getUser();
		UploaderUser uploaderUser = new UploaderUser(user.getEmail(), user.getNickname(), request.remoteAddress);
		Blob attachFile = new Blob(file.asBytes());
		attachedFile = new AttachedFile(file.getFileName(), file.getSize(), file.getContentType(), attachFile,
				uploaderUser);

		if (!attachedFile.isImage()) {
			attachedFile = new AttachedFile();
			renderArgs.put("hasError", true);
			renderArgs.put("errorMessage", "이미지 파일만 업로드할 수 있습니다.");
			render(attachedFile);
		}

		attachedFile.insert();
		
		renderArgs.put("hasError", false);
		renderArgs.put("errorMessage", "");
		render(attachedFile);
	}

	public static void showImage(Long id) throws IOException {
		AttachedFile attachedFile = null;
		try {
			attachedFile = new AttachedFile(id);
			attachedFile.get();
		} catch (SienaException e) {
			throw new NullPointerException("존재하지 않는 이미지입니다.");
		}

		response.contentType = attachedFile.getContentsType();
		response.setHeader("Content-Disposition", "filename=\"" + attachedFile.getName() + "\"");
		response.out.write(attachedFile.getAttachFile().getBytes());
	}
}
