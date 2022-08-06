package com.zuehlke.training.kafka.witnessprotection.csvproducer.io;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/* PARI_ID;
PARI_CREATEUSER;
PARI_CREATEDATE;
PARI_MODIFYUSER;
PARI_MODIFYDATE;
PARI_RUNDATE;

PARI_PERS_LAUFNUMMER;
PARI_PEVE_ID;
PARI_STATUS;
PARI_DATA;
PARI_EREIGNIS_DATUM;
ECH0058_UNIQUEIDDELIVERY;
ECH0058_TOTALNUMBEROFPACKAGES;
ECH0058_NUMBEROFACTUALPACKAGE;
ECH0058_ACTION;
PARI_STATUS_KEMS;E
CH0058_MESSAGEID;RN
*/

public class LineMessage {

    private long id;
    private String createUser;
    private String crateDate;
    private String modifyUser;
    private String modifyDate;
    private String runDate;
    private String persLaufnummer;
    private String peveId;
    private String status;
    private String data;


	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
    /*private Date ereignisDate;
    private String ECH0058_UNIQUEIDDELIVERY;
    private String ECH0058_TOTALNUMBEROFPACKAGES;
    private String ECH0058_NUMBEROFACTUALPACKAGE;
    private String ECH0058_ACTION;
    private String PARI_STATUS_KEMS;
    private String CH0058_MESSAGEID;*/

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCrateDate() {
		return this.crateDate;
	}

	public void setCrateDate(String crateDate) {
		this.crateDate = crateDate;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getRunDate() {
		return this.runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public String getPersLaufnummer() {
		return this.persLaufnummer;
	}

	public void setPersLaufnummer(String persLaufnummer) {
		this.persLaufnummer = persLaufnummer;
	}

	public String getPeveId() {
		return this.peveId;
	}

	public void setPeveId(String peveId) {
		this.peveId = peveId;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}






    @Override
    public String toString() {
        return "LineMessage{" +
                "id=" + id +
                "; createUser='" + createUser + '\'' +
                "; crateDate='" + crateDate + '\'' +
                "; modifyUser='" + modifyUser + '\'' +
                "; modifyDate='" + modifyDate + '\'' +
                "; runDate='" + runDate + '\'' +
                "; persLaufnummer=" + persLaufnummer +
                "; peveId=" + peveId +
                "; data='" + data + '\'' +
                '}';
    }
}