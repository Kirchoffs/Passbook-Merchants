package com.syh.passbook.merchants.vo;

import com.syh.passbook.merchants.constant.ErrorCode;
import com.syh.passbook.merchants.dao.MerchantDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {
    private Integer id;

    private String title;

    private String summary;

    private String desc;

    private Long limit;

    private boolean hasToken; // tokens are saved in Redis Set

    private Integer background;

    private Date start;

    private Date end;

    /**
     * check if pass is valid
     * @param merchantDao {@link MerchantDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantDao merchantDao) {
        if (!merchantDao.findById(id).isPresent()) {
            return ErrorCode.MERCHANT_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}
