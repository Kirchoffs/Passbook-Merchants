package com.syh.passbook.merchants.vo;

import com.syh.passbook.merchants.constant.ErrorCode;
import com.syh.passbook.merchants.dao.MerchantDao;
import com.syh.passbook.merchants.entity.Merchant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantRequest {
    private String name;
    private String logoUrl;
    private String businessLicenceUrl;
    private String phone;
    private String address;
    public ErrorCode validate(MerchantDao merchantDao) {
        if (this.name == null || this.name.length() == 0) {
            return ErrorCode.EMPTY_NAME;
        }
        if (merchantDao.findByName(this.name).isPresent()) {
            return ErrorCode.DUPLICATE_NAME;
        }
        if (this.logoUrl == null) {
            return ErrorCode.EMPTY_LOGO;
        }
        if (this.businessLicenceUrl == null) {
            return ErrorCode.EMPTY_BUSINESS_LICENCE;
        }
        if (this.address == null) {
            return ErrorCode.EMPTY_ADDRESS;
        }
        if (this.phone == null) {
            return ErrorCode.ERROR_PHONE;
        }

        return ErrorCode.SUCCESS;
    }

    /**
     *
     * @return {@link Merchant}
     */
    public Merchant toMerchant() {
        Merchant merchant = new Merchant();

        merchant.setName(name);
        merchant.setLogoUrl(logoUrl);
        merchant.setBusinessLicenceUrl(businessLicenceUrl);
        merchant.setPhone(phone);
        merchant.setAddress(address);

        return merchant;
    }
}
