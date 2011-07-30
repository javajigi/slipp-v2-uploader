package models;

import siena.embed.EmbeddedMap;

@EmbeddedMap
public class UploaderUser {
	private String email;
	private String nickName;
	private String ipAddress;
	
	public UploaderUser(){
	}
	
	public UploaderUser(String email, String nickName, String ipAddress) {
		this.email = email;
		this.nickName = nickName;
		this.ipAddress = ipAddress;
	}

	public String getEmail() {
		return email;
	}

	public String getNickName() {
		return nickName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploaderUser other = (UploaderUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UploaderUser [email=" + email + ", nickName=" + nickName + ", ipAddress=" + ipAddress + "]";
	}
}
