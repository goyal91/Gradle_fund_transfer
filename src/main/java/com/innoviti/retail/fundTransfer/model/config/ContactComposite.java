package com.innoviti.retail.fundTransfer.model.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactComposite implements Serializable {

	private static final long serialVersionUID = -8120766450282827123L;

	@Column(name = "cont_id")
	private Integer contactId;

	@Column(name = "mer_id")
	private String merchantId;

	@Column(name = "chain_id")
	private String chainId;

	@Column(name = "store_code")
	private String storeCode;

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

}
