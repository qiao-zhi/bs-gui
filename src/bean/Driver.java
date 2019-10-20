package bean;

import java.util.Date;

public class Driver {

	private Integer id;

	private String firstname;
	private String lastname;
	private Date birthdate;
	private Double licencenumber;
	private String licencestate;

	public static String[] dateKeys = new String[] { "birthdate" };
	public static String[] doubleKeys = new String[] { "licencenumber" };

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Double getLicencenumber() {
		return licencenumber;
	}

	public void setLicencenumber(Double licencenumber) {
		this.licencenumber = licencenumber;
	}

	public String getLicencestate() {
		return licencestate;
	}

	public void setLicencestate(String licencestate) {
		this.licencestate = licencestate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
